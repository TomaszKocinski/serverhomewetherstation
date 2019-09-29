package pl.kotbinarny.licencjat.service.weka;


import java.io.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.data.convert.JodaTimeConverters;
import org.springframework.stereotype.Component;
import pl.kotbinarny.licencjat.domain.Prediction;
import pl.kotbinarny.licencjat.domain.mapper.PredictionMapper;
import weka.core.Instances;
import weka.classifiers.functions.GaussianProcesses;
import weka.classifiers.evaluation.NumericPrediction;
import weka.classifiers.timeseries.WekaForecaster;
import weka.filters.supervised.attribute.TSLagMaker;
//import weka.classifiers.timeseries.core.TSLagMaker;


/**
 * Example of using the time series forecasting API. To compile and
 * run the CLASSPATH will need to contain:
 *
 * weka.jar (from your weka distribution)
 * pdm-timeseriesforecasting-ce-TRUNK-SNAPSHOT.jar (from the time series package)
 * jcommon-1.0.14.jar (from the time series package lib directory)
 * jfreechart-1.0.13.jar (from the time series package lib directory)
 */
@Component
@Slf4j
public class ForecastingTimeSeries {



    List<Prediction> calculate(Instances instances) {
        try {
            ArrayList<Prediction> predictions = new ArrayList<>();
            WekaForecaster forecaster = new WekaForecaster();

            // set the targets we want to forecast. This method calls
            // setFieldsToLag() on the lag maker object for us
            forecaster.setFieldsToForecast(InstanceOrder.getCommaSeperetedValuesBesideDate());

            // default underlying classifier is SMOreg (SVM) - we'll use
            // gaussian processes for regression instead
            forecaster.setBaseForecaster(new GaussianProcesses());

            forecaster.getTSLagMaker().setTimeStampField(InstanceOrder.getDate()); // date time stamp
            forecaster.getTSLagMaker().setMinLag(1);
            forecaster.getTSLagMaker().setMaxLag(100);


            // add a month of the year indicator field
            forecaster.getTSLagMaker().setAddMonthOfYear(true);

            // add a quarter of the year indicator field
            forecaster.getTSLagMaker().setAddQuarterOfYear(true);

            // build the model
            forecaster.buildForecaster(instances, System.out);
            DateTime currentDt = getCurrentDateTime(forecaster.getTSLagMaker());

            // prime the forecaster with enough recent historical data
            // to cover up to the maximum lag. In our case, we could just supply
            // the 12 most recent historical instances, as this covers our maximum
            // lag period
            forecaster.primeForecaster(instances);

            // forecast for 12 units (months) beyond the end of the
            // training data
            List<List<NumericPrediction>> forecast = forecaster.forecast(50, System.out);


            // output the predictions. Outer list is over the steps; inner list is over
            // the targets
            for (int i = 0; i < 50; i++) {
                List<NumericPrediction> predsAtStep = forecast.get(i);

                Prediction prediction = new Prediction();
                for (int j = 0; j < 4; j++) {
                    NumericPrediction predForTarget = predsAtStep.get(j);
                    PredictionMapper.mapByEnum(prediction, InstanceOrder.values()[j], predForTarget.predicted());
                }
                currentDt = advanceTime(forecaster.getTSLagMaker(), currentDt);
                prediction.setDate(LocalDateTime.ofInstant(JodaTimeConverters.JodaLocalDateTimeToInstant.INSTANCE.convert(currentDt.toLocalDateTime()), ZoneId.systemDefault()));
                predictions.add(prediction);
                log.info(prediction.toString());
            }

            // we can continue to use the trained forecaster for further forecasting
            // by priming with the most recent historical data (as it becomes available).
            // At some stage it becomes prudent to re-build the model using current
            // historical data.
            return predictions;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("Something went wrong with TimeSerieesCalculate prediction, you propably need to debug this");
        }
    }

    public static void calculate(String[] args) {
        try {
            // path to the Australian wine data included with the time series forecasting
            // package
            String pathToWineData = weka.core.WekaPackageManager.PACKAGES_DIR.toString()
                    + File.separator + "timeseriesForecasting" + File.separator + "sample-data"
                    + File.separator + "wine.arff";

            // load the wine data
            Instances wine = new Instances(new BufferedReader(new FileReader(pathToWineData)));

            // new forecaster
            WekaForecaster forecaster = new WekaForecaster();

            // set the targets we want to forecast. This method calls
            // setFieldsToLag() on the lag maker object for us
            forecaster.setFieldsToForecast("Fortified,Dry-white");

            // default underlying classifier is SMOreg (SVM) - we'll use
            // gaussian processes for regression instead
            forecaster.setBaseForecaster(new GaussianProcesses());

            forecaster.getTSLagMaker().setTimeStampField("Date"); // date time stamp
            forecaster.getTSLagMaker().setMinLag(1);
            forecaster.getTSLagMaker().setMaxLag(12); // monthly data

            // add a month of the year indicator field
            forecaster.getTSLagMaker().setAddMonthOfYear(true);

            // add a quarter of the year indicator field
            forecaster.getTSLagMaker().setAddQuarterOfYear(true);

            // build the model
            forecaster.buildForecaster(wine, System.out);

            // prime the forecaster with enough recent historical data
            // to cover up to the maximum lag. In our case, we could just supply
            // the 12 most recent historical instances, as this covers our maximum
            // lag period
            forecaster.primeForecaster(wine);

            // forecast for 12 units (months) beyond the end of the
            // training data
            List<List<NumericPrediction>> forecast = forecaster.forecast(12, System.out);

            // output the predictions. Outer list is over the steps; inner list is over
            // the targets
            for (int i = 0; i < 12; i++) {
                List<NumericPrediction> predsAtStep = forecast.get(i);
                for (int j = 0; j < 2; j++) {
                    NumericPrediction predForTarget = predsAtStep.get(j);
                    System.out.print("" + predForTarget.predicted() + " ");
                }
                System.out.println();
            }

            // we can continue to use the trained forecaster for further forecasting
            // by priming with the most recent historical data (as it becomes available).
            // At some stage it becomes prudent to re-build the model using current
            // historical data.

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static double getPredictedOnly2PastPierod(NumericPrediction predForTarget) {
        return (long)(predForTarget.predicted()*100)/100.00;
    }

    private static DateTime getCurrentDateTime(TSLagMaker lm) throws Exception {
        return advanceTime(lm,new DateTime((long)lm.getCurrentTimeStampValue()));
    }

    private static DateTime advanceTime(TSLagMaker lm, DateTime dt) {
        return new DateTime((long)lm.advanceSuppliedTimeValue(dt.getMillis()));
    }
}
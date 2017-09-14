package pl.kotbinarny.licencjat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kotbinarny.licencjat.dao.StatisticsDao;
import pl.kotbinarny.licencjat.domain.Sensor;
import pl.kotbinarny.licencjat.domain.Statistics;
import pl.kotbinarny.licencjat.dto.DateValueDTO;
import pl.kotbinarny.licencjat.service.api.StatisticsService;
import pl.kotbinarny.licencjat.service.api.TemperatureDataService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Transactional
@Service
public class StatisticsServiceImpl implements StatisticsService {
    public static final String MEAN_DAILY = "meanDaily";
    public static final String MAX_DAILY = "maxDaily";
    public static final String MIN_DAILY = "minDaily";

    @Autowired
    StatisticsDao statisticsDao;
    @Autowired
    TemperatureDataService temperatureDataService;

    public void meanDaily(Sensor sensor, LocalDateTime from, LocalDateTime to) {
        Statistics statistics = statisticsDao.findBySensorAndDateFromAndDateToAndType(sensor, from, to, MEAN_DAILY);
        if (statistics == null) statistics = new Statistics(MEAN_DAILY, from, to, sensor);
        List<DateValueDTO> dateValueDTOList = temperatureDataService.findAllBySensorHighAndLowerDate(sensor, from, to);
        BigDecimal sum = BigDecimal.ZERO;
        for (DateValueDTO element : dateValueDTOList) {
            sum = sum.add(element.getValue());
        }
        statistics.setValue(sum.divide(new BigDecimal(dateValueDTOList.size())));
        statisticsDao.save(statistics);
    }

    public void maxDaily(Sensor sensor, LocalDateTime from, LocalDateTime to) {
        Statistics statistics = statisticsDao.findBySensorAndDateFromAndDateToAndType(sensor, from, to, MAX_DAILY);
        if (statistics == null) statistics = new Statistics(MAX_DAILY, from, to, sensor);
        List<DateValueDTO> dateValueDTOList = temperatureDataService.findAllBySensorHighAndLowerDate(sensor, from, to);
        BigDecimal max = BigDecimal.ZERO;
        for (DateValueDTO element : dateValueDTOList) {
            int isGreater = max.compareTo(element.getValue());
            if (Integer.compare(1, isGreater) > 0) {
                max = element.getValue();
            }
            max = max.add(element.getValue());
        }
        statistics.setValue(max);
        statisticsDao.save(statistics);
    }

    public void minDaily(Sensor sensor, LocalDateTime from, LocalDateTime to) {
        Statistics statistics = statisticsDao.findBySensorAndDateFromAndDateToAndType(sensor, from, to, MIN_DAILY);
        if (statistics == null) statistics = new Statistics(MIN_DAILY, from, to, sensor);
        List<DateValueDTO> dateValueDTOList = temperatureDataService.findAllBySensorHighAndLowerDate(sensor, from, to);
        BigDecimal min = BigDecimal.ZERO;
        for (DateValueDTO element : dateValueDTOList) {
            int isLesser = min.compareTo(element.getValue());
            if (Integer.compare(-1, isLesser) > 0) {
                min = element.getValue();
            }
            min = min.add(element.getValue());
        }
        statistics.setValue(min);
        statisticsDao.save(statistics);
    }
}

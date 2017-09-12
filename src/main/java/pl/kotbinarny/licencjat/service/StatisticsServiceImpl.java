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
    @Autowired
    StatisticsDao statisticsDao;
    @Autowired
    TemperatureDataService temperatureDataService;

    public void meanDaily(Sensor sensor, LocalDateTime from, LocalDateTime to, String type) {
        Statistics statistics = statisticsDao.findBySensorAndDateFromAndDateToAndType(sensor, from, to, type);
        if (statistics == null) statistics = new Statistics();
        List<DateValueDTO> dateValueDTOList=temperatureDataService.findAllBySensorHighAndLowerDate(sensor,from,to);

        BigDecimal sum=BigDecimal.ZERO;
        for(DateValueDTO element:dateValueDTOList){
            sum=sum.add(element.getValue());
        }
        //BigDecimal mean=sum.divide(new BigDecimal(dateValueDTOList.size()));

    }
}

package pl.kotbinarny.licencjat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kotbinarny.licencjat.dao.StatisticsDao;
import pl.kotbinarny.licencjat.dao.TemperatureDataDao;
import pl.kotbinarny.licencjat.domain.TemperatureData;
import pl.kotbinarny.licencjat.service.api.StatisticsService;
import pl.kotbinarny.licencjat.service.api.TemperatureDataService;

@Transactional
@Service
public class StatisticsServiceImpl implements StatisticsService{
    @Autowired
    StatisticsDao statisticsDao;
    @Autowired
    TemperatureDataService temperatureDataService;

}

package pl.kotbinarny.licencjat.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kotbinarny.licencjat.domain.Statistics;

public interface StatisticsDao extends JpaRepository<Statistics,Long> {

}

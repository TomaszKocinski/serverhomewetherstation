package pl.kotbinarny.licencjat.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kotbinarny.licencjat.domain.Data;

/**
 * Created by tkocinski on 18.07.2017.
 */
public interface DataDao extends JpaRepository<Data,Integer> {
}

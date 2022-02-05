package wisebits.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import wisebits.model.CountryCounterEntity;

import java.util.List;

public interface CountryCounterRepository extends JpaRepository<CountryCounterEntity, String> {
    @Query("SELECT country FROM #{#entityName}")
    List<String> getCountryCountInfo();
}

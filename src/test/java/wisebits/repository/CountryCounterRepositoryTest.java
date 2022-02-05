package wisebits.repository;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import wisebits.model.CountryCounterEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CountryCounterRepositoryTest {
    @Autowired
    private CountryCounterRepository countryCounterRepository;

    @BeforeEach
    public void setUp() {
        countryCounterRepository.deleteAll();
    }

    @Test
    public void test_repo_communication() {
        //given
        var expected = List.of("RU", "RU", "RU", "US", "US", "US", "US", "CY");

        countryCounterRepository.save(new CountryCounterEntity().setCountry("RU"));
        countryCounterRepository.save(new CountryCounterEntity().setCountry("RU"));
        countryCounterRepository.save(new CountryCounterEntity().setCountry("RU"));
        countryCounterRepository.save(new CountryCounterEntity().setCountry("US"));
        countryCounterRepository.save(new CountryCounterEntity().setCountry("US"));
        countryCounterRepository.save(new CountryCounterEntity().setCountry("US"));
        countryCounterRepository.save(new CountryCounterEntity().setCountry("US"));
        countryCounterRepository.save(new CountryCounterEntity().setCountry("CY"));

        //when
        var actual = countryCounterRepository.getCountryCountInfo();

        //then
        assertEquals(expected, actual);
    }
}

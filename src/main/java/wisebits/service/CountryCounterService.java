package wisebits.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wisebits.model.CountryCounterEntity;
import wisebits.repository.CountryCounterRepository;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CountryCounterService {
    private final CountryCounterRepository countryCounterRepository;

    public void increment(String country) {
        countryCounterRepository.save(new CountryCounterEntity().setCountry(country));
    }

    public Map<String, Long> getCountryCounterInfo() {
        return countryCounterRepository.getCountryCountInfo()
                .stream()
                .collect(
                        Collectors.groupingBy(Function.identity(), Collectors.counting())
                );
    }

}

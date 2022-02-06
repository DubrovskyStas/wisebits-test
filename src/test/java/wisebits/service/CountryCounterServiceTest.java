package wisebits.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import wisebits.model.CountryCounterEntity;
import wisebits.repository.CountryCounterRepository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CountryCounterServiceTest {
    @Mock
    private CountryCounterRepository countryCounterRepository;
    @InjectMocks
    private CountryCounterService countryCounterService;

    @Test
    public void check_increment() {
        //given
        ArgumentCaptor<CountryCounterEntity> captor = ArgumentCaptor.forClass(CountryCounterEntity.class);
        UUID mockUuid = UUID.randomUUID();
        CountryCounterEntity expectedEntity = new CountryCounterEntity()
                .setId(mockUuid.toString())
                .setCountry("RU");

        try (MockedStatic<UUID> utilities = mockStatic(UUID.class)) {
            utilities.when(UUID::randomUUID).thenReturn(mockUuid);

            //when
            countryCounterService.increment("RU");

            //then
            verify(countryCounterRepository).save(captor.capture());

            var actualEntity = captor.getValue();

            assertEquals(expectedEntity.getCountry(), actualEntity.getCountry());
            assertEquals(expectedEntity.getId(), actualEntity.getId());
        }
    }

    @Test
    public void getCountryCounterInfo() {
        //given
        var expected = Map.of("RU", 2L, "US", 3L, "CY", 4L);
        when(countryCounterRepository.getCountryCountInfo())
                .thenReturn(List.of("RU", "RU", "US", "US", "US", "CY", "CY", "CY", "CY"));

        //when
        var actual = countryCounterService.getCountryCounterInfo();

        //then
        assertEquals(expected, actual);
    }
}

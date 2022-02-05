package wisebits.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import wisebits.controller.dto.IncrementRequest;
import wisebits.controller.dto.CounterInfoResponse;
import wisebits.service.CountryCounterService;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/")
@RequiredArgsConstructor
public class CountryCounterController {

    private final CountryCounterService countryCounterService;

    @PostMapping("/increment")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void increment(@RequestBody @Valid IncrementRequest incrementRequest) {
        countryCounterService.increment(incrementRequest.getCountryCode().getAlpha2());
    }

    @GetMapping("/country-counter-info")
    @ResponseStatus(HttpStatus.OK)
    public CounterInfoResponse getInfo(){
        return new CounterInfoResponse(countryCounterService.getCountryCounterInfo());
    }
}

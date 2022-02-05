package wisebits.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import wisebits.service.CountryCounterService;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.Mockito.doReturn;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CountryCounterControllerTest {
    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    private CountryCounterService countryCounterService;

    @Test
    public void check_correct_increment() throws Exception {
        mockMvc.perform(
                post("/v1/increment")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"countryCode\": \"ru\"}"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void check_incorrect_increment() throws Exception {
        mockMvc.perform(
                        post("/v1/increment")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"countryCode\": \"rur\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"message\":\"Country code(ISO 3166-1 alpha-2) is not in a list of codes. Full list is here https://www.iban.com/country-codes\"}"));
    }

    @Test
    public void check_correct_info() throws Exception {
        doReturn(Map.of("RU", 6L, "CY", 4L, "US", 2L))
                .when(countryCounterService).getCountryCounterInfo();

        mockMvc.perform(get("/v1/country-counter-info"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "    \"info\": {\n" +
                        "        \"RU\": 6,\n" +
                        "        \"CY\": 4,\n" +
                        "        \"US\": 2\n" +
                        "    }\n" +
                        "}"));
    }
}

package wisebits.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Map;

@Data
@AllArgsConstructor
public class CounterInfoResponse {
    private Map<String, Long> info;
}

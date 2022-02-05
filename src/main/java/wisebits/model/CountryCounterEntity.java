package wisebits.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "country_counter_info")
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class CountryCounterEntity {
    @Id
    private String id = UUID.randomUUID().toString();
    @EqualsAndHashCode.Exclude
    private String country;
}

package NTTDATA.mscustomer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;

@Document("customerTypeTwo")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerTypeTwo {
    @Id
    private String id;

    @NotEmpty
    private String typeTwo;
}

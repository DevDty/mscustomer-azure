package NTTDATA.mscustomer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Document("customer")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    private String id;

    @NotEmpty
    private String dni;

    @NotEmpty
    private String name;

    @NotEmpty
    private String lastname;

    private String email;

    private String phone;

    private String address;

    @Valid
    private CustomerType customerType;


}

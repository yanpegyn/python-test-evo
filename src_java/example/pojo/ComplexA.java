package example.pojo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ComplexA {
    private Long atibuteA;
    private String atibuteB;
    private Double atibuteC;
    private LocalDateTime atibuteD;
    private TypeA atributeTypeA1;
    private TypeA atributeTypeA2;
}

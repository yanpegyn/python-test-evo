package example.core;

import example.interfaces.ExtendedIntegration;
import example.pojo.ComplexA;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.time.LocalDate;


@RequiredArgsConstructor
public class CoreA {
    private final ExtendedIntegration extendedIntegration;
    public List<String> methodA() {
        try {
            System.out.println("Iniciando Gravacao");
            LocalDate localDate = LocalDate.now();
            List<ComplexA> listComplexA = extendedIntegration.findAllForDate(localDate);

            if(Objects.nonNull(listComplexA) && !listComplexA.isEmpty()) {
                List<String> res = new ArrayList<>();
                listComplexA.parallelStream().forEach(complex -> {
                    res.add(String.valueOf(complex.getAtributeTypeA1().getAtibuteA()));
                    res.add(String.valueOf(complex.getAtributeTypeA2().getAtibuteB()));
                });
                return res;
            }
        } catch (Throwable t) {
            System.out.println("Erro");
            System.out.println(t.getMessage());
            throw new RuntimeException(t);
        }
        return null;
    }
}

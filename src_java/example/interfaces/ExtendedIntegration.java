package example.interfaces;

import example.pojo.ComplexA;

import java.time.LocalDate;
import java.util.List;

public interface ExtendedIntegration extends ConsultaIntegration<ComplexA, Long> {
    List<ComplexA> findAllForDate(LocalDate date);
}

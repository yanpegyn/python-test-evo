package example.interfaces;

import java.util.List;
import java.util.Optional;

public interface ConsultaIntegration<T,ID> {
    List<T> findAll();

    Optional<T> findById(ID id);

    boolean existsById(ID id);
}

package saude.api.api.repository;

import saude.api.api.model.Sono;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SonoRepository extends JpaRepository<Sono, Long> {
}

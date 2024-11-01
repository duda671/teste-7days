package saude.api.api.repository;

import saude.api.api.model.Refeicao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefeicaoRepository extends JpaRepository<Refeicao, Long> {
}

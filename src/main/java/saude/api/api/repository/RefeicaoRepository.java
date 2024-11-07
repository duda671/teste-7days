package saude.api.api.repository;

import saude.api.api.model.Refeicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RefeicaoRepository extends JpaRepository<Refeicao, Long> {
    @Query("SELECT AVG(r.quantidade) FROM Refeicao r")
    Double findAverageCalories();
}

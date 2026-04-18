package desafio.itau.repository;

import desafio.itau.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    List<Transaction> findByDataHoraAfter(OffsetDateTime dataHoraLimite);

    @Query("SELECT SUM(t.valor) FROM Transaction t WHERE t.dataHora > :dataLimite")
    Double sumValorByDataHoraAfter(OffsetDateTime dataLimite);

    // VALOR MÍNIMO nos últimos 30 segundos
    @Query("SELECT MIN(t.valor) FROM Transaction t WHERE t.dataHora > :dataLimite")
    Double minValorByDataHoraAfter(OffsetDateTime dataLimite);

    // VALOR MÁXIMO nos últimos 30 segundos
    @Query("SELECT MAX(t.valor) FROM Transaction t WHERE t.dataHora > :dataLimite")
    Double maxValorByDataHoraAfter(OffsetDateTime dataLimite);

    // MÉDIA (Average) nos últimos 30 segundos
    @Query("SELECT AVG(t.valor) FROM Transaction t WHERE t.dataHora > :dataLimite")
    Double avgValorByDataHoraAfter(OffsetDateTime dataLimite);
}

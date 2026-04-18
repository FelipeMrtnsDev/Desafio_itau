package desafio.itau.repository;

import desafio.itau.model.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.OffsetDateTime;
import java.util.UUID;

public interface StatisticRepository extends JpaRepository<Statistic, UUID> {
}

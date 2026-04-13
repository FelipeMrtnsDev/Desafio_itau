package desafio.itau.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.time.OffsetDateTime;
import java.util.UUID;


@Entity
@Table(name = "tb_Transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(Types.VARCHAR)
    @Column(length = 36, columnDefinition = "varchar(36)")
    private UUID id;

    @NotNull
    @Min(1)
    private double valor;

    @NotNull
    private OffsetDateTime dataHora;

    public Transaction() {
    }

    public Transaction(double valor, OffsetDateTime dataHora, UUID id) {
        this.id = id;
        this.valor = valor;
        this.dataHora = dataHora;
    }

    public UUID getId() {
        return id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public OffsetDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(OffsetDateTime dataHora) {
        if(dataHora.isAfter(OffsetDateTime.now())) {
            throw new IllegalArgumentException("Data do passado não sao permitidas.");
        }
        this.dataHora = dataHora;
    }


}

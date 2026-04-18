package desafio.itau.controller;

import desafio.itau.model.Transaction;
import desafio.itau.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final  transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getTransactionById(@PathVariable UUID id) {
        Optional<Transaction> busca = transactionService.getById(id);

        System.out.println("busca" + busca);

        if (busca.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Erro: Transação com o ID " + id + " não encontrada.");
        }

        // 3. Se chegou aqui, é porque encontrou!
        // Usamos o .get() para tirar a Transaction de dentro da caixinha
        Transaction transacaoEncontrada = busca.get();
        return ResponseEntity.ok(transacaoEncontrada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTransaction(@PathVariable UUID id) {
        boolean deletado = transactionService.delete(id);

        if (deletado) {
            // Retorna 204 No Content (sucesso, mas sem corpo, comum em deletes)
            // Ou mantenha o .ok().build() se preferir o 200
            return ResponseEntity.noContent().build();
        } else {
            // Retorna 404 se o ID não existir
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Erro: Não foi possível deletar. ID " + id + " não encontrado.");
        }
    }

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
        Transaction novaTransacao = transactionService.create(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaTransacao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable UUID id, @RequestBody Transaction transactionDetails) {
        Optional<Transaction> busca = transactionService.getById(id);

        if (busca.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Transaction transacaoExistente = busca.get();
        transacaoExistente.setValor(transactionDetails.getValor());
        transacaoExistente.setDataHora(transactionDetails.getDataHora());
        Transaction transacaoAtualizada = transactionService.create(transacaoExistente);

        return ResponseEntity.status(HttpStatus.OK).body(transacaoAtualizada);
    }
}

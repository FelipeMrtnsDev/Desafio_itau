package desafio.itau.service;

import desafio.itau.model.Transaction;
import desafio.itau.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getAll() {
        return transactionRepository.findAll();
    }

    public Transaction create(Transaction transaction) {
        // O save() preenche o campo ID que estava nulo
        return transactionRepository.save(transaction);
    }

    public Optional<Transaction> getById(UUID id) {
        return transactionRepository.findById(id);
    }

    public boolean delete(UUID id) {
        if (transactionRepository.existsById(id)) {
            transactionRepository.deleteById(id);
            return true; // Deletou com sucesso
        }
        return false; // Não encontrou o ID
    }

}

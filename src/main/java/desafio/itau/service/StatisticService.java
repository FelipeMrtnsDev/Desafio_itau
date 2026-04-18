package desafio.itau.service;

import desafio.itau.model.Statistic;
import desafio.itau.model.Transaction;
import desafio.itau.repository.StatisticRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StatisticService {

    private final StatisticRepository statisticRepository;
    private final TransactionService transactionService;

    public StatisticService(StatisticRepository statisticRepository, TransactionService transactionService) {
        this.statisticRepository = statisticRepository;
        this.transactionService = transactionService;
    }

    public Statistic getStatistics60SecBefore() {
        List<Transaction> transactionList60SecBefore = transactionService.getTransactionsLast60Sec();

        int count = transactionList60SecBefore.size();
        double min = transactionService.getMinValor();
        double max = transactionService.getMaxValor();
        double avg =  transactionService.getAvgValor();
        double sum = transactionService.getSumValor();

        Statistic novaStatistic = new Statistic();

        novaStatistic.setMin(min);
        novaStatistic.setMax(max);
        novaStatistic.setAvg(avg);
        novaStatistic.setSum(sum);
        novaStatistic.setCount(count);

        return statisticRepository.save(novaStatistic);
    }

}

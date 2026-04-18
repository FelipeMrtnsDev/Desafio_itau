package desafio.itau.controller;

import desafio.itau.model.Statistic;
import desafio.itau.service.StatisticService;
import org.hibernate.stat.Statistics;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistc")
public class StatisticsController {

    private final StatisticService statisticService;

    public StatisticsController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @GetMapping
    public ResponseEntity<Statistics> getStatistics() {
        Statistic busca = statisticService.getStatistics60SecBefore();

        if(busca.isEmpt) {

        }

        return ResponseEntity.ok().build();
    }
}

package com.example.demo.jobs;

import com.example.demo.model.repository.GamerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@EnableScheduling

public class GamerJob {

    private final GamerRepository gamerRepository;

    @Scheduled(cron = "${timer.duration.value}")
//    @Scheduled(fixedDelay = 5000)
    public void deleteInactiveGamers () {

//        List<Gamer> gamers = gamerRepository.findAllByStatus(GamerStatus.DELETED);
//        gamerRepository.deleteAll(gamers);

        log.info("Scheduler is working");

    }

}

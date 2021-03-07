package com.example.healthcheck.task;

import java.time.Duration;

import javax.annotation.PostConstruct;

import com.example.healthcheck.service.CheckServerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@Component
public class CheckServersTask {

  @Autowired
  private CheckServerService checkServerService;

  Logger logger = LoggerFactory.getLogger(CheckServersTask.class);
  
  @PostConstruct
  public void updateServerStatuses() {
    Mono.just(1).repeat()
      .delayElements(Duration.ofMinutes(1))
      .concatMap(ignore -> {
        logger.info("Update status on all servers");

        return checkServerService
          .updateStatusForAllServers()
          .doOnSuccess(success -> {
            logger.info(String.format("Status updated on all servers"));
          })
          .onErrorResume(error -> {
            logger.error("Error happened while updating servers statuses", error);
            return Mono.empty();
          });
      })
      .subscribe();
  }
}

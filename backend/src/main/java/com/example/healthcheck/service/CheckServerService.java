package com.example.healthcheck.service;

import java.time.LocalDateTime;

import com.example.healthcheck.model.MonitorableServer;
import com.example.healthcheck.repository.MonitorableServerRepository;
import com.example.healthcheck.types.MonitorableServerStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CheckServerService {

  @Autowired
  private MonitorableServerRepository monitorableServerRepository;

  private WebClient webClient = WebClient.create();

  public Mono<MonitorableServer> updateStatusForServer (MonitorableServer server) {
    String url = server.getUrl();

    return this.webClient
      .get()
      .uri(url)
      .exchangeToMono(response -> {
        return Mono.just(response.statusCode().isError());
      })
      .onErrorResume(e -> {
        return Mono.just(true);
      })
      .flatMap(isError -> {
        server.setLastChecked(LocalDateTime.now());

        if (isError) {
          server.setStatus(MonitorableServerStatus.NOT_AVAILABLE);
        } else {
          server.setStatus(MonitorableServerStatus.AVAILABLE);
        }

        return monitorableServerRepository.save(server);
      });
  }

  public Mono<Void> updateStatusForAllServers () {
    Flux<MonitorableServer> servers = monitorableServerRepository.findAll();

    return servers
      .flatMap(this::updateStatusForServer, 10)
      .then();
  }
}

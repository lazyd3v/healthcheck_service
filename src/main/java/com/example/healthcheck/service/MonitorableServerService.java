package com.example.healthcheck.service;

import java.time.LocalDateTime;

import com.example.healthcheck.repository.MonitorableServerRepository;
import com.example.healthcheck.model.MonitorableServer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class MonitorableServerService {

  @Autowired
  private MonitorableServerRepository monitorableServerRepository;

  @Autowired
  private CheckServerService checkServerService;

  public Mono<MonitorableServer> addNewServer(MonitorableServer server) {
    server.setCreatedAt(LocalDateTime.now());
    server.setUpdatedAt(LocalDateTime.now());

    return monitorableServerRepository
      .save(server)
      .doOnSuccess(success -> {
        checkServerService.updateStatusForServer(server).subscribe();
      });
  }

  public Mono<MonitorableServer> updateServer(MonitorableServer server, Long serverId) {
    server.setId(serverId);
    server.setUpdatedAt(LocalDateTime.now());

    return monitorableServerRepository.save(server);
  }
}

package com.example.healthcheck.resource;

import com.example.healthcheck.model.MonitorableServer;
import com.example.healthcheck.repository.MonitorableServerRepository;
import com.example.healthcheck.service.MonitorableServerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/servers")
public class MonitorableServerResource {

  @Autowired
  private MonitorableServerRepository monitorableServerRepository;

  @Autowired
  private MonitorableServerService monitorableServerService;

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Flux<MonitorableServer> getAllBooks() {
    return monitorableServerRepository.findAll();
  }

  @GetMapping(value = "/{id}")
  public Mono<MonitorableServer> findById(@PathVariable Long id) {
    return monitorableServerRepository.findById(id);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public Mono<MonitorableServer> addNewServer(@RequestBody MonitorableServer server) {
    return monitorableServerService.addNewServer(server);
  }

  @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/{id}")
  public Mono<MonitorableServer> updateServer(@RequestBody MonitorableServer serverDetails, @PathVariable Long serverId) {
    return monitorableServerService.updateServer(serverDetails, serverId);
  }

  @DeleteMapping(value = "/{id}")
  public Mono<Void> delete(@PathVariable Long id) {
      return monitorableServerRepository.deleteById(id);
  }
}

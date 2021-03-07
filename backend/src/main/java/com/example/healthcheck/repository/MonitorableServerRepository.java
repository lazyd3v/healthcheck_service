package com.example.healthcheck.repository;

import com.example.healthcheck.model.MonitorableServer;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonitorableServerRepository extends R2dbcRepository<MonitorableServer, Long> {
}

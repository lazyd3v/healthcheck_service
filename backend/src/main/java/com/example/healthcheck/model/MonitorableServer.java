package com.example.healthcheck.model;

import java.time.LocalDateTime;

import com.example.healthcheck.types.MonitorableServerStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("monitorable_server")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonitorableServer {

    @Id
    @JsonProperty(access = Access.READ_ONLY)
    private Long id;

    @Column(value = "name")
    private String name;

    @Column(value = "url")
    private String url;

    @Column(value = "created_at")
    @JsonProperty(access = Access.READ_ONLY)
    private LocalDateTime createdAt;

    @Column(value = "updated_at")
    @JsonProperty(access = Access.READ_ONLY)
    private LocalDateTime updatedAt;

    @Column(value = "last_checked")
    @JsonProperty(access = Access.READ_ONLY)
    private LocalDateTime lastChecked;

    @Column(value = "status")
    @JsonProperty(access = Access.READ_ONLY)
    private MonitorableServerStatus status;
}

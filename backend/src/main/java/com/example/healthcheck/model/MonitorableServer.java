package com.example.healthcheck.model;

import java.time.LocalDateTime;

import com.example.healthcheck.types.MonitorableServerStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.NotBlank;

@Table("monitorable_server")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonitorableServer {

    @Id
    @JsonProperty(access = Access.READ_ONLY)
    private Long id;

    @Column(value = "name")
    @NotBlank
    private String name;

    @Column(value = "url")
    @Pattern(regexp = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]")
    private String url;

    @Column(value = "created_at")
    @JsonProperty(access = Access.READ_ONLY)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;

    @Column(value = "updated_at")
    @JsonProperty(access = Access.READ_ONLY)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedAt;

    @Column(value = "last_checked")
    @JsonProperty(access = Access.READ_ONLY)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime lastChecked;

    @Column(value = "status")
    @JsonProperty(access = Access.READ_ONLY)
    private MonitorableServerStatus status;
}

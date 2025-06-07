package com.nitin.springboot_todo_app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Configurations {

    @Id
    @Column(length = 36)
    private String id = UUID.randomUUID().toString();
    @NotBlank(message = "config_key must not be empty")
    private String config_key;
    @NotBlank(message = "config_value must not be empty")
    private String config_value;

    @Column(name = "archived", nullable = false)
    private Boolean archived = false;

    @Column(name = "created_on")
    private LocalDateTime created_on = LocalDateTime.now();
    @Column(name = "last_updated_by")
    private String last_updated_by;

    @Column(name = "last_updated_on")
    private LocalDateTime lastUpdatedOn;


    public Configurations(){}

    public Configurations(String id, String config_key, String config_value, Boolean archived, LocalDateTime created_on, String last_updated_by, LocalDateTime lastUpdatedOn) {
        this.id = id;
        this.config_key = config_key;
        this.config_value = config_value;
        this.archived = archived;
        this.created_on = created_on;
        this.last_updated_by = last_updated_by;
        this.lastUpdatedOn = lastUpdatedOn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @NonNull
    public Boolean getArchived() {
        return archived;
    }

    public void setArchived(@NonNull Boolean archived) {
        this.archived = archived;
    }

    @NonNull
    public String getConfig_key() {
        return config_key;
    }

    public void setConfig_key(@NonNull String config_key) {
        this.config_key = config_key;
    }

    @NonNull
    public String getConfig_value() {
        return config_value;
    }

    public void setConfig_value(@NonNull String config_value) {
        this.config_value = config_value;
    }

    public String getLast_updated_by() {
        return last_updated_by;
    }

    public void setLast_updated_by(String last_updated_by) {
        this.last_updated_by = last_updated_by;
    }

    public LocalDateTime getLastUpdatedOn() {
        return lastUpdatedOn;
    }

    public void setLastUpdatedOn(LocalDateTime lastUpdatedOn) {
        this.lastUpdatedOn = lastUpdatedOn;
    }

    public LocalDateTime getCreated_on() {
        return created_on;
    }

    public void setCreated_on(LocalDateTime created_on) {
        this.created_on = created_on;
    }
}

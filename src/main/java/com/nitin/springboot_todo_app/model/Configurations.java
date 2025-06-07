package com.nitin.springboot_todo_app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import org.springframework.lang.NonNull;

@Entity
public class Configurations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "config_key must not be empty")
    private String config_key;
    @NotBlank(message = "config_value must not be empty")
    private String config_value;

    private Boolean archived = false;

    public Configurations(){}

    public Configurations(Long id, String config_key, String config_value, @NonNull Boolean is_archived) {
        this.id = id;
        this.config_key = config_key;
        this.config_value = config_value;
        this.archived = is_archived;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
}

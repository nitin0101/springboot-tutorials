package com.nitin.springboot_todo_app.controller;

import com.nitin.springboot_todo_app.exception.ResourceNotFoundException;
import com.nitin.springboot_todo_app.model.Configurations;
import com.nitin.springboot_todo_app.repository.ConfigurationsRepository;
import io.swagger.v3.core.util.Json;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/configurations")
public class ConfigurationsController {

    private static final Logger logger = LoggerFactory.getLogger(ConfigurationsController.class);

    @Autowired
    private ConfigurationsRepository configurationsRepository;

    @GetMapping
    public List<Configurations> getConfigs(@RequestParam(name = "isArchived",required = false) Boolean isArchived) {
        if (isArchived == null) {
            logger.info("Fetching all configurations (no archive filter)");
            return configurationsRepository.findAll();
        }
        logger.info("Fetching configurations where isArchived = {}", isArchived);
        return configurationsRepository.findAll().stream()
                .filter(el->el.getArchived().equals(isArchived)).toList();
    }

    @GetMapping("/byid/{id}")
    public Configurations getConfigById(@PathVariable Long id) {
        return configurationsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Configuration not found with id " + id));
    }

    @GetMapping("/byconfigkey/{configKey}")
    public Configurations getConfigByKeyName(@PathVariable String configKey) {
        return configurationsRepository
                .findAll()
                .stream()
                .filter(el->el.getConfig_key().toLowerCase().equals(configKey))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Configuration not found with configKey " + configKey));
    }


    @PutMapping("")
    public Configurations createConfig(@Valid @RequestBody Configurations configurations){

        Optional<Configurations> configurations1 = configurationsRepository
                .findAll()
                .stream()
                .filter(el -> el.getConfig_key().toLowerCase().equals(configurations.getConfig_key()))
                .findFirst();

     if(configurations1.isPresent()){
         throw new RuntimeException("Config key already exists");
     }
     configurations.setLast_updated_by("Nitin m");
     configurations.setLastUpdatedOn(LocalDateTime.now());
        return configurationsRepository.save(configurations);
    }

    @DeleteMapping
    public void deleteAll(){
        configurationsRepository.deleteAll();
    }
    @PatchMapping("/{config_key}")
    public ResponseEntity<String> updateArchiveStatus(
            @PathVariable("config_key") String configKey,
            @RequestParam(name = "archive",defaultValue = "true") boolean archive
    ){

        Optional<Configurations> optionalConfigurations = configurationsRepository
                .findAll()
                .stream()
                .filter(el->el.getConfig_key().equalsIgnoreCase(configKey))
                .findFirst();


        if (optionalConfigurations.isPresent()){
            Configurations configurations = optionalConfigurations.get();
            configurations.setArchived(archive);
            configurations.setLastUpdatedOn(LocalDateTime.now());
            configurations.setLast_updated_by("Nitin M");
            configurationsRepository.save(configurations);
            logger.info("Config updated: {}", Json.pretty(configurations));
            String status = archive ? "archived" : "unarchived";
            return ResponseEntity.ok("Configuration successfully " + status + ".");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Configuration with key '" + configKey + "' not found.");
        }
    }

}
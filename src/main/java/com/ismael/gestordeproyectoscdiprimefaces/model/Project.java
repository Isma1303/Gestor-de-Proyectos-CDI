package com.ismael.gestordeproyectoscdiprimefaces.model;


import java.time.LocalDateTime;
import java.io.Serializable;
import java.time.format.DateTimeFormatter;


public class Project implements  Serializable {
    private Long id;
    private String name;
    private String owner;
    private String status;
    private LocalDateTime createdAt;
    private String description;

    public Project(){}

    public Project(Long id, String name,  String owner, String status, LocalDateTime createdAt, String description) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.status = status;
        this.createdAt = createdAt;
        this.description = description;
    }

    //getters

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getOwner() {
        return owner;
    }
    public String getStatus() {
        return status;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public String getDescription() {
        return description;
    }

//    setters

    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedAtFormatted() {
        if (createdAt == null) {
            return null;
        }
        return createdAt.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }
}

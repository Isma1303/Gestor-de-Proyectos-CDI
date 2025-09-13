package com.ismael.gestordeproyectoscdiprimefaces.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task implements Serializable{
    private Long id;
    private Long projectId;
    private String title;
    private String priority;
    private LocalDateTime dueDate;
    private Boolean done;
    private String notes;

    public Task(){
    }

    public Task(Long id, Long projectId, String title, String priority, LocalDateTime dueDate, Boolean done, String notes) {
    this.id = id;
    this.projectId = projectId;
    this.title = title;
    this.priority = priority;
    this.dueDate = dueDate;
    this.done = done;
    this.notes = notes;

    }

//getters

    public Long getId(){
    return id;
    }
    public Long getProjectId(){
        return projectId;
    }
    public String getTitle(){
        return title;
    }
    public String getPriority(){
        return priority;
    }
    public LocalDateTime getDueDate(){
        return dueDate;
    }
    public Boolean getDone(){
        return done;
    }
    public String getNotes(){
        return notes;
    }



//setters

    public void setId(Long id){
        this.id = id;
    }
    public void setProjectId(Long projectId){
        this.projectId = projectId;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void setPriority(String priority){
        this.priority = priority;
    }
    public void setDueDate(LocalDateTime dueDate){
        this.dueDate = dueDate;
    }
    public void setDone(Boolean done){
        this.done = done;
    }
    public void setNotes(String notes){
        this.notes = notes;
    }
    public String getDueDateFormatted() {
        if (dueDate == null) {
            return null;
        }
        return dueDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }
}

package com.ismael.gestordeproyectoscdiprimefaces.controller;

import com.ismael.gestordeproyectoscdiprimefaces.model.Project;
import com.ismael.gestordeproyectoscdiprimefaces.model.Task;
import com.ismael.gestordeproyectoscdiprimefaces.repository.ProjectRepository;
import com.ismael.gestordeproyectoscdiprimefaces.repository.TaskRepository;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;
import java.time.ZoneId;
import java.util.Date;

@Named
@ViewScoped
public class TaskController implements Serializable {
    @Inject
    TaskRepository repo;
    @Inject
    ProjectRepository  projectRepo;

    private List<Task> tasks;
    private List<Project> projects;
    private Task selected;
    private boolean editMode;
    private Date calendarDueDate;

    @PostConstruct
    public void init(){
        tasks = repo.findAll();
        projects = projectRepo.findAll();
        selected = new Task();
        editMode = false;
        calendarDueDate = null;
    }

    public void prepareCreate() {
        selected = new Task();
        editMode = false;
        calendarDueDate = null;
    }

    public void prepareEdit(Task t) {
        selected = new Task(t.getId(), t.getProjectId(), t.getTitle(), t.getPriority(), t.getDueDate(), t.getDone(), t.getNotes());
        editMode = true;
        if (selected.getDueDate() != null) {
            calendarDueDate = Date.from(selected.getDueDate().atZone(ZoneId.systemDefault()).toInstant());
        } else {
            calendarDueDate = null;
        }
    }

    public void save() {
        if (calendarDueDate != null) {
            selected.setDueDate(calendarDueDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        } else {
            selected.setDueDate(null);
        }
        repo.save(selected);
        tasks = repo.findAll();
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, editMode ? "Tarea ctualizada" : "Tarea creada",
                        selected.getTitle()));
    }

    public void delete(Task t) {
        repo.deleteById(t.getId());
        tasks = repo.findAll();
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_WARN, "Tarea eliminada", t.getTitle()));
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public Task getSelected() {
        return selected;
    }

    public boolean isEditMode() {
        return editMode;
    }

    public Date getCalendarDueDate() {
        return calendarDueDate;
    }

    public void setCalendarDueDate(Date calendarDueDate) {
        this.calendarDueDate = calendarDueDate;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public String getProjectNameById(Long projectId) {
        return projects.stream()
                .filter(p -> p.getId().equals(projectId))
                .map(Project::getName)
                .findFirst()
                .orElse("N/A");
    }


}

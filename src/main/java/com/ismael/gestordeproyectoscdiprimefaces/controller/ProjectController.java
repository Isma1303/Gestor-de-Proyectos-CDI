package com.ismael.gestordeproyectoscdiprimefaces.controller;

import com.ismael.gestordeproyectoscdiprimefaces.model.Project;
import com.ismael.gestordeproyectoscdiprimefaces.repository.ProjectRepository;
import com.ismael.gestordeproyectoscdiprimefaces.service.ProjectService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Named
@ViewScoped
public class ProjectController implements Serializable {
    @Inject
    ProjectRepository repo;

    private List<Project> projects;
    private Project selected;
    private boolean editMode;
    private Date calendarCreatedAt;
    private String query;

    @Inject
    ProjectService projectService;

    @PostConstruct
    public void init(){
        projects = projectService.list();
        selected = new Project();
        editMode = false;
        calendarCreatedAt = null;
        query = "";
    }
    public void prepareCreate() {
        selected = new Project();
        editMode = false;
        calendarCreatedAt = null;
    }

    public void prepareEdit(Project p) {
        selected = new Project(p.getId(), p.getName(), p.getOwner(), p.getStatus(), p.getCreatedAt(), p.getDescription());
        editMode = true;
        if (selected != null && selected.getCreatedAt() != null) {
            this.calendarCreatedAt = Date.from(selected.getCreatedAt().truncatedTo(ChronoUnit.MILLIS).atZone(ZoneId.systemDefault()).toInstant());
        }
    }

    public void save() {
        if (calendarCreatedAt != null) {
            selected.setCreatedAt(calendarCreatedAt.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().truncatedTo(ChronoUnit.MILLIS));
        } else {
            selected.setCreatedAt(null);
        }
        repo.save(selected);
        projects = projectService.list();
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, editMode ? "Proyecto actualizado" : "Proyecto creado",
                        selected.getName()));
    }

    public void delete(Project p) {
        repo.deleteById(p.getId());
        projects = projectService.list();
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_WARN, "Proyecto eliminao", p.getName()));
    }

    public void search() {
        projects = projectService.filterByName(query);
    }

    public List<Project> getProjects() {
        return projects;
    }

    public Project getSelected() {
        return selected;
    }

    public boolean isEditMode() {
        return editMode;
    }

    public Date getCalendarCreatedAt() {
        return calendarCreatedAt;
    }

    public void setCalendarCreatedAt(Date calendarCreatedAt) {
        this.calendarCreatedAt = calendarCreatedAt;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}

package com.ismael.gestordeproyectoscdiprimefaces.controller;

import com.ismael.gestordeproyectoscdiprimefaces.model.Project;
import com.ismael.gestordeproyectoscdiprimefaces.service.CatalogService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class CatalogController implements Serializable {

    @Inject
    CatalogService catalogService;

    private List<Project> projects;
    private String query;

    @PostConstruct
    public void init() {
        projects = catalogService.list();
    }

    public void search() {
        projects = catalogService.filterByName(query);
    }




    public List<Project> getProjects() {
        return projects;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
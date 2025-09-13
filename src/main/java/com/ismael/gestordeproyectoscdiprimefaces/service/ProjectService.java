package com.ismael.gestordeproyectoscdiprimefaces.service;

import com.ismael.gestordeproyectoscdiprimefaces.model.Project;
import com.ismael.gestordeproyectoscdiprimefaces.repository.ProjectRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;


@ApplicationScoped

public class ProjectService {
    @Inject
    ProjectRepository repo;

    public List<Project> list() {
        return repo.findAll();
    }

    public List<Project> filterByName(String query) {
        if (query == null || query.isBlank()) return list();
        final String q = query.toLowerCase();
        return list().stream()
                .filter(p -> p.getName().toLowerCase().contains(q) || p.getOwner().toLowerCase().contains(q))
                .collect(Collectors.toList());
    }
}

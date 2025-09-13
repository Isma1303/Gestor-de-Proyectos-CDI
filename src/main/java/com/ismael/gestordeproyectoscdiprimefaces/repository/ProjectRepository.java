package com.ismael.gestordeproyectoscdiprimefaces.repository;

import com.ismael.gestordeproyectoscdiprimefaces.model.Project;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
public class ProjectRepository {
    private final Map<Long, Project> data =new ConcurrentHashMap<>();
    private final AtomicLong seq =new AtomicLong(0);


    @PostConstruct
    public void seed(){
        save(new Project(
                null,
                "Proyecto Desarrollo",
                "Ismael Liquez",
                "ACTIVE",
                LocalDateTime.now(),
                "Proyecto desarrollo web"
        ));

        save(new Project(
                null,
                "Proyecto Móvil",
                "Ana Martínez",
                "IN_PROGRESS",
                LocalDateTime.now().minusDays(5),
                "App para gestión de tareas"
        ));

        save(new Project(
                null,
                "Proyecto Infraestructura",
                "Carlos López",
                "ON_HOLD",
                LocalDateTime.now().minusMonths(1),
                "Migración a la nube"
        ));
    }

    public List<Project> findAll(){
        ArrayList<Project> list = new ArrayList<>(data.values());
        list.sort(Comparator.comparing(Project::getId));
        return list;
    }
    public Optional<Project> findById(Long id) {
        return Optional.ofNullable(data.get(id));
    }

    public Project save(Project p) {
        if (p.getId() == null) p.setId(seq.incrementAndGet());
        data.put(p.getId(), p);
        return p;
    }
    public void deleteById(Long id) {
        data.remove(id);
    }

}

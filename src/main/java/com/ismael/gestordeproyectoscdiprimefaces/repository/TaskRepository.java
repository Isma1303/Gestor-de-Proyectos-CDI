package com.ismael.gestordeproyectoscdiprimefaces.repository;

import com.ismael.gestordeproyectoscdiprimefaces.model.Task;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
public class TaskRepository {

    private final Map<Long, Task> data = new ConcurrentHashMap<>();
    private final AtomicLong seq = new AtomicLong(0);

    @PostConstruct
    void seed(){
        save(new Task(
                null,
                1L,
                "Crear UI",
                "MEDIUM",
                LocalDateTime.now().plusDays(3),
                false,
                "Crear una interfaz elegante y responsiva"
        ));

        save(new Task(
                null,
                1L,
                "Optimizar Código",
                "LOW",
                LocalDateTime.now().plusWeeks(1),
                false,
                "Refactorizar y mejorar la legibilidad del código"
        ));

        save(new Task(
                null,
                1L,
                "Realizar tareas internas",
                "HIGH",
                LocalDateTime.now().plusDays(1),
                false,
                "Gestión interna del proyecto y documentación"
        ));
    }

    public Task save(Task t) {
        if (t.getId() == null) t.setId(seq.incrementAndGet());
        data.put(t.getId(), t);
        return t;
    }

    public List<Task> findAll(){
        ArrayList<Task> list = new ArrayList<>(data.values());
        list.sort(Comparator.comparing(Task::getId));
        return list;
    }
    public Optional<Task> findById(Long id) {
        return Optional.ofNullable(data.get(id));
    }

    public void deleteById(Long id) {
        data.remove(id);
    }

}

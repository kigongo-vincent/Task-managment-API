package com.tasks.tasks.repositories;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

import com.tasks.tasks.models.Task;

public interface TasksRepository extends ListCrudRepository<Task, Integer> {

    @Query("""
            SELECT * FROM base_task LEFT JOIN base_user ON base_task.author = base_user.id
            """)
    List<Task> getTaskDetails();

}

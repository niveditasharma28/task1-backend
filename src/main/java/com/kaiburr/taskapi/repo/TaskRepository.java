package com.kaiburr.taskapi.repo;

import com.kaiburr.taskapi.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface TaskRepository extends MongoRepository<Task, String> {
    List<Task> findByNameContainingIgnoreCase(String q);
}

package com.kaiburr.taskapi.service;

import com.kaiburr.taskapi.model.Task;
import com.kaiburr.taskapi.model.TaskExecution;
import com.kaiburr.taskapi.repo.TaskRepository;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private final TaskRepository repo;

    public TaskService(TaskRepository repo) {
        this.repo = repo;
    }

    public List<Task> findAll() {
        return repo.findAll();
    }

    public Task save(Task t) {
        if (!isSafeCommand(t.getCommand())) {
            throw new IllegalArgumentException("Unsafe command");
        }
        return repo.save(t);
    }

    public void delete(String id) {
        repo.deleteById(id);
    }

    public TaskExecution executeLocally(String id) throws Exception {
        Task t = repo.findById(id).orElseThrow();
        String cmd = t.getCommand();
        if (!isSafeCommand(cmd)) throw new IllegalArgumentException("Unsafe command");

        Instant start = Instant.now();
        ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", cmd);
        pb.redirectErrorStream(true);
        Process p = pb.start();

        String output;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
            output = br.lines().collect(Collectors.joining("\n"));
        }
        p.waitFor();
        Instant end = Instant.now();

        TaskExecution ex = new TaskExecution(start, end, output);
        t.getExecutions().add(ex);
        repo.save(t);
        return ex;
    }

    private boolean isSafeCommand(String cmd) {
        if (cmd == null) return false;
        if (cmd.matches(".*[;&|`$><()].*")) return false;
        return cmd.trim().matches("^(echo|date|dir|whoami)(\\s+[\\w\\-./]*)*$");
    }
}

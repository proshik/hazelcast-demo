package ru.proshik.prof_of_concept.clerk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.proshik.prof_of_concept.clerk.model.MyTask;
import ru.proshik.prof_of_concept.clerk.service.TaskService;

/**
 * Created by PKrylov on 10.08.2016.
 */
@RestController
@RequestMapping(value = "tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity add(@RequestBody MyTask task) {

        taskService.add(task);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity get() {
        return ResponseEntity.ok(taskService.get());
    }

    @RequestMapping(method = RequestMethod.GET, value = "state", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity stateQueue() {
        return ResponseEntity.ok(taskService.getQueueState());
    }

}

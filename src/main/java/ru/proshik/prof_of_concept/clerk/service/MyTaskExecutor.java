package ru.proshik.prof_of_concept.clerk.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.proshik.prof_of_concept.clerk.exception.TaskException;
import ru.proshik.prof_of_concept.clerk.model.MyTask;

import java.io.Serializable;
import java.util.concurrent.Callable;

/**
 * Created by PKrylov on 11.08.2016.
 */
public class MyTaskExecutor implements Callable<String>, Serializable {

    private static final Logger LOG = LoggerFactory.getLogger(TaskService.class);

    private final MyTask task;

    public MyTaskExecutor(MyTask task) {
        this.task = task;
    }

    @Override
    public String call() throws Exception {
        if (task.getTaskName().equals("FailTask")) {
            LOG.error("Task with name={} generate exception", task.getTaskName());
            throw new TaskException();
        }
        Thread.currentThread().getId();
        LOG.debug("Task with name={} successful executed", task.getTaskName());
        return "success";
    }

}

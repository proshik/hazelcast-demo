package ru.proshik.prof_of_concept.clerk.model;

import java.io.Serializable;

/**
 * Created by PKrylov on 10.08.2016.
 */
public class MyTask implements Serializable {

    private String taskName;

    public MyTask() {
    }

    public MyTask(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyTask myTask = (MyTask) o;

        return taskName != null ? taskName.equals(myTask.taskName) : myTask.taskName == null;

    }

    @Override
    public int hashCode() {
        return taskName != null ? taskName.hashCode() : 0;
    }
}

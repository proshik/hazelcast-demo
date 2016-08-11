package ru.proshik.prof_of_concept.clerk.service;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.proshik.prof_of_concept.clerk.model.MyTask;

/**
 * Created by PKrylov on 10.08.2016.
 */
@Service
public class TaskService {

    public static final String QUEUE_NAME = "task_queue";

    @Autowired
    private HazelcastInstance hazelcastInstance;

    public void add(MyTask task) {
        IQueue<MyTask> queue = hazelcastInstance.getQueue(QUEUE_NAME);
        try {
            queue.put(task);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public MyTask get() {
        IQueue<MyTask> queue = hazelcastInstance.getQueue(QUEUE_NAME);
        return queue.poll();
    }

    public int getQueueState() {
        return hazelcastInstance.getQueue(QUEUE_NAME).size();
    }

}

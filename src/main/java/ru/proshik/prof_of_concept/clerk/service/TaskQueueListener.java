package ru.proshik.prof_of_concept.clerk.service;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;
import ru.proshik.prof_of_concept.clerk.model.MyTask;

/**
 * Created by PKrylov on 11.08.2016.
 */
@Service
public class TaskQueueListener implements ApplicationListener<ContextRefreshedEvent> {

    public static final String QUEUE_NAME = "task_queue";

    @Autowired
    private TaskItemListener taskItemListener;

    @Autowired
    private HazelcastInstance hazelcastInstance;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        IQueue<MyTask> queue1 = hazelcastInstance.getQueue(QUEUE_NAME);
        queue1.addItemListener(taskItemListener, true);
    }

}

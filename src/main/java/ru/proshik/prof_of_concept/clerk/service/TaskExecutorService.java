package ru.proshik.prof_of_concept.clerk.service;

import com.hazelcast.core.ExecutionCallback;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IExecutorService;
import com.hazelcast.core.IQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.proshik.prof_of_concept.clerk.model.MyTask;

/**
 * Created by PKrylov on 11.08.2016.
 */
@Service
public class TaskExecutorService {

    private static final Logger LOG = LoggerFactory.getLogger(TaskExecutorService.class);

    @Autowired
    private HazelcastInstance hazelcastInstance;

    @Autowired
    private IExecutorService executorService;

    void execute() {

        IQueue<MyTask> taskQueue = hazelcastInstance.getQueue("task_queue");

        MyTask task = taskQueue.poll();
        if (task != null) {
            executorService.submit(new MyTaskExecutor(task), new ExecutionCallback<String>() {

                @Override
                public void onResponse(String response) {
                    LOG.debug("Executed  = " + response);
                }

                @Override
                public void onFailure(Throwable t) {
                    LOG.error("Fail executed");
                }
            });
        }
    }

}

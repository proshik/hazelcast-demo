package ru.proshik.prof_of_concept.clerk.service;


import com.hazelcast.core.ItemEvent;
import com.hazelcast.core.ItemListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.proshik.prof_of_concept.clerk.model.MyTask;

/**
 * Created by PKrylov on 11.08.2016.
 */
@Component
public class TaskItemListener implements ItemListener<MyTask> {

    private static final Logger LOG = LoggerFactory.getLogger(TaskItemListener.class);

    @Autowired
    private TaskExecutorService taskExecutorService;


    @Override
    public void itemAdded(ItemEvent<MyTask> item) {
        LOG.debug("Task was added to queue");
        taskExecutorService.execute();
    }

    @Override
    public void itemRemoved(ItemEvent<MyTask> item) {
        LOG.debug("Task was removed from queue");
    }
}

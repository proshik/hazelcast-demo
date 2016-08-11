package ru.proshik.prof_of_concept.clerk.config;

import com.hazelcast.config.Config;
import com.hazelcast.config.ExecutorConfig;
import com.hazelcast.config.QueueConfig;
import com.hazelcast.config.QueueStoreConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IExecutorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.proshik.prof_of_concept.clerk.service.TheQueueStore;

/**
 * Created by PKrylov on 10.08.2016.
 */
@Configuration
public class HazelcastConfig {

    @Bean
    public HazelcastInstance hazelcastInstance() {
        Config config = new Config();
        ExecutorConfig executorConfig = config.getExecutorConfig("exec");
        executorConfig.setPoolSize(1).setQueueCapacity(1)
                .setStatisticsEnabled(true);

        QueueStoreConfig queueStoreConfig = new QueueStoreConfig();
        queueStoreConfig
                .setEnabled(true)
                .setStoreImplementation(new TheQueueStore())
                .setProperty("binary", "false");

        QueueConfig queueConfig = new QueueConfig();
        queueConfig
                .setName("task_queue")
                .setBackupCount(1)
                .setMaxSize(10)
                .setStatisticsEnabled(true);
        queueConfig.setQueueStoreConfig(queueStoreConfig);

        config.addQueueConfig(queueConfig);

        return Hazelcast.newHazelcastInstance(config);
    }

    @Bean
    public IExecutorService iExecutorService() {
        return hazelcastInstance().getExecutorService("exec");
    }

}

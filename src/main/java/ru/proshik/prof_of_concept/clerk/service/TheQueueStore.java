package ru.proshik.prof_of_concept.clerk.service;

import com.hazelcast.core.QueueStore;
import ru.proshik.prof_of_concept.clerk.model.MyTask;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Created by PKrylov on 11.08.2016.
 */
public class TheQueueStore implements QueueStore<MyTask> {

    @Override
    public void store(Long key, MyTask value) {
        System.out.println();
    }

    @Override
    public void storeAll(Map<Long, MyTask> map) {
        System.out.println();
    }

    @Override
    public void delete(Long key) {
        System.out.println();
    }

    @Override
    public void deleteAll(Collection<Long> keys) {
        System.out.println();
    }

    @Override
    public MyTask load(Long key) {
        System.out.println();
        return null;
    }

    @Override
    public Map<Long, MyTask> loadAll(Collection<Long> keys) {
        System.out.println();
        return null;
    }

    @Override
    public Set<Long> loadAllKeys() {
        System.out.println();
        return null;
    }
}

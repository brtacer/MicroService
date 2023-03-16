package com.berat.utility;

import com.berat.repository.entity.BaseEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;

public class ServiceManager<T extends BaseEntity,ID> implements IService<T,ID>{

    private final ElasticsearchRepository<T,ID> elasticsearchRepository;

    public ServiceManager(ElasticsearchRepository<T,ID> elasticsearchRepository){
        this.elasticsearchRepository = elasticsearchRepository;
    }

    @Override
    public T save(T t) {
        long time=System.currentTimeMillis();
        t.setCreateat(time);
        t.setUpdateat(time);
        t.setState(true);
        return elasticsearchRepository.save(t);
    }

    @Override
    public Iterable<T> saveAll(Iterable<T> t) {
        t.forEach(x-> {
            long time=System.currentTimeMillis();
            x.setCreateat(time);
            x.setUpdateat(time);
            x.setState(true);
        });
        return elasticsearchRepository.saveAll(t);
    }

    @Override
    public T update(T t) {
        t.setUpdateat(System.currentTimeMillis());
        return elasticsearchRepository.save(t);
    }

    @Override
    public void delete(T t) {
        elasticsearchRepository.delete(t);
    }

    @Override
    public void deleteById(ID id) {
        elasticsearchRepository.deleteById(id);
    }

    @Override
    public Optional<T> findById(ID id) {
        return elasticsearchRepository.findById(id);
    }

    @Override
    public Iterable<T> findAll() {
        return elasticsearchRepository.findAll();
    }
}

package com.berat.utility;

import com.berat.repository.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public class ServiceManager<T extends BaseEntity,ID> implements IService<T,ID>{

    private final JpaRepository<T,ID> jpaRepository;

    public ServiceManager(JpaRepository<T,ID> jpaRepository){
        this.jpaRepository=jpaRepository;
    }

    @Override
    public T save(T t) {
        long time=System.currentTimeMillis();
        t.setCreateat(time);
        t.setUpdateat(time);
        t.setState(true);
        return jpaRepository.save(t);
    }

    @Override
    public Iterable<T> saveAll(Iterable<T> t) {
        t.forEach(x-> {
            long time=System.currentTimeMillis();
            x.setCreateat(time);
            x.setUpdateat(time);
            x.setState(true);
        });
        return jpaRepository.saveAll(t);
    }

    @Override
    public T update(T t) {
        t.setUpdateat(System.currentTimeMillis());
        return jpaRepository.save(t);
    }

    @Override
    public void delete(T t) {
        jpaRepository.delete(t);
    }

    @Override
    public void deleteById(ID id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public Optional<T> findById(ID id) {
        return jpaRepository.findById(id);
    }

    @Override
    public List<T> findAll() {
        return jpaRepository.findAll();
    }
}

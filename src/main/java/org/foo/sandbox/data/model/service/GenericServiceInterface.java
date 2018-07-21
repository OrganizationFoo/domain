package org.foo.sandbox.data.model.service;

import org.springframework.data.domain.Sort.Direction;

public interface GenericServiceInterface<T, E, I> {

    public Iterable<T> findAll(int page, int size, String field, Direction direction);

    public Iterable<T> findAll(String field, Direction direction);

    public long count();

    public boolean existsById(I id);

    public T findById(I id);

    public Iterable<T> findAll();

    public Iterable<T> findAllById(Iterable<I> ids);

    public T save(T dto);

    public Iterable<T> saveAll(Iterable<? extends T> dtos);

    public void deleteById(I id);

    public void delete(T dto);

    public void deleteAll();

    public void deleteAll(Iterable<? extends T> dtos);
}

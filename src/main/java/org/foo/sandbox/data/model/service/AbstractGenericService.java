package org.foo.sandbox.data.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.repository.PagingAndSortingRepository;

public abstract class AbstractGenericService<T, E, I> implements GenericServiceInterface<T, E, I> {

    private final PagingAndSortingRepository<E, I> repo;
    private final Converter<T, E> toEntity;
    private final Converter<E, T> fromEntity;

    public AbstractGenericService(final PagingAndSortingRepository<E, I> repo, final Converter<T, E> toEntity, final Converter<E, T> fromEntity) {
        this.repo = repo;
        this.toEntity = toEntity;
        this.fromEntity = fromEntity;
    }

    @Override
    public Iterable<T> findAll(int page, int size, String field, Direction direction) {
        Sort sort = new Sort(direction, field);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<E> found = repo.findAll(pageable);
        return fromEntities(found);
    }

    @Override
    public Iterable<T> findAll(String field, Direction direction) {
        Sort sort = new Sort(direction, field);
        Iterable<E> found = repo.findAll(sort);
        return fromEntities(found);
    }

    @Override
    public long count() {
        return repo.count();
    }

    @Override
    public boolean existsById(I id) {
        return repo.existsById(id);
    }

    @Override
    public T findById(I id) {
        return fromOptionalEntity(repo.findById(id));
    }

    @Override
    public Iterable<T> findAll() {
        return fromEntities(repo.findAll());
    }

    @Override
    public Iterable<T> findAllById(Iterable<I> ids) {
        return fromEntities(repo.findAllById(ids));
    }

    @Override
    public T save(T dto) {
        return fromEntity(repo.save(toEntity(dto)));
    }

    @Override
    public Iterable<T> saveAll(Iterable<? extends T> dtos) {
        return fromEntities(repo.saveAll(toEntities(dtos)));
    }

    @Override
    public void deleteById(I id) {
        repo.deleteById(id);
    }

    @Override
    public void delete(T dto) {
        repo.delete(toEntity(dto));
    }

    @Override
    public void deleteAll() {
        repo.deleteAll();
    }

    @Override
    public void deleteAll(Iterable<? extends T> dtos) {
        repo.deleteAll(toEntities(dtos));
    }

    // ------------------------------------------------------------------------

    private E toEntity(T dto) {
        return toEntity.convert(dto);
    }

    private List<E> toEntities(Iterable<? extends T> dtos) {
        List<E> entities = new ArrayList<>();
        for (T dto : dtos) {
            E entity = toEntity.convert(dto);
            entities.add(entity);
        }
        return entities;
    }

    private T fromEntity(E entity) {
        return fromEntity.convert(entity);
    }

    private List<T> fromEntities(Iterable<? extends E> entities) {
        List<T> dtos = new ArrayList<>();
        for (E entity : entities) {
            T dto = fromEntity.convert(entity);
            dtos.add(dto);
        }
        return dtos;
    }

    private T fromOptionalEntity(Optional<E> optionalEntity) {
        T dto = null;
        if (optionalEntity.isPresent()) {
            E entity = optionalEntity.get();
            dto = fromEntity.convert(entity);
        }
        return dto;
    }
}

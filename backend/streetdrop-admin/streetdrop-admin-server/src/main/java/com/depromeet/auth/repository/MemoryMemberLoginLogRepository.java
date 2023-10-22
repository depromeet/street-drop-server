package com.depromeet.auth.repository;

import com.depromeet.auth.entity.MemberLoginLog;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
@Repository
public class MemoryMemberLoginLogRepository implements MemberLoginLogRepository {

    private final List<MemberLoginLog> memberLoginLog = new ArrayList<>();

    @Override
    public void flush() {

    }

    @Override
    public <S extends MemberLoginLog> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends MemberLoginLog> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<MemberLoginLog> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public MemberLoginLog getOne(Long aLong) {
        return null;
    }

    @Override
    public MemberLoginLog getById(Long aLong) {
        return null;
    }

    @Override
    public MemberLoginLog getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends MemberLoginLog> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends MemberLoginLog> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends MemberLoginLog> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends MemberLoginLog> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends MemberLoginLog> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends MemberLoginLog> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends MemberLoginLog, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends MemberLoginLog> S save(S entity) {
        entity.setId(memberLoginLog.size() + 1L);
        memberLoginLog.add(entity);
        return entity;
    }

    @Override
    public <S extends MemberLoginLog> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<MemberLoginLog> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<MemberLoginLog> findAll() {
        return memberLoginLog;
    }

    @Override
    public List<MemberLoginLog> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(MemberLoginLog entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends MemberLoginLog> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<MemberLoginLog> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<MemberLoginLog> findAll(Pageable pageable) {
        return null;
    }
}

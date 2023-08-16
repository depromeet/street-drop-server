package com.depromeet.domains.item.repository;

import com.depromeet.item.Item;
import com.depromeet.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query(value = "select i from Item i join fetch i.song s join fetch s.album a join fetch a.artist ar join fetch i.itemLocation il join fetch il.villageArea va join fetch i.user u",
            countQuery = "select count(i) from Item i")
    Page<Item> findAll(Pageable pageable);

    @Query(value = "select i from Item i join fetch i.song s join fetch s.album a join fetch a.artist ar join fetch i.itemLocation il join fetch il.villageArea va join fetch i.user u where u = :user order by i.createdAt desc ")
    List<Item> findByUser(User user);
}

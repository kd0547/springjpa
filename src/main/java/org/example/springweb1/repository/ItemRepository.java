package org.example.springweb1.repository;

import jakarta.persistence.EntityManager;
import org.example.springweb1.domain.item.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemRepository {

    private final EntityManager em;

    @Autowired
    public ItemRepository(EntityManager em) {
        this.em = em;
    }

    public void save(Item item) {
        if(item.getId() == null) {
            em.persist(item);
        } else {
            //업데이트와 유사하다.
            em.merge(item);
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class,id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i",Item.class)
                .getResultList();
    }






}

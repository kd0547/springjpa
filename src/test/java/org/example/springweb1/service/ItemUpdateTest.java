package org.example.springweb1.service;

import jakarta.persistence.EntityManager;
import org.example.springweb1.domain.item.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ItemUpdateTest {

    @Autowired
    EntityManager em;

    @Test
    public void updateTest() throws Exception {
        //변경 감지
        Book book = em.find(Book.class,1L);
        
    }
}

package com.lsm.boot.jpa.repository;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

public abstract class AbstractBatchRepository<T> {

    @Autowired
    private EntityManager entityManager;

    @Transactional()
    public void batchInsert(List<T> list) {
        Session session = (Session)entityManager.getDelegate();
        session.setFlushMode(FlushMode.MANUAL);
        for (int i = 0; i < list.size(); i++) {
            session.save(list.get(i));
//            entityManager.persist(list.get(i));
            if (i % 25 == 0) {
                session.flush();
                session.clear();
                /*entityManager.flush();
                entityManager.clear();*/
            }
        }
    }
}

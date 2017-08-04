package com.lsm.boot.jpa.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;
import java.util.List;

/**
 * http://blog.csdn.net/xiewenbo/article/details/8574521
 * http://www.cnblogs.com/IcanFixIt/p/7042977.html
 * http://blog.csdn.net/zh921112/article/details/37694257
 * @param <T>
 */
public abstract class AbstractBatchRepository<T> {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Transactional()
    public void batchInsert(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            em.persist(list.get(i));
            if (i % 100 == 0) {
                em.flush();
                em.clear();
            }
        }
    }
}

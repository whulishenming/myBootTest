package com.lsm.boot.jpa.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories("com.lsm.boot.jpa.repository")
@EnableTransactionManagement
public class JpaConfig {

    @Bean
    public EntityManagerFactory entityManagerFactory(@Qualifier("jpaDataSource") DataSource jpaDataSource){
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();

        factory.setDataSource(jpaDataSource);
        factory.setPackagesToScan("com.lsm.boot.jpa.model");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(false);
        vendorAdapter.setShowSql(true);
        factory.setJpaVendorAdapter(vendorAdapter);
        Map<String, String> jpaProperties = new HashMap<>();
        jpaProperties.put("hibernate.jdbc.batch_size", "25");
        jpaProperties.put("hibernate.order_inserts", "true");
        factory.setJpaPropertyMap(jpaProperties);
        factory.afterPropertiesSet();

        return factory.getObject();
    }

    @Bean
    public EntityManager entityManager(@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {

        return entityManagerFactory.createEntityManager();
    }

    @Bean
    public PlatformTransactionManager transactionManager(@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {

        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);

        return txManager;
    }
}

package com.przemo.RestAPI;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
class PersistenceContext {
 
    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, 
                                                                Environment env) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan("com.przemo.RestAPI");
 
        Properties jpaProperties = new Properties();
     
        //Configures the used database dialect. This allows Hibernate to create SQL
        //that is optimized for the used database.
        jpaProperties.put("hibernate.dialect", env.getRequiredProperty("spring.jpa.properties.hibernate.dialect"));
 
        //Specifies the action that is invoked to the database when the Hibernate
        //SessionFactory is created or closed.
        jpaProperties.put("hibernate.hbm2ddl.auto", 
                env.getRequiredProperty("spring.jpa.hibernate.ddl-auto")
        );
 
        jpaProperties.put("hibernate.show_sql", 
                env.getRequiredProperty("spring.jpa.hibernate.show_sql")
        );
        entityManagerFactoryBean.setJpaProperties(jpaProperties);
 
        return entityManagerFactoryBean;
    }
     
    //Add the other beans here
    @Bean(destroyMethod = "close")
    	DataSource dataSource(Environment env) {
        HikariConfig dataSourceConfig = new HikariConfig();
        //dataSourceConfig.setDriverClassName(env.getRequiredProperty("db.driver"));
        dataSourceConfig.setJdbcUrl(env.getRequiredProperty("spring.datasource.url"));
        dataSourceConfig.setUsername(env.getRequiredProperty("spring.datasource.username"));
        dataSourceConfig.setPassword(env.getRequiredProperty("spring.datasource.password"));
 
        return new HikariDataSource(dataSourceConfig);
    }
    
    @Bean(name="transactionManager")
    public PlatformTransactionManager dbTransactionManager() {
        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
       // transactionManager.setEntityManagerFactory(
         //       dbEntityManager().getObject());
        return transactionManager;
    }
}
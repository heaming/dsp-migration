package com.ad.migration.config;


import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.ad.migration.domain.recentad",
        entityManagerFactoryRef = "recentAdEntityManagerFactory",
        transactionManagerRef = "recentAdTransactionManager"
)
public class RecentAdConfig {

    @Primary
    @Bean("recentAdDataSource")
    @ConfigurationProperties(prefix = "spring.jpa.recentad.hikari")
    public DataSource recentDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean("recentAdJpaProperties")
    @ConfigurationProperties(prefix = "spring.jpa.recentad.properties")
    public Properties recentJpaProperties() {
        return new Properties();
    }

    @Primary
    @Bean("recentAdEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean recentAdEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("recentAdDataSource") DataSource dataSource,
            @Qualifier("recentAdJpaProperties") Properties jpaProperties
    ) {
        LocalContainerEntityManagerFactoryBean factoryBean = builder
                .dataSource(dataSource)
                .packages("com.ad.migration.domain.recentad")
                .build();

        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factoryBean.setJpaProperties(jpaProperties);

        return factoryBean;
    }

    @Primary
    @Bean("recentAdTransactionManager")
    public PlatformTransactionManager recentTransactionManager(
            @Qualifier("recentAdEntityManagerFactory") EntityManagerFactory entityManagerFactory
    ) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}

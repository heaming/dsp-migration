package com.ad.migration.gradual.config;


import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.ad.migration.gradual.domain.legacyad",
        entityManagerFactoryRef = "legacyAdEntityManagerFactory",
        transactionManagerRef = "legacyAdTransactionManager"
)
@EnableTransactionManagement
public class LegacyAdConfig {

    @Bean("legacyAdDataSource")
    @ConfigurationProperties(prefix = "spring.jpa.legacyad.hikari")
    public DataSource legacyDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean("legacyAdJpaProperties")
    @ConfigurationProperties(prefix = "spring.jpa.legacyad.properties")
    public Properties legacyJpaProperties() {
        return new Properties();
    }

    @Bean("legacyAdEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean legacyAdEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("legacyAdDataSource") DataSource dataSource,
            @Qualifier("legacyAdJpaProperties") Properties jpaProperties
    ) {
        LocalContainerEntityManagerFactoryBean factoryBean = builder
                .dataSource(dataSource)
                .packages("com.ad.migration.gradual.domain.legacyad")
                .build();

        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factoryBean.setJpaProperties(jpaProperties);

        return factoryBean;
    }

    @Bean("legacyAdTransactionManager")
    public PlatformTransactionManager legacyTransactionManager(
            @Qualifier("legacyAdEntityManagerFactory") EntityManagerFactory entityManagerFactory
    ) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}

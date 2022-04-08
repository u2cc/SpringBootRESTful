//package com.rest.jpa;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(
//        entityManagerFactoryRef = "mySQLEntityManagerFactory",
//        transactionManagerRef = "mySQLTransactionManager",
//        basePackages = {
//                "com.rest.repository"
//        }
//
//)
//public class MySQLConfig {
//    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource.oracle")
//    public DataSource oracleDataSource(){
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "mySQLEntityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("mySQLDataSource") DataSource dataSource){
//        return builder.dataSource(dataSource)
//                .packages("com.rest.entities")
//                .persistenceUnit("mysql")
//                .build();
//    }
//
//    @Bean(name = "mySQLTransactionManager")
//    public PlatformTransactionManager oracleTransactionManager(@Qualifier("mySQLEntityManagerFactory") EntityManagerFactory mySQLEntityManagerFactory){
//        return new JpaTransactionManager(mySQLEntityManagerFactory);
//    }
//}

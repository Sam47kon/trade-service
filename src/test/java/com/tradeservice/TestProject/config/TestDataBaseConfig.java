package com.tradeservice.TestProject.config;

import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan("com.tradeservice.TestProject")
public class TestDataBaseConfig { // FIXME idk wtf


  private static final String PROPERTY_NAME_DATABASE_DRIVER = "org.h2.Driver";
  private static final String PROPERTY_NAME_DATABASE_URL = "jdbc:h2:mem:testdb;MODE=Oracle;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE";
  private static final String PROPERTY_NAME_DATABASE_USERNAME = "sam";
  private static final String PROPERTY_NAME_DATABASE_PASSWORD = "4747";

  private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "org.hibernate.dialect.H2Dialect";
  private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "true";
  private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "com.tradeservice.TestProject.entities";
  private static final String PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO = "create-drop";

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
    entityManagerFactoryBean.setDataSource(dataSource());
    entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
    entityManagerFactoryBean.setPackagesToScan(PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN);

    entityManagerFactoryBean.setJpaProperties(hibernateProp());

    return entityManagerFactoryBean;
  }

  @Bean
  public JpaTransactionManager transactionManager() {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

    return transactionManager;
  }

  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();

    dataSource.setDriverClassName(PROPERTY_NAME_DATABASE_DRIVER);
    dataSource.setUrl(PROPERTY_NAME_DATABASE_URL);
    dataSource.setUsername(PROPERTY_NAME_DATABASE_USERNAME);
    dataSource.setPassword(PROPERTY_NAME_DATABASE_PASSWORD);

    return dataSource;
  }

  private Properties hibernateProp() {
    Properties properties = new Properties();
    properties.put("hibernate.dialect", PROPERTY_NAME_HIBERNATE_DIALECT);
    properties.put("hibernate.show_sql", PROPERTY_NAME_HIBERNATE_SHOW_SQL);
    properties.put("hibernate.hbm2ddl.auto", PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO);
    return properties;
  }
}

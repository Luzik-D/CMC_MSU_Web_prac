package ru.msu.cmc.web_prac.video_rental.cfg;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class HibernateCfg {
    /*
     * Init dataSources
     */
    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/video_rental");
        dataSource.setUsername("root");
        dataSource.setPassword((""));

        return dataSource;
    }

    /*
     * Init hibernate properties
     */
    private Properties getHibernateProperties() {
        Properties properties = new Properties();

        properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");

        return properties;
    }

    /*
     * Init session factory
     */
    @Bean
    public LocalSessionFactoryBean setSessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

        sessionFactory.setDataSource(getDataSource());
        sessionFactory.setPackagesToScan(new String[] {"ru.msu.cmc.web_prac.video_rental"});
        sessionFactory.setHibernateProperties(getHibernateProperties());

        return sessionFactory;
    }
}

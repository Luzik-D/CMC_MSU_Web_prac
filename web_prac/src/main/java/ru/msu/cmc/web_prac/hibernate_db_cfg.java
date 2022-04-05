@Configuration
@PropertySource("classpath:application.properties")
public class HibernateDBCfg {
    @Value("org.postgresql.Driver")
    private String DB_DRIVER;
    @Value("jdbc:postgresql://localhost/video_rental")
    private String DB_URL;
    @Value("postgres")
    private String DB_USERNAME;
    @Value("110921")
    private String DB_PASSWORD;

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(oraDataSource());
        sessionFactory.setPackagesToScan("web_prac.tables");

        Properties hibProperties = new Properties();
        hibProperties.setProperty("hibernate.hbm2ddl.auto", "update");
        hibProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL11Dialect");
        hibProperties.setProperty("connection_pool_size", "1");

        sessionFactory.setHibernateProperties(hibProperties);

        return sessionFactory;
    }

    @Bean
    public DataSource oraDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(DB_DRIVER);
        dataSource.setUrl(DB_URL);
        dataSource.setUsername(DB_USERNAME);
        dataSource.setPassword(DB_PASSWORD);

        return dataSource;
    }
}
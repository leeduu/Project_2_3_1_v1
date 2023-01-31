package web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.*;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

@Configuration
@ComponentScan("web")
@EnableWebMvc   // равноценно тегу <mvc:annotation-driven/> в xml
@EnableTransactionManagement

public class WebConfig implements WebMvcConfigurer { //равноценно отдельному файлу xml

    private final ApplicationContext applicationContext;

    public WebConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/");
        templateResolver.setSuffix(".html");
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        registry.viewResolver(resolver);
    }

//    @Bean
//    public EntityManagerFactory entityManagerFactory(DataSource dataSource) {
//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        vendorAdapter.setGenerateDdl(true);
//        vendorAdapter.setShowSql(true);
//        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//        em.setDataSource(dataSource());
//        em.setJpaVendorAdapter(vendorAdapter);
//        em.setPackagesToScan("web.model");
//        em.afterPropertiesSet();
//        return em.getObject();
//    }

//    @Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(environment.getProperty("db.driver"));
//        dataSource.setUrl(environment.getProperty("db.url"));
//        dataSource.setUsername(environment.getProperty("db.username"));
//        dataSource.setPassword(environment.getProperty("db.password"));
//        return dataSource;
//    }

//    @Bean
//    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(entityManagerFactory);
//        return transactionManager;
//    }
//
//    @Bean
//    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
//        return new PersistenceExceptionTranslationPostProcessor();
//    }
//

//    @Bean
//    public Properties additionalProperties() {
//        Properties properties = new Properties();
//        properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("db.hibernate.hbm2ddl.auto"));
//        properties.put("hibernate.dialect", environment.getRequiredProperty("db.hibernate.dialect"));
//        properties.put("hibernate.show_sql", environment.getRequiredProperty("db.hibernate.show_sql"));
//        return properties;
//    }

//    @Bean
//    public JdbcTemplate jdbcTemplate() {
//        return new JdbcTemplate(dataSource());
//    }
}
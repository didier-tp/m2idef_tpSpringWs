package com.capgemini.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/*  different uses:
 
   in main() :
   JavaConfigApplicationContext context =
    new AnnotationConfigApplicationContext(AppConfig.class, DataConfig.class);
    Service service = context.getBean(Service.class);
    
   in springContext.xml :
   <bean class="tp.myapp.minibank.impl.config.DomainAndPersistenceConfig"/>
   
   in spring test:
   @RunWith(SpringJUnit4ClassRunner.class)
	@ContextConfiguration(classes={tp.myapp.minibank.impl.config.DataSourceConfig.class,
		                       tp.myapp.minibank.impl.config.DomainAndPersistenceConfig.class}) //java config
   
   ...
 */



@Configuration
@Import(DataSourceConfig.class)
@EnableTransactionManagement() //"transactionManager" (not "txManager") is expected !!!
@EnableJpaRepositories(basePackages= {"com.capgemini.dao"})
@ComponentScan(basePackages={"com.capgemini.service"}) //to find and interpret @Autowired,  @Component , ...
public class DomainAndPersistenceConfig {

	// JpaVendorAdapter (Hibernate ou OpenJPA ou ...)
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdapter.setShowSql(false);
		hibernateJpaVendorAdapter.setGenerateDdl(false);
		//hibernateJpaVendorAdapter.setDatabase(Database.MYSQL);
		hibernateJpaVendorAdapter.setDatabase(Database.H2);
		return hibernateJpaVendorAdapter;
	}

	// EntityManagerFactory
	@Bean
	public EntityManagerFactory entityManagerFactory(
			JpaVendorAdapter jpaVendorAdapter, DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(jpaVendorAdapter);
		factory.setPackagesToScan("com.capgemini.entity");
		factory.setDataSource(dataSource);
		Properties jpaProperties = new Properties();
		jpaProperties.put("hibernate.hbm2ddl.auto", "create");
		jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		factory.afterPropertiesSet();
		return factory.getObject();
	}
	
	// pour activer la prise en charge de @PersistentContext dans le code 
	@Bean
	public PersistenceAnnotationBeanPostProcessor enablePersistentContextAnnotation(){
		return new PersistenceAnnotationBeanPostProcessor();
	}

	// Transaction Manager for JPA or ...
	@Bean(name="transactionManager")//("transactionManager" but not "txManager")
	public PlatformTransactionManager transactionManager(
			EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory);
		return txManager;
	}

}
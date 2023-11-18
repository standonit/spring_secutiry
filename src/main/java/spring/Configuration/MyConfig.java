package spring.Configuration;



import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@ComponentScan("spring")
@EnableWebMvc
public class MyConfig {

	@Bean
	public DataSource dataSource() {
			ComboPooledDataSource dataSource = new ComboPooledDataSource();
		try {
			dataSource.setDriverClass("org.postgresql.Driver");
			dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres?useSSL=false&serverTimezone=UTC");
			dataSource.setUser("postgres");
			dataSource.setPassword("4815162342");
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataSource;
	}
	
	 @Bean
	 public ViewResolver viewResolver() {
	 InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
	 internalResourceViewResolver.setPrefix("/WEB-INF/view/");
	 internalResourceViewResolver.setSuffix(".jsp");
	 
	return internalResourceViewResolver;
	 
	 }
}

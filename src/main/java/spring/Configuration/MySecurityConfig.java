package spring.Configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class MySecurityConfig {

	@Autowired
	DataSource dataSource;

	@Bean
	public UserDetailsManager userDetailsManager(DataSource dataSource) {
		return new JdbcUserDetailsManager(dataSource);
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((user) -> user.requestMatchers(new AntPathRequestMatcher("/"))
				.hasAnyRole("HR", "MANAGER", "IT", "EMPLOYEE")
				.requestMatchers(new AntPathRequestMatcher("/manager_info/**")).hasRole("MANAGER")
				.requestMatchers(new AntPathRequestMatcher("/hr_info/**")).hasRole("HR").anyRequest().authenticated())
				.formLogin(Customizer.withDefaults());

		return http.build();
	}
}
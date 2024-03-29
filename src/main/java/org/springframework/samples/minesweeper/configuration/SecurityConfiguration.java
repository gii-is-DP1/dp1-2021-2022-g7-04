package org.springframework.samples.minesweeper.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/resources/**", "/webjars/**", "/h2-console/**").permitAll()
		.antMatchers(HttpMethod.GET, "/", "/oups").permitAll()
		.antMatchers("/welcome").permitAll()
		.antMatchers("/users/new").permitAll()
		.antMatchers("/user/**").permitAll()
		.antMatchers("/users/**").permitAll()
		.antMatchers("/{username}/delete").hasAnyAuthority("admin")
		.antMatchers("/tutorial").permitAll()
		.antMatchers("/configAchievements").hasAnyAuthority("admin")
		.antMatchers("/updateAchievements").hasAnyAuthority("admin")
		.antMatchers("/audits").hasAnyAuthority("admin")
		.antMatchers("/players/new").permitAll()
		.antMatchers("/players/find/**").hasAnyAuthority("admin")
		.antMatchers("/players/list/**").hasAnyAuthority("admin","player")
		.antMatchers("/{playerId}/delete").hasAnyAuthority("admin")
		.antMatchers("/cells/update").permitAll()
		.antMatchers("/gameStats").authenticated()
		.antMatchers("/selectGame").hasAnyAuthority("player")
		.antMatchers("/newGame").hasAnyAuthority("player")
		.antMatchers("/finishGame").hasAnyAuthority("player")
		.antMatchers("/continueGame").hasAnyAuthority("player")
		.antMatchers("/restartGame").hasAnyAuthority("player")
		.antMatchers("/admin/**").hasAnyAuthority("admin").anyRequest().denyAll().and().formLogin()
		/* .loginPage("/login") */
		.failureUrl("/login-error").and().logout().logoutSuccessUrl("/");
		// Configuración para que funcione la consola de administración
		// de la BD H2 (deshabilitar las cabeceras de protección contra
		// ataques de tipo csrf y habilitar los framesets si su contenido
		// se sirve desde esta misma página.
		http.csrf().ignoringAntMatchers("/h2-console/**");
		http.headers().frameOptions().sameOrigin();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select username,password,enabled " + "from users " + "where username = ? and enabled = TRUE")
				.authoritiesByUsernameQuery("select username, authority " + "from authorities " + "where username = ?")
				.passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = NoOpPasswordEncoder.getInstance();
		return encoder;
	}
}
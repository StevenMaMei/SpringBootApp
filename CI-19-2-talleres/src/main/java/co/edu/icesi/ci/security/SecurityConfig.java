package co.edu.icesi.ci.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import co.edu.icesi.ci.talleres.model.Tmio1User;
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	public SecurityConfig() {
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {

		httpSecurity
		.authorizeRequests().antMatchers("/buses/add/**").hasAnyRole(Tmio1User.ROL_ADMINISTRADOR.toString())
		.and().authorizeRequests().antMatchers("/conductores/add/**").hasAnyRole(Tmio1User.ROL_ADMINISTRADOR.toString())
		.and().authorizeRequests().antMatchers("/rutas/add/**").hasAnyRole(Tmio1User.ROL_ADMINISTRADOR.toString())
		.and()
		.formLogin().loginPage("/login").permitAll().and()
		.authorizeRequests().antMatchers("/**").authenticated()
		.and()
		.authorizeRequests().antMatchers("/**").permitAll().and()
		.logout().invalidateHttpSession(true).clearAuthentication(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout");
	}

}

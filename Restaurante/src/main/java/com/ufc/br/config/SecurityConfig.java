package com.ufc.br.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ufc.br.security.UserDetailsServiceImplementacao;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsServiceImplementacao userDetailsServiceImplementacao; 
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
		.antMatchers("/pessoa/cadastro/usuario").permitAll()
		.antMatchers("/prato/listar").permitAll()
		.antMatchers("/pessoa/salvar").permitAll()
		.antMatchers("/pessoa/cadastro/gerente").hasRole("ADMIN")
		.antMatchers("/prato/formulario").hasRole("ADMIN")
		.antMatchers("/prato/cadastrar").hasRole("ADMIN")
		.antMatchers("/carrinho/").hasRole("USER")
		
		.anyRequest().authenticated()
		
		.and()
		.formLogin()
		.loginPage("/pessoa/logar").permitAll().defaultSuccessUrl("/prato/listar")
		
		.and()
		.logout()
		.logoutSuccessUrl("/pessoa/logar?logout").permitAll();
		
		
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsServiceImplementacao).passwordEncoder(new  BCryptPasswordEncoder());
		
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/js/**", "/images/**","/img/**");
	}



	
	
	
}

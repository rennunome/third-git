package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.auth.DatabaseUserDetailsService;

@EnableWebSecurity
public class WebSecurityConf extends WebSecurityConfigurerAdapter{

	@Autowired
	private DatabaseUserDetailsService userDetailsService;

	@Override
	protected void configure (AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			//ログインしないとこのWebアプリケーション配下のりそーずには一切アクセスができないということ
			.antMatchers("/css/**").permitAll()
			.anyRequest().authenticated()
			.and()
			//ブラウザ上でログイン情報とパスワードを求めるもの
			.formLogin().loginPage("/login").defaultSuccessUrl("/top", true).failureUrl("/error").usernameParameter("id").passwordParameter("password").permitAll();

		AuthenticationManager a = this.authenticationManager();
        if (a instanceof ProviderManager) {
            ProviderManager a2 = (ProviderManager)a;
            a2.getProviders().forEach(p -> {
                if (p instanceof MessageSourceAware) {
                    ((MessageSourceAware)p).setMessageSource(s);
                }
            });
        }
	}
	//パスワードをハッシュ化するためのメソッド
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();

	}}



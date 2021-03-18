package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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

	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			//ログインしないとこのWebアプリケーション配下のりそーずには一切アクセスができないということ
			.anyRequest().authenticated()
			.and()
			//ブラウザ上でログイン情報とパスワードを求めるもの
			.formLogin();
	}
	//パスワードをハッシュ化するためのメソッド
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();

	}
}



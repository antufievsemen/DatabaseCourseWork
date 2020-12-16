package ru.spbstu.antufievsemen.coursedatabase.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private static final String PERSONAL_ENDPOINT = "/personal/*";
  private static final String QUERY_PART_ENDPOINT = "/query-part/*";
  private static final String QUERY_RESOURCE_ENDPOINT = "/query-resource/*";
  private static final String RESOURCE_ENDPOINT = "/store/*";
  private static final String GRADE_BEERS_ENDPOINT = "/gradeBeer/*";
  private static final String QUERY_RESOURCE_ADD_ENDPOINT = "/query-resource/addQueryResource";
  private static final String QUERY_RESOURCE_UPDATE_ENDPOINT = "/query-resource/updateQueryResource/*";


  @Override
  protected void configure(HttpSecurity http) throws Exception{
    http
            .csrf().disable()
            .httpBasic().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
//            .antMatchers("/personal/list-managers", "/personal/list-factoryWorkers", "/personal/list-storeWorkers").hasRole("ADMIN")
            .mvcMatchers(PERSONAL_ENDPOINT).hasRole("ADMIN")
            .antMatchers(QUERY_PART_ENDPOINT).hasAnyRole("ADMIN", "MANAGER")
            .antMatchers(QUERY_RESOURCE_ENDPOINT).hasAnyRole("ADMIN", "FACTORY", "STORE")
            .antMatchers(RESOURCE_ENDPOINT).hasAnyRole("ADMIN", "STORE")
            .antMatchers(GRADE_BEERS_ENDPOINT).hasAnyRole("ADMIN", "FACTORY", "MANAGER");
//            .antMatchers()
  }
}

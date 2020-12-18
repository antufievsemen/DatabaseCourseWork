package ru.spbstu.antufievsemen.coursedatabase.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.DefaultSecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private static final String PERSONAL_ENDPOINT = "/personal/*";
  private static final String QUERY_PART_ENDPOINT = "/query-part/*";
  private static final String QUERY_RESOURCE_ENDPOINT = "/query-resource/*";
  private static final String STORE_ENDPOINT = "/store/*";
  private static final String GRADE_BEERS_ENDPOINT = "/gradeBeer/*";

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http
            .formLogin()
            .and()
            .authorizeRequests()
            .antMatchers(PERSONAL_ENDPOINT).hasAuthority("ADMIN")

//            .antMatchers(QUERY_PART_ENDPOINT).hasAuthority("MANAGER")
            .antMatchers("/query-part/list-parts", "/query-part/part*", "/query-part/updateQueryPart*").hasAnyAuthority("FACTORY", "MANAGER")
            .antMatchers("/query-part/addQueryPart", "query-part/deleteQueryPart").hasAuthority("MANAGER")

//            .antMatchers(QUERY_RESOURCE_ENDPOINT).hasAuthority("FACTORY")
            .antMatchers("/query-resource/list-resources", "/query-resource/queryResource*", "/query-resource/updateQueryResource*").hasAnyAuthority("STORE", "FACTORY")
            .antMatchers("/query-resource/deleteQueryResource", "/query-resource/addQueryResource").hasAuthority("FACTORY")

//            .antMatchers(STORE_ENDPOINT).hasAuthority("STORE")
            .antMatchers("/store/list-resources", "/store/resource*").hasAnyAuthority("FACTORY", "STORE")
            .antMatchers("/store/deleteResource", "/store/addResource", "/store/updateResource*").hasAuthority("STORE")

//            .antMatchers(GRADE_BEERS_ENDPOINT).hasAuthority("FACTORY")
            .antMatchers("/gradeBeer/list-beerGrades", "/gradeBeer/beerGrade*").hasAnyAuthority("MANAGER", "FACTORY")
            .antMatchers("/gradeBeer/deleteGradeBeer", "/gradeBeer/addGradeBeer", "/gradeBeer/updateGradeBeer*").hasAuthority("FACTORY")

            .antMatchers("/", "/updateUser").authenticated()
            .antMatchers("/login").permitAll();
  }
}

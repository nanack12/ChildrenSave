package kr.co.childrensave.config;

import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import kr.co.childrensave.service.OAuth2UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig	{
	
	private final OAuth2UserService oAuth2UserService = new OAuth2UserService();
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		HttpSessionRequestCache requsetCache = new HttpSessionRequestCache();
		requsetCache.setMatchingRequestParameterName(null);
		http
			.formLogin(authorize -> authorize.loginPage("/login")
					.loginProcessingUrl("/loginProc")
					.defaultSuccessUrl("/index")
					.permitAll()		
					);
		

        http.oauth2Login((oauth2Configurer) -> oauth2Configurer
                        .loginPage("/login")
                        .defaultSuccessUrl("/loginSuccess")
                        .failureUrl("/login?error")
                        .successHandler(successHandler())
                        .userInfoEndpoint()
                        .userService(oAuth2UserService));
        			
        
		http
		    .logout(authorize -> authorize
		    		.logoutUrl("/logout")
		            .logoutSuccessUrl("/index")
	                .invalidateHttpSession(true)
	                .deleteCookies("JSESSIONID"));
		
		http 
			 .csrf(csrf -> csrf.disable())
			 .requestCache(request -> request
					 .requestCache(requsetCache))
		     .authorizeHttpRequests(authorize -> authorize 
		    		 .requestMatchers
		    		 ("/","/index","/error","/favicon.ico","/bootstrap/**","mapper/**","/test.do","/testdb.do","/login","/save","/loginProc","/saveProc","/toggleoverlay.do","/togglelist.do","/school.do","/board/paging","/notice/list","/siteinfo.do","teaminfo.do")
		    		 .permitAll()
				     .anyRequest().authenticated()
		    		 );
		http
			.sessionManagement(authorize -> authorize
					.maximumSessions(1)
					.maxSessionsPreventsLogin(false)
					);
		http
			.sessionManagement(authorize -> authorize
					.sessionFixation().changeSessionId()
					);
		
		/*
		 * http .authorizeHttpRequests() .requestMatchers("/login", "/signup",
		 * "/user").permitAll() .anyRequest().authenticated() .and() .formLogin() // 폼
		 * 기반 로그인 설정 .loginPage("/login") .defaultSuccessUrl("/home") .and() .logout()
		 * // 로그아웃 설정 .logoutSuccessUrl("/login") .invalidateHttpSession(true) .and()
		 * .csrf().disable() //csrf 비활성화 .build();
		 */
		
		
		
		return http.build();
	}
	
	@Bean
    public AuthenticationSuccessHandler successHandler() {
        return ((request, response, authentication) -> {
            DefaultOAuth2User defaultOAuth2User = (DefaultOAuth2User) authentication.getPrincipal();
 
            String id = defaultOAuth2User.getAttributes().get("id").toString();
            String body = """
                    {"id":"%s"}
                    """.formatted(id);
 
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            response.sendRedirect("/index");
 
            PrintWriter writer = response.getWriter();
            writer.println(body);
            writer.flush();
            
        });
        
        
    }
	
	
}

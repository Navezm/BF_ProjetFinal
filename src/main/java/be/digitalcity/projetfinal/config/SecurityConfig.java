package be.digitalcity.projetfinal.config;

import be.digitalcity.projetfinal.config.jwt.JwtAuthenticationFilter;
import be.digitalcity.projetfinal.config.jwt.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider tokenProvider;

    public SecurityConfig(JwtTokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
//                .httpBasic();

        http.authorizeRequests()
                .antMatchers(SecurityConstants.LOGIN_URL).permitAll()
                .antMatchers(SecurityConstants.REGISTER_URL)
                .permitAll()

                // User
                .antMatchers(HttpMethod.POST, "/user/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/user/**").hasAuthority("modifyUser")
                .antMatchers(HttpMethod.DELETE, "/user/**").hasAuthority("deleteUser")
                .antMatchers(HttpMethod.GET, "/user/**").hasAuthority("ADMIN")

                // Address
                .antMatchers(HttpMethod.GET, "/address/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/address/**").hasAuthority("USER")
                .antMatchers(HttpMethod.DELETE, "/address/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT, "/painting/**").hasAuthority("USER")

                // Disponibility
                .antMatchers("/disponibility/**").hasAuthority("addAvailability")

                // EventCategory
                .antMatchers(HttpMethod.GET, "/eventCategory/**").hasAuthority("USER")
                .antMatchers(HttpMethod.DELETE, "/eventCategory/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/eventCategory/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT, "/eventCategory/**").hasAuthority("ADMIN")

                // Group
                .antMatchers("/group/**").hasAuthority("ADMIN")

                // Painting
                .antMatchers(HttpMethod.GET, "/painting/**").hasAuthority("shopAccess")
                .antMatchers(HttpMethod.POST, "/painting/**").hasAuthority("addPainting")
                .antMatchers(HttpMethod.DELETE, "/painting/**").hasAuthority("deletePainting")
                .antMatchers(HttpMethod.PUT, "/painting/**").hasAuthority("ADMIN")

                // PaintingPurchase
                .antMatchers(HttpMethod.GET, "/paintingPurchase/**").hasAuthority("handlePurchase")
                .antMatchers(HttpMethod.PUT, "/paintingPurchase/**").hasAuthority("handlePurchase")
                .antMatchers(HttpMethod.DELETE, "/paintingPurchase/**").hasAuthority("handlePurchase")
                .antMatchers(HttpMethod.POST, "/paintingPurchase/**").hasAuthority("buy")

                // PaintingQuotation
                .antMatchers(HttpMethod.GET, "/paintingQuotation/**").hasAuthority("treatingQuotation")
                .antMatchers(HttpMethod.DELETE, "/paintingQuotation/**").
                hasAuthority("treatingQuotation")
                .antMatchers(HttpMethod.PUT, "/paintingQuotation/**").hasAuthority("treatingQuotation")
                .antMatchers(HttpMethod.POST, "/paintingQuotation/**").hasAuthority("makingQuotation")

                // PaintingType
                .antMatchers(HttpMethod.GET, "/paintingType/**").hasAuthority("shopAccess")
                .antMatchers(HttpMethod.POST, "/paintingType/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT, "/paintingType/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/paintingType/**").hasAuthority("ADMIN")

                // Picture
                .antMatchers(HttpMethod.GET, "/picture/**").hasAuthority("shopAccess")
                .antMatchers(HttpMethod.POST, "/picture/**").hasAuthority("addPicture")
                .antMatchers(HttpMethod.PUT, "/picture/**").hasAuthority("addPicture")
                .antMatchers(HttpMethod.DELETE, "/picture/**").hasAuthority("deletePicture")

                // PicturePurchase
                .antMatchers(HttpMethod.GET, "picturePurchase").hasAuthority("handlePurchase")
                .antMatchers(HttpMethod.PUT, "picturePurchase").hasAuthority("handlePurchase")
                .antMatchers(HttpMethod.POST, "picturePurchase").hasAuthority("buy")
                .antMatchers(HttpMethod.DELETE, "picturePurchase").hasAuthority("handlePurchase")

                // Reservation
                .antMatchers(HttpMethod.GET, "/reservation/**").hasAuthority("handleReservation")
                .antMatchers(HttpMethod.PUT, "/reservation/**").hasAuthority("handleReservation")
                .antMatchers(HttpMethod.DELETE, "/reservation/**").hasAuthority("handleReservation")
                .antMatchers(HttpMethod.POST, "/reservation/**").hasAuthority("makingReservation")

                // Role
                .antMatchers("/role/**").hasAuthority("handleRole")

                .anyRequest().authenticated();

        http.addFilterBefore(new JwtAuthenticationFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        corsConfiguration.addAllowedOrigin("http://localhost:4200/");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addExposedHeader("Authorization");
        corsConfiguration.setAllowCredentials(true);

        return request -> corsConfiguration;
    }

}

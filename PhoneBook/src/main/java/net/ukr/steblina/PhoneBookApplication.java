package net.ukr.steblina;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@ComponentScan
@EnableAutoConfiguration(exclude = {HibernateJpaAutoConfiguration.class,DataSourceAutoConfiguration.class,JpaRepositoriesAutoConfiguration.class})//})//
@PropertySource("file:${lardi.conf}")
public class PhoneBookApplication {
    public static void main(String[] args) {
    	SpringApplication.run(PhoneBookApplication.class, args);
    }

}

package kuraido.midka;

import kuraido.midka.models.Customer;
import kuraido.midka.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
@SpringBootApplication

//@PropertySource("classpath:application-${spring.profiles.active:default}.properties")
public class MidkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MidkaApplication.class, args);
    }

/*    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository){
        return args -> {
            Customer vasya = new Customer("Vasya",
                    "Pupkin", "vasiliy@mail.ru", "Elektrik",
                    "996555555555", "Bishkek", "www.vasyavip.kg");
*//*            Customer ulan = new Customer("Ulan",
                    "Sydygaliev", "ulan.sydygaliev@mail.ru", "Java Intern",
                    "996555170228", "Bishkek", "");*//*

            Customer erlan = new Customer("Erlan",
                    "Kumavich", "erlaha.gg@mail.ru", "Senior Pascal Dev",
                    "88005553535", "Domodedovo", "www.illuminati.ru");
            customerRepository.save(vasya);
            customerRepository.save(erlan);
        };

    }*/

}

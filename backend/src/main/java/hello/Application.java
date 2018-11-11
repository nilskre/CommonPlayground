package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Bean
    public CommandLineRunner demo(SessionRepository repository) {
        return (args) -> {
            repository.save(new Session("Title INIT", "Description", "Game", "Place", "Date", 42));

            // fetch all sessions
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Session session : repository.findAll()) {
                log.info(session.toString());
            }
            log.info("");

            // fetch an individual session by ID
            repository.findById(1L)
                    .ifPresent(customer -> {
                        log.info("Customer found with findById(1L):");
                        log.info("--------------------------------");
                        log.info(customer.toString());
                        log.info("");
                    });
        };
    }

}

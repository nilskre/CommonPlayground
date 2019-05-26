package commonplayground;

import commonplayground.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);
    private TestData testData = new TestData();

    public static void main(String[] args) {
        String ENV_PORT = System.getenv().get("PORT");
        String ENV_DYNO = System.getenv().get("DYNO");
        if (ENV_PORT != null && ENV_DYNO != null) {
            System.getProperties().put("server.port", ENV_PORT);
        }
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner demo(SessionRepository sessionRepository, UserRepository userRepository, MessageRepository messageRepository) {
        return (args) -> {
            //sessionRepository.save(testData.getTestSessions().get(0));
            //sessionRepository.save(testData.getTestSessions().get(1));
            //sessionRepository.save(testData.getTestSessions().get(2));
            //userRepository.save(testData.getTestUser());
            //messageRepository.save(testData.getTestMessage());

            // fetch all sessions
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Session session : sessionRepository.findAll()) {
                log.info(session.toString());
            }
            log.info("");

            // fetch an individual session by ID
            sessionRepository.findById(1L)
                    .ifPresent(customer -> {
                        log.info("Customer found with findById(1L):");
                        log.info("--------------------------------");
                        log.info(customer.toString());
                        log.info("");
                    });
        };
    }

}

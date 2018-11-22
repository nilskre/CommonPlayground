package hello;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SpringSecurityExperiment {

    public static void main(String[] args) {

        int i = 0;
        while (i < 10) {
            String password = "123456";
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(password);

            System.out.println(hashedPassword);
            i++;


            if (passwordEncoder.matches("123456", hashedPassword)){
                System.out.println("MATCH");
            }
        }

    }
}

package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/postNewSession")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name, @RequestParam(value="game", defaultValue = "OneGame") String game) {
        System.out.println("Param1: " + name);
        System.out.println("Param2: " + game);
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name + game));
    }
}

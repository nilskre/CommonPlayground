package commonplayground.controller.cucumber.api;

import commonplayground.Application;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import static io.restassured.RestAssured.get;

public class GlobalStepDefinitions /*extends CucumberRuntime*/{
    private ConfigurableApplicationContext ctx;

    @Given("The backend is active")
    public void theBackendIsActive() {
        try {
            //SpringApplication.run(Application.class);
            ctx = new SpringApplicationBuilder(Application.class)
                    .web(WebApplicationType.NONE).run();
        } catch (Exception e) {
            assert false;
        }
    }

    @When("^I look for the session list \"([^\"]*)\"$")
    public void iLookFor(String requestHeader) {
        get(requestHeader).then().statusCode(200);
    }

    @And("stopSpringBoot")
    public void stopspringboot() {
        SpringApplication.exit(ctx, new ExitCodeGenerator() {
            @Override
            public int getExitCode() {
                // return the error code
                return 0;
            }
        });
    }
}

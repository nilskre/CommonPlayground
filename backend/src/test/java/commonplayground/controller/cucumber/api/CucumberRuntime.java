package commonplayground.controller.cucumber.api;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = Application.class)
//@ContextConfiguration
//@RunWith(SpringRunner.class)
//@ContextConfiguration(classes = Application.class)
//@WebAppConfiguration
//@ContextConfiguration
//@SpringBootTest
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration
public abstract class CucumberRuntime {

    @Before
    public void testSetUpCu() {
        System.out.println("EXECUTE CU");
        //super.gedings();
        //test();
    }

    @After
    public void tearDown() {
        System.out.println("EXIT");
    }

    /*@Test
    public void setup(){
        System.out.println("HHHEEEEEEELLLLLLLOOOOOOOOOOO");
    }*/

    /*@Before
    public void setup_cucumber_spring_context(){
        // Dummy method so cucumber will recognize this class as glue
        // and use its context configuration.
    }*/

    /*@BeforeClass
    public static void setUpTestEnv(){

    }
*/
    /*@Test
    public void gedings(){
        System.out.println("Runned");

    }
*/
    //private static final Logger log = LoggerFactory.getLogger(CucumberRuntime.class);

    /*private final String SERVER_URL = "http://localhost";
    private final String THINGS_ENDPOINT = "/registerNewUser";

    private RestTemplate restTemplate;

    //@LocalServerPort
    protected int port = 8080;

    public CucumberRuntime() {
        restTemplate = new RestTemplate();
    }

    String thingsEndpoint() {
        System.out.println("ENDPOINT " + SERVER_URL + ":" + port + THINGS_ENDPOINT);
        return SERVER_URL + ":" + port + THINGS_ENDPOINT;
    }

    int put(final String something) {
        return restTemplate.postForEntity(thingsEndpoint(), something, Void.class).getStatusCodeValue();
    }

    Bag getContents() {
        return restTemplate.getForEntity(thingsEndpoint(), Bag.class).getBody();
    }

    void clean() {
        restTemplate.delete(thingsEndpoint());
    }*/
}
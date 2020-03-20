package integration;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

//available tags @SmokeTest, @Regression @SystemTest
@CucumberOptions(
        features = "src/test/resources/Features",
        tags = {"@regression","@~ignore"},
        plugin ={ "pretty",
                "junit:target/result.xml",
                "html:target/cucumber"
    })
public class AUTOTest {



}

package integration;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

//available tags @happFlow, @regression @errorScenarios
@CucumberOptions(
        features = "src/test/resources/Features",
        glue = {"stepDef"},
        tags = {"@regression","~@ignore"},
        plugin ={ "pretty",
                "junit:target/result.xml",
                "html:target/cucumber"
    })
public class AUTOTest {



}

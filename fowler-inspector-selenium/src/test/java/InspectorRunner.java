import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/features",
        glue = "glue",
        plugin = { "pretty", "html:target/cucumber-reports.html" }
//        plugin = {"pretty"}
)
public class InspectorRunner {
}
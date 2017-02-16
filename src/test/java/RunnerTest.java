
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        monochrome = false,
        plugin = {
        },
        features = {"src/test/resources/"},
        tags = {
               "@All",
               // "@Test1",
                //"@Test2"
               // "@Test3"
                
        }
)

public class RunnerTest {
}
package API;

import Utils.SerenityPropertiesReader;
import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;
import API.apis.Apis;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/API/features/api.feature",
        glue = "API.stepdefinitions",
        plugin = "pretty"
)

public class CucumberApiTestSuite {
    public static final SerenityPropertiesReader propertiesReader = new SerenityPropertiesReader();
    public static Apis apis = new Apis();
}
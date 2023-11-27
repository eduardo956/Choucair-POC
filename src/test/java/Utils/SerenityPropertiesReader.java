package Utils;
import net.serenitybdd.core.environment.WebDriverConfiguredEnvironment;

public class SerenityPropertiesReader {

    public String getSerenityProperty(String propertyName) {
        return WebDriverConfiguredEnvironment.getEnvironmentVariables().getProperty(propertyName);
    }
}

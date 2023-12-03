package WEB;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;
import WEB.pages.CheckOut;
import WEB.pages.PaginaPrincipal;
import WEB.pages.SubCategoria;
import WEB.utils.ContextSerenity;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/WEB/features/AgregarProductosV2.feature",
        glue = "WEB.stepdefinitions",
        plugin = "pretty"
)

public class CucumberTestSuite {
    public static final String BASE_URL = "https://www.exito.com";
    public static PaginaPrincipal paginaPrincipalPage = new PaginaPrincipal();
    public static SubCategoria subCategoriaPage = new SubCategoria();
    public static ContextSerenity ContextUtils = new ContextSerenity();
    public static CheckOut checkOutPage = new CheckOut();
}
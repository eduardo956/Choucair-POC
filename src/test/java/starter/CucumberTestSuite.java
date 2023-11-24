package starter;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;
import starter.pages.CheckOut;
import starter.pages.PaginaPrincipal;
import starter.pages.SubCategoria;
import starter.utils.ContextSerenity;
import starter.utils.Producto;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/productos/AgregarProductos.feature",
        glue = "starter.stepdefinitions",
        plugin = "pretty"
)

public class CucumberTestSuite {
    public static final String BASE_URL = "https://www.exito.com";
    public static PaginaPrincipal paginaPrincipalPage = new PaginaPrincipal();
    public static SubCategoria subCategoriaPage = new SubCategoria();
    public static ContextSerenity ContextUtils = new ContextSerenity();
    public static CheckOut checkOutPage = new CheckOut();
}
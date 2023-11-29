package Movile;

import Utils.SerenityPropertiesReader;
import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import Movile.pages.PaginaPrincipal;
import Movile.pages.PaginaRegistro;
import Movile.pages.PaginaLogeo;
import Movile.pages.PaginaProductos;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/Movile/features",
        glue = "Movile.stepdefinitions",
        plugin = "pretty"
)

public class CucumberMovileTestSuite {

    public static PaginaPrincipal PaginaPrincipal = new PaginaPrincipal();
    public static PaginaRegistro PaginaRegistro = new PaginaRegistro();
    public static PaginaLogeo PaginaLogeo = new PaginaLogeo();
    public static PaginaProductos PaginaProductos = new PaginaProductos();
}
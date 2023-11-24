package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import starter.CucumberTestSuite;
import starter.utils.Producto;
import java.util.List;

public class SearchStepDefinitions extends CucumberTestSuite {
    @Given("Estoy en la página de Tiendas Éxito")
    public void elRobotEstaEnLaPaginaDeTiendasExito() {
        paginaPrincipalPage.open();
        paginaPrincipalPage.cerrarModal();
    }
    @When("Elijo la categoría {string} y la subcategoría {string}")
    public void eligeLaCategoriaYLaSubcategoria(String catergoria, String subCategoria) {
        paginaPrincipalPage.seleccionarMenu();
        paginaPrincipalPage.seleccionarCategoriaPorNombre(catergoria,subCategoria);
    }
    @And("Elijo {int} productos aleatoriamente con cantidades entre {int} y {int}")
    public void eligeProductosyCantidadesAleatorias(int numeroProductos, int cantidadMinima, int cantidadMaxima) {
        int est = 2;
        ContextUtils.guardarContextoProductos("Productos", subCategoriaPage.elegirProductosConCantidadAleatoriayRetornarlos(numeroProductos, cantidadMinima, cantidadMaxima));
    }
    @And("Selecciono el carrito de Compras")
    public void seleccionoElCarritoDeCompras() {
        paginaPrincipalPage.seleccionarCarrito();
    }
    @And("Ingreso el email {string}")
    public void carritoingresoElEmail(String email) {
        checkOutPage.ingresarEmail(email);
    }
    @Then("Valida que los productos en el carrito son correctos")
    public void validaQueLosProductosEnElCarritoSonCorrectos() {
        List<Producto> productosRecuperados = ContextUtils.obtenerContextoProductos("Productos");
    }

    @Then("El nombre de los productos agregados deberá coincidir con los nombres en el carrito")
    public void elNombreDeLosProductosAgregadosDeberaCoincidirConLosNombresEnElCarrito() {
        List<Producto> productosRecuperados = ContextUtils.obtenerContextoProductos("Productos");
    }

    @Then("El total de los precios de los productos agregados deberá coincidir con los precios en el carrito")
    public void elTotalDeLosPreciosDeLosProductosAgregadosDeberaCoincidirConLosPreciosEnElCarrito() {
        List<Producto> productosRecuperados = ContextUtils.obtenerContextoProductos("Productos");
    }

    @Then("Las cantidades de los productos agregados deberán coincidir con las cantidades en el carrito")
    public void lasCantidadesDeLosProductosAgregadosDeberanCoincidirConLasCantidadesEnElCarrito() {
        List<Producto> productosRecuperados = ContextUtils.obtenerContextoProductos("Productos");
    }

    @Then("El número de productos agregados debe ser igual al número en el carrito")
    public void elNumeroDeProductosAgregadosDebeSerIgualAlNumeroEnElCarrito() {
        List<Producto> productosRecuperados = ContextUtils.obtenerContextoProductos("Productos");
    }
}

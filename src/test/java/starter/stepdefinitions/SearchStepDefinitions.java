package starter.stepdefinitions;

import starter.pages.paginaPrincipal;
import starter.pages.subCategoriaPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SearchStepDefinitions {

    private paginaPrincipal paginaPrincipal = new paginaPrincipal();
    private subCategoriaPage subCategoriaPage = new subCategoriaPage();
    @Given("El robot está en la página de Tiendas Éxito")
    public void elRobotEstaEnLaPaginaDeTiendasExito() {
        paginaPrincipal.open();
        paginaPrincipal.cerrarModal();
    }

    @When("Elige una categoría y subcategoría")
    public void eligeUnaCategoriaYSubcategoria() {
        paginaPrincipal.seleccionarMenu();
        paginaPrincipal.seleccionarCategoriaPorNombre("Tecnología","Televisores");
    }
    @And("Elige {int} productos aleatoriamente con cantidades entre {int} y {int}")
    public void eligeProductosAleatoriamenteConCantidadesEntreY(int numeroProductos, int cantidadMinima, int cantidadMaxima) {
        for(int i=0; i<numeroProductos;i++){
            subCategoriaPage.eligirProductosconCantidadAleatoria(cantidadMinima,cantidadMaxima);
        }
    }

    @Then("Valida que los productos en el carrito son correctos")
    public void validaQueLosProductosEnElCarritoSonCorrectos() {
    }
}

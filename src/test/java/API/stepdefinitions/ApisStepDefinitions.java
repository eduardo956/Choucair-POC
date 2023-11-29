package API.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import API.CucumberApiTestSuite;

import io.cucumber.datatable.DataTable;
import org.junit.Assert;

public class ApisStepDefinitions extends CucumberApiTestSuite {
    private DataTable dataTableContext;
    private String idContext;
    
    @Given("No existo en la lista de empleados")
    public void noExistoEnLaListaDeEmpleados(DataTable dataTable) {
        dataTableContext= dataTable;
        apis.esperarPorSegundos(60);
        String mensajeError = "Se encontro al empleado en la respuesta";
        Assert.assertFalse(mensajeError, apis.verificarExistenciaEnListaEmpleadosPorDatatable(dataTable));
    }
    
    @Given("Existo en la lista de empleados con los siguientes detalles")
    public void ExistoEnLaListaDeEmpleados(DataTable dataTable) {
        dataTableContext = dataTable;
        apis.esperarPorSegundos(60);
        String mensajeError = "No se encontro al empleado en la respuesta";
        Assert.assertTrue(mensajeError, apis.verificarExistenciaEnListaEmpleadosPorDatatable(dataTable));
    }

    @When("Lo creo en el sistema")
    public void meRegistroEnElSistema() {
        apis.esperarPorSegundos(60);
        idContext = apis.crearNuevoEmpleadoPorDatatable(dataTableContext);
    }

    @Then("Podre consultar mis datos por mi id")
    public void podreConsultarMisDatosPorMiId() {
        apis.esperarPorSegundos(60);
        apis.validarConsultarDatosPorIdYDatatable(idContext, dataTableContext);
    }

    @When("Actualizo mis datos con los siguientes detalles")
    public void actualizoMisDatos(DataTable dataTable) {
        dataTableContext = dataTable;
        apis.esperarPorSegundos(60);
        apis.actualizarDatosPorDatatable(dataTable);
    }

    @Then("Podre encontrarme con esos datos en la lista de empleados")
    public void podreEncontrarmeConEsosDatosEnLaListaDeEmpleados() {
        apis.esperarPorSegundos(60);
        String mensajeError = "No se encontro al empleado en la respuesta";
        Assert.assertTrue(mensajeError, apis.verificarExistenciaEnListaEmpleadosPorDatatable(dataTableContext));
    }

    @Then("No debería encontrarme en la lista de empleados")
    public void noDeberiaEncontrarmeEnLaListaDeEmpleados() {
        apis.esperarPorSegundos(60);
        String mensajeError = "Se encontro al empleado en la respuesta";
        Assert.assertFalse(mensajeError,apis.verificarExistenciaEnListaEmpleadosPorDatatable(dataTableContext));
    }
    
    @When("Elimino mi registro con id {int}")
    public void eliminoMiRegistroConId(int idtercero) {
        apis.esperarPorSegundos(60);
        apis.eliminarEmpleadoPorId(idtercero);
    }

}

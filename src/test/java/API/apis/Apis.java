package  API.apis;

import API.CucumberApiTestSuite;
import io.cucumber.datatable.DataTable;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.core.Serenity;
import org.junit.Assert;
import java.util.List;

public class Apis extends CucumberApiTestSuite{
    private String baseUrl = propertiesReader.getSerenityProperty("baseApi.url");;
    private String construirCuerpoSolicitud(String nombre, String salario, String edad) {
        return String.format("{\"name\":\"%s\",\"salary\":\"%s\",\"age\":\"%s\"}", nombre, salario, edad);
    }

    private String obtenerIdDesdeRespuesta(Response response) {
        String employeeListString = response.getBody().asString();
        return JsonPath.from(employeeListString).get("data.id").toString();
    }

    private String construirCadenaComparacion(String nombre, String salario, String edad) {
        return String.format("\"name\":\"%s\",\"salary\":%s,\"age\":%s", nombre, salario, edad);
    }

    private void verificarExistenciaCadenaEnRespuesta(String responseString, String expectedString, String errorMessage) {
        Assert.assertTrue(errorMessage, responseString.contains(expectedString));
    }

    public String crearNuevoEmpleadoPorDatatable(DataTable dataTableContext) {
        RestAssured.baseURI = baseUrl;
        RestAssured.basePath = "/create";
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.contentType(ContentType.JSON);

        List<List<String>> rows = dataTableContext.asLists(String.class);
        List<String> columns = rows.get(1);
        String nombre = columns.get(0);
        String salario = columns.get(1);
        String edad = columns.get(2);

        String requestBody = construirCuerpoSolicitud(nombre, salario, edad);

        Response response = httpRequest.body(requestBody).post();

        Assert.assertEquals(200, response.statusCode());

        String employeeListString = response.getBody().asString();
        String expectedString = construirCadenaComparacion(nombre, salario, edad);

        Serenity.recordReportData()
                .withTitle("Crear Empleado Request")
                .andContents("Request Body: " + requestBody);

        Serenity.recordReportData()
                .withTitle("Crear Empleado Response")
                .andContents("Response Body: " + response.getBody().asString());

        Serenity.recordReportData()
                .withTitle("Empleado Esperado")
                .andContents(expectedString);

        Assert.assertFalse("El Empleado" + nombre + "No ah sido encontrado", employeeListString.contains(expectedString));

        return obtenerIdDesdeRespuesta(response);
    }

    public void validarConsultarDatosPorIdYDatatable(String idEmpleado, DataTable dataTableContext) {
        RestAssured.baseURI = baseUrl;
        RestAssured.basePath = "/employee/{id}";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.pathParam("id", idEmpleado).get();

        Assert.assertEquals(200, response.statusCode());
        String employeeListString = response.getBody().asString();
        List<List<String>> rows = dataTableContext.asLists(String.class);
        List<String> columns = rows.get(1);
        String nombre = columns.get(0);
        String salario = columns.get(1);
        String edad = columns.get(2);

        String expectedString = construirCadenaComparacion(nombre, salario, edad);
        String errorMessage = "No se encontró al empleado " + nombre + " en la consulta";

        Serenity.recordReportData()
                .withTitle("Consultar Empleado por ID Response")
                .andContents("Response Body: " + response.getBody().asString());

        Serenity.recordReportData()
                .withTitle("Empleado Esperado")
                .andContents(expectedString);

        verificarExistenciaCadenaEnRespuesta(employeeListString, expectedString, errorMessage);
    }

    public void actualizarDatosPorDatatable(DataTable dataTableContext) {
        RestAssured.baseURI = baseUrl;
        RestAssured.basePath = "/update/{id}";
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.contentType(ContentType.JSON);

        List<List<String>> rows = dataTableContext.asLists(String.class);
        List<String> columns = rows.get(1);
        String idEmpleado = columns.get(0);
        String nombre = columns.get(1);
        String salario = columns.get(2);
        String edad = columns.get(3);
        String requestBody = String.format("\"name\":\"%s\", \"salary\":%s, \"age\":%s", nombre, salario, edad);
        Response response = httpRequest.body(requestBody)
                .pathParam("id", idEmpleado)
                .put();

        Assert.assertEquals(200, response.statusCode());

        Serenity.recordReportData()
                .withTitle("Crear Empleado Request")
                .andContents("Request Body: " + requestBody);

        Serenity.recordReportData()
                .withTitle("Actualizar Empleado por ID Response")
                .andContents("Response Body: " + response.getBody().asString());

        String responseString = response.getBody().asString();
        String expectedMessage = "Successfully! Record has been updated.";
        String errorMessage = "La petición de Actualizacion fue incorrecta";

        verificarExistenciaCadenaEnRespuesta(responseString, expectedMessage, errorMessage);
    }

    public boolean verificarExistenciaEnListaEmpleadosPorDatatable(DataTable dataTable) {
        RestAssured.baseURI = baseUrl;
        RestAssured.basePath = "/employees";
        RequestSpecification httpRequest = RestAssured.given();

        Response response = httpRequest.get();
        Assert.assertEquals(200, response.statusCode());

        String employeeString = response.getBody().asString();

        List<List<String>> rows = dataTable.asLists(String.class);
        List<String> columns = rows.get(1);
        String nombre = columns.get(0);
        String salario = columns.get(1);
        String edad = columns.get(2);

        Serenity.recordReportData()
                .withTitle("Lista de Empleados Response")
                .andContents("Response Body: " + response.getBody().asString());

        String expectedString = String.format("\"employee_name\":\"%s\",\"employee_salary\":%s,\"employee_age\":%s", nombre, salario, edad);

        Serenity.recordReportData()
                .withTitle("Empleado Esperado")
                .andContents(expectedString);

        return employeeString.contains(expectedString);
    }

    public void esperarPorSegundos(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void eliminarEmpleadoPorId(int idEmpleado) {
        RestAssured.baseURI = baseUrl;
        RestAssured.basePath = "delete/{id}";
        RequestSpecification httpRequest = RestAssured.given();

        Response response = httpRequest.pathParam("id", idEmpleado).delete();

        String bodyResponseString = response.getBody().asString();
        String expectedString = String.format("\"status\":\"%s\",\"data\":\"%s\",\"message\":\"%s\"","success", idEmpleado, "Successfully! Record has been deleted");

        Serenity.recordReportData()
                .withTitle("Eliminar Empleado Response")
                .andContents("Response Body: " + response.getBody().asString());

        Serenity.recordReportData()
                .withTitle("Empleado Esperado")
                .andContents(bodyResponseString);

        Assert.assertTrue("La petición de eliminacion fue incorrecta", bodyResponseString.contains(expectedString));
    }
}

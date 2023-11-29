package Movile.stepdefinitions;

import Movile.CucumberMovileTestSuite;
import Movile.pages.PaginaProductos;
import Movile.pages.PaginaRegistro;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class MovilStepDefinitions extends CucumberMovileTestSuite {
    public String nombreProducto;
    @Given("Me encuentro en la aplicacion Movil de tiendas Exito")
    public void meEncuentroEnLaAplicacionMovilDeTiendasExito() {

        PaginaPrincipal.cerrarModalPublicidad();
        PaginaPrincipal.verificarPaginaPrincipal();
    }

    @When("Cierro el modal de publicidad")
    public void cierroElModalDePublicidad() {
        PaginaPrincipal.cerrarModalPublicidad();
    }

    @And("Selecciono el boton Registrarse")
    public void seleccionoElBotonRegistrarse() {
        PaginaPrincipal.seleccionarBotonRegistrarse();
    }

    @And("Lleno en el formulario de registro los siguientes datos")
    public void llenoEnElFormularioDeRegistroLosSiguientesDatos(DataTable dataTable) {
        PaginaRegistro.llenarFormularioRegistroPorDataTable(dataTable);
    }

    @And("Acepto los terminos y condiciones y politicas de privacidad")
    public void aceptoLosTerminosYCondicionesYPoliticasDePrivacidad() {
        PaginaRegistro.aceptarTerminosYCondiciones();
        PaginaRegistro.aceptarPoliticasDePrivadicad();
    }

    @And("Selecciono el boton confirmar")
    public void seleccionoElBotonConfirmar() {
        PaginaRegistro.seleccionarBotonConfirmar();
    }

    @Then("Deberia visualizar la ventana de correo de confirmacion")
    public void deberiaVisualizarLaVentanaDeCorreoDeConfirmacion() {
        PaginaRegistro.validarVentanaCorreoConfirmacion();
    }
    @And("Selecciono como metodo de envio compra y recoge")
    public void seleccionoComoMetodoDeEnvioCompraYRecoge() {
        PaginaProductos.seleccionarMetodosEnvio();
    }

    @And("Selecciono la ciudad {string} y almacen {string}")
    public void seleccionoLaCiudadYAlmacen(String ciudad, String almacen) {
        PaginaProductos.seleccionarCiudadYAlmacen(ciudad, almacen);
    }

    @And("Selecciono el boton Continuar")
    public void seleccionoElBotonContinuar() {
        PaginaProductos.seleccionarBotonContinuar();
    }

    @And("Agrego un producto")
    public void agregoUnProducto() {
        nombreProducto = PaginaProductos.agregarPrimerProducto();
    }

    @And("Selecciono el carrito de compras")
    public void seleccionoElCarritoDeCompras() {
        PaginaProductos.seleccionarCarritoCompras();
    }

    @Then("Visualizare el producto agregado correctamente al carrito")
    public void visualizareElProductoAgregadoCorrectamenteAlCarrito() {
    }

    @And("Selecciono el boton Ingresar")
    public void seleccionoElBotonIngresar() {
        PaginaPrincipal.seleccionarBotonIngresar();
    }

    @And("Ingreso el correo Electronico {string}")
    public void ingresoElCorreoElectronico(String email) {
        PaginaLogeo.ingresoEmail(email);
    }

    @And("Ingreso la contraseña {string}")
    public void ingresoLaContrasena(String contraseña) {
        PaginaLogeo.ingresoContraseña(contraseña);
    }

    @Then("Deberia visualizar la pantalla principal")
    public void deberiaVisualizarLaPantallaPrincipal() {
        PaginaProductos.verificarPantallaProductos();
    }

    @And("Otorgo permisos a la app sobre mi ubicacion")
    public void otorgoPermisosALaAppSobreMiUbicacion() {
        PaginaProductos.otorgarPermisosSobreUbicacion();
    }

    @And("Selecciono la seccion Jugueteria")
    public void seleccionoLaSeccionJugueteria() {
        PaginaProductos.seleccionarSeccionJugueteria();
    }
}

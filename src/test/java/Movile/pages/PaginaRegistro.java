package Movile.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import io.cucumber.datatable.DataTable;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.webdriver.WebDriverFacade;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaginaRegistro extends PageObject {

    private static final String REGISTRO_INPUT_TEMPLATE = "//android.widget.EditText[@text=\"ReplaceMe\"]";
    private static final String ANO_NACIMIENTO_INPUT = "com.exito.appcompania:id/filled_exposed_dropdown_anio_registro";
    private static final String MES_NACIMIENTO_INPUT = "com.exito.appcompania:id/filled_exposed_dropdown_mes_registro";
    private static final String DIA_NACIMIENTO_INPUT = "com.exito.appcompania:id/filled_exposed_dropdown_dia_registro";
    private static final String TELEFONO_INPUT = "com.exito.appcompania:id/TextInputEditText_tel";
    private static final String CORREO_CONFIRMACION_TITULO = "//android.widget.TextView[@resource-id=\"com.exito.appcompania:id/AppCompatTextView_bienvenido\"]";
    private static final String CORREO_ELECTRONICO_INPUT = "//android.widget.EditText[@text=\"Correo electrónico\"]";
    private static final String BOTON_CONFIRMAR = "com.exito.appcompania:id/AppCompatButton_registrar";

    public void llenarFormularioRegistroPorDataTable(DataTable dataTable) {
        List<List<String>> rows = dataTable.asLists(String.class);

        WebElementFacade nombresInput = find(By.xpath(REGISTRO_INPUT_TEMPLATE.replace("ReplaceMe", "Nombres")));
        WebElementFacade apellidosInput = find(By.xpath(REGISTRO_INPUT_TEMPLATE.replace("ReplaceMe", "Apellidos")));
        WebElementFacade numeroDocumentoInput = find(By.xpath(REGISTRO_INPUT_TEMPLATE.replace("ReplaceMe", "Número de documento")));
        WebElementFacade anoNacimientoInput = find(By.id(ANO_NACIMIENTO_INPUT));
        WebElementFacade mesNacimientoInput = find(By.id(MES_NACIMIENTO_INPUT));
        WebElementFacade diaNacimientoInput = find(By.id(DIA_NACIMIENTO_INPUT));
        WebElementFacade telefonoInput = find(By.id(TELEFONO_INPUT));
        WebElementFacade correoElectronicoInput = find(By.xpath(CORREO_ELECTRONICO_INPUT));

        // Llenar los campos con los valores del DataTable
        llenarCampo(nombresInput, rows.get(1).get(0));
        llenarCampo(apellidosInput, rows.get(1).get(1));
        llenarCampo(numeroDocumentoInput, rows.get(1).get(2));
        // Seleccionar año, mes y día de nacimiento
        seleccionarValorDropdown(anoNacimientoInput, mesNacimientoInput, diaNacimientoInput, rows.get(1).get(3));
        llenarCampo(telefonoInput, rows.get(1).get(4));
        llenarCampo(correoElectronicoInput, rows.get(1).get(5));
    }

    private void llenarCampo(WebElementFacade campo, String valor) {
        campo.type(valor);
    }

    public void seleccionarBotonConfirmar() {
        find(By.id(BOTON_CONFIRMAR)).click();
    }
    private void seleccionarValorDropdown(WebElementFacade ano, WebElementFacade mes, WebElementFacade dia, String fechaNacimiento) {
        List<String> fechaNacimientoList = List.of(fechaNacimiento.split("-"));
        llenarCampo(ano, fechaNacimientoList.get(0));
        llenarCampo(mes, fechaNacimientoList.get(1));
        llenarCampo(dia, fechaNacimientoList.get(2));
    }

    public void aceptarPoliticasDePrivadicad() {
        tocarEnCoordenadas(140,2131);
    }

    public void aceptarTerminosYCondiciones() {
        tocarEnCoordenadas(138,2243);
    }
    public void tocarEnCoordenadas(int x, int y) {
        WebDriverFacade facade = (WebDriverFacade) getDriver();
        AppiumDriver appiumDriver = (AppiumDriver) facade.getProxiedDriver();
        AndroidTouchAction touch = new AndroidTouchAction((PerformsTouchActions) appiumDriver);
        touch.tap(PointOption.point(x, y)).perform();
    }

    public void validarVentanaCorreoConfirmacion() {
        Assert.assertTrue(find(By.xpath(CORREO_CONFIRMACION_TITULO)).isPresent());
    }
}

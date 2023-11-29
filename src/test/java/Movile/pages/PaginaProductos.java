package Movile.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.offset.PointOption;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.webdriver.WebDriverFacade;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;


public class PaginaProductos extends PageObject {;
    public static final String OFFERTA_BANNER = "com.exito.appcompania:id/imageViewBanner";
    public static final String CIUDAD_LISTA = "com.exito.appcompania:id/filled_exposed_dropdown_city";
    public static final String ALMACEN_LISTA = "com.exito.appcompania:id/filled_exposed_dropdown_store";
    public static final String CARRITO_COMPRAS_BOTON = "com.exito.appcompania:id/appCompatImageView_shopping_cart";
    public static final String COMPRA_RECOGE_BOTON = "//android.view.ViewGroup[@resource-id=\"com.exito.appcompania:id/constraitLayout_buy_and_collect\"]/android.view.ViewGroup";
    public static final String CONFIRMAR_BOTON = "com.exito.appcompania:id/appCompatButton_continue";
    public static final String BOTON_CATEGORIA = "(//android.widget.ImageView[@resource-id=\"com.exito.appcompania:id/imageView_hall\"])[2]";
    public static final String ONLY_THIS_TIME_BUTTON = "com.android.permissioncontroller:id/permission_allow_one_time_button";
    public void otorgarPermisosSobreUbicacion() {
        find(By.id(ONLY_THIS_TIME_BUTTON)).click();
    }

    public void verificarPantallaProductos() {
        assert find(By.id(OFFERTA_BANNER)).isVisible();
    }

    public void seleccionarSeccionJugueteria() {
        find(By.xpath(BOTON_CATEGORIA)).click();
    }

    public void seleccionarMetodosEnvio() {
        find(By.xpath(COMPRA_RECOGE_BOTON)).click();
    }

    public void seleccionarCiudadYAlmacen(String ciudad, String almacen) {
        find(By.id(CIUDAD_LISTA)).click();
        tocarEnCoordenadas(208, 822);
        find(By.id(ALMACEN_LISTA)).click();
        tocarEnCoordenadas(551, 1740);
    }

    public void tocarEnCoordenadas(int x, int y) {
        WebDriverFacade facade = (WebDriverFacade) getDriver();
        AppiumDriver appiumDriver = (AppiumDriver) facade.getProxiedDriver();
        AndroidTouchAction touch = new AndroidTouchAction((PerformsTouchActions) appiumDriver);
        touch.tap(PointOption.point(x, y)).perform();
    }

    public void seleccionarBotonContinuar() {
        find(By.id(CONFIRMAR_BOTON)).click();
    }

    public String  agregarPrimerProducto() {
        // Seleccionar el contenedor del primer producto
        WebElementFacade contenedorProducto = find(By.xpath("(//android.view.ViewGroup[@resource-id=\"com.exito.appcompania:id/constraintLayout_plp_item\"])[1]"));

        // Obtener el nombre del producto
        String nombreProducto = contenedorProducto.find(By.id("com.exito.appcompania:id/appCompatTextView_price")).getText();

        // Obtener el precio del producto
        String precioProducto = contenedorProducto.find(By.id("com.exito.appcompania:id/appCompatTextView_price")).getText();

        // Hacer clic en el botón del producto
        contenedorProducto.find(By.id("com.exito.appcompania:id/appCompatTextView_price")).click();
        return nombreProducto;
    }

    public void seleccionarCarritoCompras() {
        find(By.id(CARRITO_COMPRAS_BOTON)).click();
    }
}

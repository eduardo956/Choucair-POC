package starter.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.annotations.DefaultUrl;
import org.openqa.selenium.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import starter.CucumberTestSuite;

import java.time.Duration;

@DefaultUrl(CucumberTestSuite.BASE_URL)
public class PaginaPrincipal extends PageObject {
    private static final String MODAL_BOTON_CERRAR_SELECTOR = "wps-overlay-close-button";
    private static final String MENU_BOTON_SELECTOR = "exito-category-menu-3-x-button";
    private static final String CARRITO_BOTON_SELECTOR = "exito-header-3-x-minicartLink";
    private static final String CATEGORIA_XPATH_TEMPLATE = "//p[text()='%s']";
    private static final String SUBCATEGORIA_XPATH_TEMPLATE = "//a//p[contains(text(),'%s')]";
    @FindBy(id = MODAL_BOTON_CERRAR_SELECTOR)
    private WebElementFacade modalCloseButton;
    @FindBy(className = MENU_BOTON_SELECTOR)
    private WebElementFacade menuButton;
    @FindBy(className = CARRITO_BOTON_SELECTOR)
    private WebElementFacade carritoButton;

    public void cerrarModal() {
        modalCloseButton.waitUntilVisible().withTimeoutOf(Duration.ofSeconds(10)).click();
    }

    public void seleccionarMenu() {
        menuButton.click();
    }
    public void seleccionarCategoriaPorNombre(String nombreCategoria, String subCategoria) {
        By categoriaSelector = By.xpath(String.format(CATEGORIA_XPATH_TEMPLATE, nombreCategoria));
        By subCategoriaSelector = By.xpath(String.format(SUBCATEGORIA_XPATH_TEMPLATE, subCategoria));

        WebElementFacade subCategoriaElement = find(subCategoriaSelector);
        WebElementFacade categoriaElement = find(categoriaSelector);
        categoriaElement.click();
        subCategoriaElement.click();
    }
    public void seleccionarCarrito() {
        carritoButton.waitUntilClickable().withTimeoutOf(Duration.ofSeconds(10)).click();
    }
}

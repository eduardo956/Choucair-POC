package WEB.pages;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.core.annotations.findby.FindBy;
import WEB.CucumberTestSuite;
import java.time.Duration;

@DefaultUrl(CucumberTestSuite.BASE_URL)
public class PaginaPrincipal extends PageObject {
    private static final String MODAL_BOTON_CERRAR_SELECTOR = "wps-overlay-close-button";
    private static final String MENU_BOTON_SELECTOR = "exito-category-menu-3-x-button";
    private static final String CARRITO_BOTON_SELECTOR = "exito-header-3-x-minicartLink";
    private static final String CATEGORIA_XPATH_TEMPLATE = "//p[text()='replaceMe']";
    private static final String SUBCATEGORIA_XPATH_TEMPLATE = "//a//p[contains(text(),'replaceMe')]";
    @FindBy(className = MENU_BOTON_SELECTOR)
    private WebElementFacade menuButton;
    @FindBy(className = CARRITO_BOTON_SELECTOR)
    private WebElementFacade carritoButton;

    public void cerrarModal() {
        WebElementFacade modalCloseButton = find(By.id(MODAL_BOTON_CERRAR_SELECTOR));
        modalCloseButton.waitUntilClickable().click();
    }

    public void seleccionarMenu() {
        menuButton.click();
    }

    public void seleccionarCategoriaPorNombre(String nombreCategoria, String subCategoria) {
        String categoriaSelector = CATEGORIA_XPATH_TEMPLATE.replace("replaceMe", nombreCategoria);
        String subCategoriaSelector = SUBCATEGORIA_XPATH_TEMPLATE.replace("replaceMe", subCategoria);

        WebElementFacade categoriaElement = find(categoriaSelector);
        WebElementFacade subCategoriaElement = find(subCategoriaSelector);
        categoriaElement.click();
        subCategoriaElement.click();
    }

    public void seleccionarCarrito() {
        carritoButton.waitUntilClickable().withTimeoutOf(Duration.ofSeconds(10)).click();
    }
}

package starter.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.annotations.DefaultUrl;
import org.openqa.selenium.By;

import java.time.Duration;

@DefaultUrl("https://www.exito.com/")
public class paginaPrincipal extends PageObject {
    private By modalCloseButtonSelector = By.id("wps-overlay-close-button");
    private By menuButtonSelector = By.className("exito-category-menu-3-x-button");
    public void cerrarModal() {
        WebElementFacade closeButton = find(modalCloseButtonSelector)
                .withTimeoutOf(Duration.ofSeconds(10))
                .waitUntilVisible();

        if (closeButton.isCurrentlyVisible()) {
            closeButton.click();
        }
    }

    public void seleccionarMenu() {
        find(menuButtonSelector).click();
    }

    public void seleccionarCategoriaPorNombre(String nombreCategoria, String subCategoria) {
        By categoriaSelector = By.xpath("//p[text()='" + nombreCategoria + "']");
        By subCategoriaSelector = By.xpath("//strong[contains(text(),'" + subCategoria + "')]" );

        WebElementFacade subCategoriaElement = find(subCategoriaSelector);
        WebElementFacade categoriaElement = find(categoriaSelector);
        categoriaElement.click();
        subCategoriaElement.click();
    }

}

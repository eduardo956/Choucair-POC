package starter.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class subCategoriaPage extends PageObject{
    private By modalCloseButtonSelector = By.cssSelector("span[class=\"exito-geolocation-3-x-cursorPointer\"]" );
    public void cerrarModal() {

        WebElementFacade closeButton = find(modalCloseButtonSelector)
                .withTimeoutOf(Duration.ofSeconds(15))
                .waitUntilVisible();

        if (closeButton.isCurrentlyVisible()) {
            closeButton.click();
        }
    }

    public void eligirProductosconCantidadAleatoria(int cantidadMinima, int cantidadMaxima) {
        List<WebElementFacade> mainBuyButtons = findAll(By.className("exito-vtex-components-4-x-mainBuyButton"));

        // Obtener el número total de elementos
        int totalButtons = mainBuyButtons.size();

        // Generar un número aleatorio entre 0 y totalButtons-1
        int randomIndex = new Random().nextInt(totalButtons);

        // Obtener el WebElement en la posición aleatoria
        WebElementFacade randomButton = mainBuyButtons.get(randomIndex);

        // Hacer clic en el botón aleatorio
        randomButton.click();
    }
}

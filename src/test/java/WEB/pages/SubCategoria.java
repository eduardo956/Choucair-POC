package WEB.pages;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.JavascriptExecutor;
import WEB.utils.Producto;

import java.util.*;

public class SubCategoria extends PageObject {
    private static final String GALERIA_DE_PRODUCTOS_SELECTOR = "gallery-layout-container";
    private static final String PRODUCTOS_SELECTOR = "vtex-search-result-3-x-galleryItem";
    private static final String BOTON_COMPRAR_SELECTOR = "exito-vtex-components-4-x-mainBuyButton";
    private static final String BOTON_AGREGAR_CANTIDAD_SELECTOR = "shelf-exito-vtex-components-buy-button-manager-more";
    private static final String NOMBRE_PRODUCTO_SELECTOR = "vtex-store-components-3-x-productNameContainer";
    private static final String PRECIO_PRODUCTO_SELECTOR = ".exito-vtex-components-4-x-PricePDP .exito-vtex-components-4-x-currencyContainer";
    @FindBy(id = GALERIA_DE_PRODUCTOS_SELECTOR)
    private WebElementFacade galleryLayoutContainer;

    public List<Producto> elegirProductosConCantidadAleatoriayRetornarlos(int numeroProductos, int cantidadMinima, int cantidadMaxima) {
        List<WebElementFacade> productosAleatorios = obtenerProductosAleatorios(numeroProductos);
        return agregarProductosConCantidad(productosAleatorios, cantidadMinima, cantidadMaxima);
    }

    private List<WebElementFacade> obtenerProductosAleatorios(int numeroProductos) {
        waitFor(galleryLayoutContainer).isPresent();

        List<WebElementFacade> productosList = galleryLayoutContainer.thenFindAll(By.className(PRODUCTOS_SELECTOR));
        Set<Integer> indicesElegidos = new HashSet<>();
        List<WebElementFacade> productosAleatorios = new ArrayList<>();

        for (int i = 0; i < numeroProductos; i++) {
            int randomIndex;
            do {
                randomIndex = new Random().nextInt(productosList.size());
            } while (indicesElegidos.contains(randomIndex));

            indicesElegidos.add(randomIndex);
            productosAleatorios.add(productosList.get(randomIndex));
        }

        return productosAleatorios;
    }

    private List<Producto> agregarProductosConCantidad(List<WebElementFacade> productos, int cantidadMinima, int cantidadMaxima) {
        List<Producto> productosList = new ArrayList<>();

        for (WebElementFacade randomProduct : productos) {
            WebElementFacade randomButton = randomProduct.find(By.className(BOTON_COMPRAR_SELECTOR));
            scrollToElement(randomButton);
            randomButton.waitUntilClickable().click();

            int cantidadAleatoria = new Random().nextInt(cantidadMaxima - cantidadMinima + 1) + cantidadMinima;

            // Obtener nombre y precio del producto
            String nombreProducto = obtenerNombreProducto(randomProduct);
            double precioProducto = obtenerPrecioProducto(randomProduct);

            // Crear objeto Producto y agregarlo a la lista
            Producto producto = new Producto(nombreProducto, precioProducto*cantidadAleatoria, cantidadAleatoria);
            productosList.add(producto);

            WebElementFacade randomAddButton = randomProduct.find(By.className(BOTON_AGREGAR_CANTIDAD_SELECTOR));

            for (int i = 1; i < cantidadAleatoria; i++) {
                randomAddButton.waitUntilClickable().click();
            }
        }
        return productosList;
    }

    private void scrollToElement(WebElementFacade element) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'});", element);

        // Esperar a que la animación de desplazamiento termine
        try {
            Thread.sleep(1000); // Puedes ajustar el tiempo de espera según sea necesario
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String obtenerNombreProducto(WebElementFacade producto) {
        WebElementFacade nombreContainer = producto.find(By.className(NOMBRE_PRODUCTO_SELECTOR));
        return nombreContainer.getText();
    }

    private double obtenerPrecioProducto(WebElementFacade producto) {
        WebElementFacade precioContainer = producto.find(By.cssSelector(PRECIO_PRODUCTO_SELECTOR));
        String precioTexto = precioContainer.getText();

        // Eliminar caracteres no numéricos y convertir la cadena a double
        precioTexto = precioTexto.replaceAll("[^0-9.]", "");

        // Reemplazar la coma (",") por el punto (".") como separador decimal
        precioTexto = precioTexto.replace(".", "");

        try {
            return Double.parseDouble(precioTexto);
        } catch (NumberFormatException e) {
            // Manejar la excepción, por ejemplo, imprimir un mensaje de error
            System.err.println("Error al convertir el precio: " + precioTexto);
            e.printStackTrace();
            return 0.0;
        }
    }



}

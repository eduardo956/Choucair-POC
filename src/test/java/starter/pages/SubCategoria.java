package starter.pages;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import starter.utils.Producto;

import java.util.*;

public class SubCategoria extends PageObject {
    private static final String GALERIA_DE_PRODUCTOS_SELECTOR = "gallery-layout-container";
    private static final String PRODUCTOS_SELECTOR = "vtex-search-result-3-x-galleryItem";
    private static final String BOTON_COMPRAR_SELECTOR = "exito-vtex-components-4-x-mainBuyButton";
    private static final String BOTON_AGREGAR_CANTIDAD_SELECTOR = "shelf-exito-vtex-components-buy-button-manager-more";
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
            randomButton.click();

            WebElementFacade randomAddButton = randomProduct.find(By.className(BOTON_AGREGAR_CANTIDAD_SELECTOR));
            randomAddButton.waitUntilVisible();

            int cantidadAleatoria = new Random().nextInt(cantidadMaxima - cantidadMinima + 1) + cantidadMinima;

            // Obtener nombre y precio del producto
            String nombreProducto = obtenerNombreProducto(randomProduct);
            double precioProducto = obtenerPrecioProducto(randomProduct);

            // Crear objeto Producto y agregarlo a la lista
            Producto producto = new Producto(nombreProducto, precioProducto, cantidadAleatoria);
            productosList.add(producto);

            for (int i = 1; i < cantidadAleatoria; i++) {
                randomAddButton.click();
            }
        }

        return productosList;
    }

    private String obtenerNombreProducto(WebElementFacade producto) {
        // Lógica para obtener el nombre del producto a partir del WebElementFacade
        // Reemplaza esto con tu lógica real
        return "Nombre del Producto";
    }

    private double obtenerPrecioProducto(WebElementFacade producto) {
        // Lógica para obtener el precio del producto a partir del WebElementFacade
        // Reemplaza esto con tu lógica real
        return 10.0;
    }

}

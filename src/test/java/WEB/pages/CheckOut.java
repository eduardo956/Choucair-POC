package WEB.pages;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import WEB.utils.Producto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CheckOut  extends PageObject {

    private static final String EMAIL_INPUT_SELECTOR = "input[type='email']";
    private static final String BOTON_ACEPTAR_SELECTOR = "button[type='submit']";
    private static final String CONTENEDOR_PRODUCTO_SELECTOR = "div[data-molecule-product-detail='true']";
    private static final String PRECIO_TOTAL_SELECTOR = ".exito-checkout-io-0-x-summaryTotal span[data-molecule-summary-item-value='true']";
    private static final String NOMBRE_PRODUCTO_SELECTOR = "div[data-molecule-product-detail-name='true'] span[data-molecule-product-detail-name-span='true']";
    private static final String PRECIO_PRODUCTO_SELECTOR = "div[data-molecule-product-detail-price-best-price='true'] span";
    private static final String CANTIDAD_PRODUCTO_SELECTOR = "div[data-molecule-product-detail-quantity='true'] span[data-molecule-quantity-und-value='true']";

    public void ingresarEmail(String email) {
        WebElementFacade emailInput = find(By.cssSelector(EMAIL_INPUT_SELECTOR));
        WebElementFacade botonAceptar = find(By.cssSelector(BOTON_ACEPTAR_SELECTOR));
        emailInput.waitUntilVisible().sendKeys(email);
        botonAceptar.click();
    }

    private List<Producto> recuperarProductosEnCarrito() {
        List<WebElementFacade> contenedoresProductos = findAll(By.cssSelector(CONTENEDOR_PRODUCTO_SELECTOR));

        return contenedoresProductos.stream().map(contenedor -> {
            WebElementFacade nombreProductoElement = contenedor.find(By.cssSelector(NOMBRE_PRODUCTO_SELECTOR));
            WebElementFacade precioProductoElement = contenedor.find(By.cssSelector(PRECIO_PRODUCTO_SELECTOR));
            WebElementFacade cantidadProductoElement = contenedor.find(By.cssSelector(CANTIDAD_PRODUCTO_SELECTOR));

            String nombreProducto = nombreProductoElement.getText().trim();
            String precioProductoTexto = precioProductoElement.getText().replaceAll("[^0-9]", ""); // Elimina todo excepto los dígitos
            double precioProducto = Double.parseDouble(precioProductoTexto);
            int cantidadProducto = Integer.parseInt(cantidadProductoElement.getText().trim());

            return new Producto(nombreProducto, precioProducto, cantidadProducto);
        }).collect(Collectors.toList());
    }

    public void validarNombres(List<Producto> productosRecuperados) {
        List<Producto> productosEnCarrito = recuperarProductosEnCarrito();

        List<String> nombresEnCarrito = productosRecuperados.stream().map(Producto::getNombre).toList();

        for (Producto producto : productosEnCarrito) {
            // Realiza la comparación según tus necesidades
            assert nombresEnCarrito.contains(producto.getNombre()) : "Error en la validación del nombre del producto: "
                    + "Producto en el carrito: " + producto.getNombre()
                    + ", Productos esperados: " + nombresEnCarrito;
        }
    }

    public void validarPrecios(List<Producto> productosRecuperados) {
        double totalPreciosEnCarrito = recuperarProductosEnCarrito().stream()
                .mapToDouble(Producto::getPrecioTotal)
                .sum();

        double totalPreciosRecuperados = productosRecuperados.stream()
                .mapToDouble(Producto::getPrecioTotal)
                .sum();

        double totalCarrito = obtenerTotalCarrito();

        String mensajeError = String.format("Error en la validación del total de precios de los productos: Total en el carrito: %.2f, Total recuperado: %.2f", totalPreciosEnCarrito, totalPreciosRecuperados);
        assert totalPreciosRecuperados == totalPreciosEnCarrito : mensajeError;
        assert totalPreciosEnCarrito == totalCarrito : mensajeError;
    }

    public void validarCantidades(List<Producto> productosRecuperados) {
        List<Integer> cantidadesEnCarrito = recuperarProductosEnCarrito().stream()
                .map(Producto::getCantidad)
                .sorted()
                .toList();

        List<Integer> cantidadesRecuperadas = productosRecuperados.stream()
                .map(Producto::getCantidad)
                .sorted()
                .toList();

        assert cantidadesRecuperadas.equals(cantidadesEnCarrito) : "Error en la validación de las cantidades de los features: "
                + "Cantidades en el carrito: " + cantidadesEnCarrito
                + ", Cantidades esperadas: " + cantidadesRecuperadas;
    }

    public void validarNumeroDeProductos(List<Producto> productosRecuperados) {
        int numeroProductosEnCarrito = recuperarProductosEnCarrito().size();
        int numeroProductosRecuperados = productosRecuperados.size();

        assert numeroProductosRecuperados == numeroProductosEnCarrito : "Error en la validación del número de productos en el carrito: "
                + "Número de productos en el carrito: " + numeroProductosEnCarrito
                + ", Número de productos esperados: " + numeroProductosRecuperados;
    }

    private double obtenerTotalCarrito() {
        WebElementFacade totalElement = find(By.cssSelector(PRECIO_TOTAL_SELECTOR));
        String totalText = totalElement.getText().replaceAll("[^0-9]", "");

        return Double.parseDouble(totalText);
    }

    public void validarTodosLosProductosDelCarrito(List<Producto> productosRecuperados) {
        List<String> errores = new ArrayList<>();

        try {
            validarNombres(productosRecuperados);
        } catch (AssertionError e) {
            errores.add(e.getMessage());
        }

        try {
            validarPrecios(productosRecuperados);
        } catch (AssertionError e) {
            errores.add(e.getMessage());
        }

        try {
            validarCantidades(productosRecuperados);
        } catch (AssertionError e) {
            errores.add(e.getMessage());
        }

        try {
            validarNumeroDeProductos(productosRecuperados);
        } catch (AssertionError e) {
            errores.add(e.getMessage());
        }

        if (!errores.isEmpty()) {
            throw new AssertionError("La prueba falló con los siguientes errores:\n" + String.join("\n", errores));
        }
    }

}

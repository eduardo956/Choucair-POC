package WEB.utils;
import net.serenitybdd.core.Serenity;

import java.util.List;

public class ContextSerenity {
    public void guardarContextoProductos(String clave, List<Producto> productos) {
        Serenity.setSessionVariable(clave).to(productos);
    }

    public List<Producto> obtenerContextoProductos(String clave) {
        return Serenity.sessionVariableCalled(clave);
    }
}

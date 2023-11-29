package Movile.pages;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.PageObject;

public class PaginaPrincipal extends PageObject {
    public static final String LOGO_REGISTRARSE = "com.exito.appcompania:id/AppCompatImageView_logo_exito";
    public static final String BOTON_REGISTRARSE = "com.exito.appcompania:id/AppCompatButton_registrarse";
    public static final String BOTON_CERRAR_MODAL = "com.exito.appcompania:id/collapse_button";
    public static final String BOTON_INGRESAR = "com.exito.appcompania:id/AppCompatButton_ingresar";

    public void verificarPaginaPrincipal() {
        assert find(By.id(LOGO_REGISTRARSE)).isVisible();
    }

    public void seleccionarBotonRegistrarse() {
        find(By.id(BOTON_REGISTRARSE)).click();
    }

    public void cerrarModalPublicidad() {
        try {
            find(By.id(BOTON_CERRAR_MODAL)).click();
        } catch (Exception e) {
            System.out.println("No se pudo cerrar el modal de publicidad ");
        }
    }

    public void seleccionarBotonIngresar() {
        find(By.id(BOTON_INGRESAR)).click();
    }
}

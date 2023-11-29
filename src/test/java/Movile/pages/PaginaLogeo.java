package Movile.pages;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.PageObject;

public class PaginaLogeo extends PageObject {
    public static final String EMAIL_INPUT = "com.exito.appcompania:id/TextInputEditText_email";
    public static final String CONTRASEÑA_INPUT = "//android.widget.EditText[@text=\"Contraseña\"]";

    public void ingresoEmail(String email) {
        find(By.id(EMAIL_INPUT)).type(email);
    }

    public void ingresoContraseña(String contraseña) {
        find(By.xpath(CONTRASEÑA_INPUT)).type(contraseña);
    }
}

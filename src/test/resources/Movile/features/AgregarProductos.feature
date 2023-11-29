@AutomationMobile @AgregarProductoCarrito
Feature: Regostrarse y Agregar producto al carrito en la aplicación móvil de Éxito

  Scenario: Validar que cuando lleno mis datos en la pantalla de registro y continue, me pida correo de confirmacion
    Given Me encuentro en la aplicacion Movil de tiendas Exito
    And   Selecciono el boton Registrarse
    And   Lleno en el formulario de registro los siguientes datos
      | nombres | apellidos | numeroDocumento | fechaNacimiento | celular    | correoElectronico         |
      | Javier  | Martinez  | 6965656646      | 2000-03-20      | 3464646643 | eduardo250096@hotmail.com |
    And   Acepto los terminos y condiciones y politicas de privacidad
    And   Selecciono el boton confirmar
    Then  Deberia visualizar la ventana de correo de confirmacion

  Scenario: Validar que cuando me encuentro en la aplicacion Movil de Tienda Exito y me logeo con las credenciales correctas, podre visualizar la pantalla principal
    Given Me encuentro en la aplicacion Movil de tiendas Exito
    And   Selecciono el boton Ingresar
    And   Ingreso el correo Electronico "educito006@gmail.com"
    And   Ingreso la contraseña "Eduardo956%"
    And   Selecciono el boton Ingresar
    And   Otorgo permisos a la app sobre mi ubicacion
    Then  Deberia visualizar la pantalla principal


  @test
  Scenario: Validar que cuando me logeo y selecciono un producto, aparesca en el carrito de compras
    Given Me encuentro en la aplicacion Movil de tiendas Exito
    And   Selecciono el boton Ingresar
    And   Ingreso el correo Electronico "educito006@gmail.com"
    And   Ingreso la contraseña "Eduardo956%"
    And   Selecciono el boton Ingresar
    And   Otorgo permisos a la app sobre mi ubicacion
    And   Selecciono la seccion Jugueteria
    And   Selecciono como metodo de envio compra y recoge
    And   Selecciono la ciudad "Cali" y almacen "Exito Unicali"
    And   Selecciono el boton Continuar
    And   Agrego un producto
    And   Selecciono el carrito de compras
    Then  Visualizare el producto agregado correctamente al carrito

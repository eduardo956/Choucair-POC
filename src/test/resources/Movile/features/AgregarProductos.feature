@AutomationMobile @AgregarProductoCarrito
Feature: Regostrarse y Agregar producto al carrito en la aplicación móvil de Éxito

  Scenario: Validar que cuando me registro, inicio sesion y agrego un producto al carrito, el producto haya sido agregado correctamente al carrito
    Given Me encuentro en la aplicacion Movil de tiendas Exito

    When  Cierro el modal de publicidad
    And   Selecciono el boton Registrarse
    And   Lleno en el formulario de registro los siguientes datos

    And   Acepto los terminos y condiciones y politicas de privacidad
    And   Selecciono el boton confirmar
    And   Ingreso el codigo de confirmacion que llego a mi email
    And   Ingreso la contraseña
    And   Selecciono como metodo de envio compra y recoge
    And   Selecciono la ciudad "Cali" y almacen "Exito Chipichape"
    And   Selecciono el boton Continuar
    And   Agrego un producto
    And   Selecciono el carrito de compras
    Then  Visualizare el producto agregado correctamente al carrito

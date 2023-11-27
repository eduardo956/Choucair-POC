@AutomationWeb @AgregarProductos
Feature: Añadir productos al carrito de compras
  Background:
    Given Estoy en la página de Tiendas Éxito
    When  Elijo la categoría "Tecnología" y la subcategoría "Audífonos"
    And   Elijo 5 productos aleatoriamente con cantidades entre 1 y 10
    And   Selecciono el carrito de Compras
    And   Ingreso el email "eduardo250096@hotmail.com"

  Scenario: Validar que cuando agrego productos aleatoriamente, el nombre de los productos se veran en el carrito de compras
    Then El nombre de los productos agregados deberá coincidir con los nombres en el carrito

  Scenario: Validar que cuando agrego productos aleatoriamente, el precio de los productos deberan coincidir con el total del carrito de compras
    Then El total de los precios de los productos agregados deberá coincidir con los precios en el carrito

  Scenario: Validar que cuando agrego productos aleatoriamente, las cantidades de los productos agregados deberan coincidir con las cantidades del carrito de compras
    Then Las cantidades de los productos agregados deberán coincidir con las cantidades en el carrito

  Scenario: Validar que cuando agrego productos aleatoriamente, el numero de productos agregados deberan coincidir con el numero de productos del carrito de compras
    Then El número de productos agregados debe ser igual al número en el carrito
Feature: Añadir productos al carrito de compras
  Background:
    Given Estoy en la página de Tiendas Éxito
    When  Elijo la categoría "Celulares y accesorios" y la subcategoría "Samsung"
    And   Elijo 5 productos aleatoriamente con cantidades entre 1 y 10
    And   Selecciono el carrito de Compras
    And   Ingreso el email "eduardo250096@hotmail.com"

  Scenario: Verificar nombres de productos en el carrito
    Then El nombre de los productos agregados deberá coincidir con los nombres en el carrito

  Scenario: Verificar total de precios en el carrito
    Then El total de los precios de los productos agregados deberá coincidir con los precios en el carrito

  Scenario: Verificar cantidades de productos en el carrito
    Then Las cantidades de los productos agregados deberán coincidir con las cantidades en el carrito

  Scenario: Verificar número de productos en el carrito
    Then El número de productos agregados debe ser igual al número en el carrito


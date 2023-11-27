@AutomationWeb @AgregarProductosV2
Feature: Añadir productos al carrito de compras V2

    Scenario: Validar los productos en el carrito de compras cuando los agrego aleatoriamente
    Given Estoy en la página de Tiendas Éxito
    When  Elijo la categoría "Tecnología" y la subcategoría "Audífonos"
    And   Elijo 5 productos aleatoriamente con cantidades entre 1 y 10
    And   Selecciono el carrito de Compras
    And   Ingreso el email "eduardo250096@hotmail.com"
    Then  Valido los productos del carrito de compras
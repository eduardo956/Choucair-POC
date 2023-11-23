Feature: Añadir productos al carrito de compras
  @AñadirProductos
  Scenario: Agregar productos al carrito
    Given El robot está en la página de Tiendas Éxito
    When Elige una categoría y subcategoría
    And Elige 5 productos aleatoriamente con cantidades entre 1 y 10
    Then Valida que los productos en el carrito son correctos
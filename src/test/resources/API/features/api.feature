Feature: CRUD Empleados

  @Agregar
  Scenario: Validar que cuando no existo en la lista de empleados y me creo en el sistema, me pueda encontrar por id
    Given No existo en la lista de empleados
      | name | salary | age |
      | Edu  | 37643  | 23  |
    When Lo creo en el sistema
    Then Podre consultar mis datos por mi id

  @Actualizar
  Scenario: Validar que cuando existo en la lista de mpleados y actualizo mis datos, Podre encontrarme con esos datos en la lista de empleados
    Given Existo en la lista de empleados con los siguientes detalles
      | name            | salary | age |
      | Charde Marshall | 470600 | 36  |
    When Actualizo mis datos con los siguientes detalles
      | id | name | salary | age |
      | 13 | test | 45623  | 30  |
    Then Podre encontrarme con esos datos en la lista de empleados

  @Eliminar
  Scenario: Validar que cuando existo en la lista de empleados y me elimino, no deberia encontrarme en la lista de empleados
    Given Existo en la lista de empleados con los siguientes detalles
      | name            | salary | age |
      | Charde Marshall | 470600 | 36  |
    When Elimino mi registro con id 13
    Then No debería encontrarme en la lista de empleados




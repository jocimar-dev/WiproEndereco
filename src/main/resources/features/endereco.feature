Feature: Consulta de endereços pelo CEP
  Como um usuário
  Eu quero consultar o endereço usando o CEP
  Para obter informações detalhadas do endereço

  Scenario: Consultar endereço válido
    Given um CEP válido "01001-000"
    When eu consulto o endereço
    Then o endereço é retornado com sucesso

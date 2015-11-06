Feature: Como candidato quiero asegurarme que al postularme debo poner un email válido.

    Scenario: El usuario ingresa un email valido para postularse
        Given un usuario logueado en la aplicación
        When ingresa un nombre
        And ingresa un email valido
        Then se le notifica que se subieron los datos correctamente.

    Scenario: El usuario ingresa un email inválido para postularse
        Given un usuario logueado en la aplicación
        When ingresa un nombre
        And ingresa un email invalido
        Then se le notifica que dicho email no está permitido.

    Scenario: El usuario no ingresa un email para postularse
        Given un usuario logueado en la aplicación
        When ingresa un nombre
        And no ingresa email
        Then se le notifica que se requiere dicho campo.

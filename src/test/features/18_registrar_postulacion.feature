Feature: Como oferente quiero registrar quien se postulo al aplicar a una postulación.

    Scenario: Al realizarse una postulación a una oferta se registra quien se postuló.
        Given la oferta X ha sido creada
        When el usuario se postula a esa oferta de trabajo
        Then se registra quien se postuló a la oferta

    Scenario: Al ver el detalle de mis ofertas veo el listado de quienes se postularon.
        Given un usuario logueado
        When ve el detalle de una oferta
        Then se muestra la lista de los postulados.

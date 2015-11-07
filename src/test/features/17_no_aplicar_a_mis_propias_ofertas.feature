Feature: Como oferente quiero no aplicar a mis propias ofertas.

    Scenario: El usuario no puede postularse a una oferta de trabajo creada por el mismo
        Given el usuario esta logueado
        And crea una oferta de trabajo
        When el usuario quiere postularse a esa oferta de trabajo
        Then se le notifica que no puede postularse a una oferta que el creo

Feature: Como oferente quiero especificar un máximo de postulantes.

    Scenario: se especifica cantidad maxima de postulantes
        Given una oferta con un maximo de 10 postulantes
        When se alcanza el maximo de postulantes
        Then no se muestra la oferta

    Scenario: No se especifica cantidad maxima de postulantes
        Given una oferta sin maximo de postulantes
        When aplican los postulantes
        Then se muestra la oferta

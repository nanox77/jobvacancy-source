Feature: Como oferente quiero especificar un máximo de postulantes.

    Scenario: se especifica cantidad maxima de postulantes
        Given una oferta con un maximo de 10 postulantes
        When se alcanza el maximo de postulantes
        Then no aparece la oferta en la lista ofertas disponibles

    Scenario: No se especifica cantidad maxima de postulantes
        Given una oferta X sin maximo de postulantes
        When un usuario ve la lista de ofertas disponibles
        Then se muestra la oferta X en la lista de ofertas disponibles

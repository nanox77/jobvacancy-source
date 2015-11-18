Feature: Como oferente quiero saber la cantidad de postulaciones de cada una de mis ofertas

    Scenario: Se realiza una postulacion a la oferta.
        Given la oferta X esta creada y tiene 0 postulaciones
        When un usuario se postula a esa oferta        
        Then se incrementa la cantidad de postulaciones de la oferta en uno.

    Scenario: El usuario ve el detalle de sus ofertas.
        Given un usuario logueado en la aplicación
        When ve el detalle de alguna de sus ofertas        
        Then se muestra la cantidad de postulaciones realizadas para esa oferta
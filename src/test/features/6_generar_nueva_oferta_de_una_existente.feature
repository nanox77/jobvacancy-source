Feature: Como oferente quiero poder generar una nueva oferta a partir de una ya existente.

    Scenario: El usuario crea una nueva oferta a partir de una ya existente.
        Given el usuario está en la pagina My Offers
        When duplica una oferta de trabajo
        Then se abre la ventana de creación de ofertas con los datos pre-cargados de la oferta duplicada.

Feature: Como oferente quiero dar por finalizada una oferta

    Scenario: El oferente da por finalizada un oferta
        Given la oferta X pertenece al usuario Y
        When el usuario Y da por finalizada la oferta 
        Then la oferta X no se muestra en la lista de ofertas disponibles

    Scenario: Un usuario crea un oferta de trabajo X 
        Given no finalizó la oferta X And no se llegó al limite de postulaciones
        When otro usuario ve la lista de ofertas disponibles        
        Then se muestra la oferta X en dicha lista
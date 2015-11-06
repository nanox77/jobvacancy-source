Feature: Como candidato quiero poder adjuntar el link a mi CV al postularme.

    Scenario: El usuario ingresa un link valido al postularse.

        Given El usuario esta logueado
        And El usuario se postula a una oferta
        And El usuario ingresa nombre e email válido
        When El usuario ingresa un link a su CV valido https://micvenlared.com/link_al_cv
        Then Se envia el CV al oferente

    Scenario: El usuario ingresa un link inválido al postularse

        Given El usuario esta logueado
        And El usuario se postula a una oferta
        And El usuario ingresa nombre e email válido
        When El usuario ingresa un link inválido htp:/ww.micvenlared/*link_al_cv!
        Then se le notifica que dicho link no es valido

    Scenario: El usuario no ingresa un link al postularse

        Given El usuario esta logueado
        And El usuario se postula a una oferta
        And El usuario ingresa nombre e email válido
        When el usuario no ingresa link
        Then se le notifica que el campo es obligatorio

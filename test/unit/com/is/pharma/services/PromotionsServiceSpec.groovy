package com.is.pharma.services

import com.is.pharma.PromotionsService
import com.is.pharma.PromotionUpdateCommand
import com.is.pharma.model.Image
import com.is.pharma.model.Promotion
import exceptions.EntityNotFoundException
import grails.buildtestdata.mixin.Build
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(PromotionsService)
@Build([Image,Promotion])
class PromotionsServiceSpec extends Specification {

    def setup() {
        mockForConstraintsTests(PromotionUpdateCommand)
    }

    void "Update Promotion success"() {
        setup:
          def image = new Image().build()
          def promo =  new Promotion().build(image: image)
          def cmd = new PromotionUpdateCommand([id: promo.id, description:"update this"])

        when:
          def respond =service.updatePromotion(cmd.id,cmd)

        then:
          assert respond instanceof Promotion
          assert respond.description== cmd.description
    }

    void "Cant update cause does not exist"(){
        when:
          service.updatePromotion(1, null)

        then:
          thrown(EntityNotFoundException)
    }
}

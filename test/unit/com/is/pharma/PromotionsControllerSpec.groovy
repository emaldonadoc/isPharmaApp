package com.is.pharma

import com.is.pharma.model.Image
import com.is.pharma.model.Promotion
import exceptions.BadRequestException
import grails.converters.JSON
import grails.test.mixin.TestFor
import grails.test.mixin.TestMixin
import grails.test.mixin.domain.DomainClassUnitTestMixin
import grails.test.mixin.web.ControllerUnitTestMixin
import isphama.PromotionsService
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(PromotionsController)
@TestMixin([DomainClassUnitTestMixin,ControllerUnitTestMixin])
class PromotionsControllerSpec extends Specification {

    def setup(){
        mockDomain(Promotion)
        mockDomain(Image)
    }

	void "Get promotion list"() {
      setup:
        def image = new Image(extention: 'ext', image: "hereComeImageBase64")
        def promoCreated = new Promotion(
                date: new Date(),
                description: "Test promotion description",
                shortDescription: "Test", image:image).save(flush:true)
        def promoCreated2 =new Promotion(
                date: new Date(),
                description: "Test promotion description2",
                shortDescription: "Test2", image: image).save(flush:true)

      when:
        controller.list()

      then:
        assert response.status == 200
        def json = JSON.parse(response.text)
        def promo1 = json[0]
        assert promo1.description == promoCreated.description
        assert promo1.shortDescription == promoCreated.shortDescription
        def promo2 = json[1]
        assert promo2.description == promoCreated2.description
        assert promo2.shortDescription == promoCreated2.shortDescription
	}

   void "No promotion update with bad request"(){
       when:
         controller.update()

       then:
         assert response.status == 400
         assert response.errorMessage == "Validation exception.Bad Request"
         thrown(BadRequestException)
   }

   void "No promotion update with bad date rate"(){
       setup:
        /* def promo2Update =
               new Promotion(date:new Date().minus(20), description: "desc", shortDescription: "short").save(flush: true)
         PromotionsService promotionsService = Mock()
         promotionsService.updatePromotion {-> promo2Update}*/
         def cmd = new PromotionUpdateCommand(id:1,date: new Date().minus(30))

       when:
         controller.update(cmd)

       then:
         assert response.status == 400
   }
}

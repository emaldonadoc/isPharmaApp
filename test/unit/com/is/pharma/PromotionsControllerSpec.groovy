package com.is.pharma

import com.is.pharma.model.Image
import com.is.pharma.model.Promotion
import exceptions.BadRequestException
import grails.converters.JSON
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import grails.test.mixin.TestMixin
import grails.test.mixin.domain.DomainClassUnitTestMixin
import isphama.PromotionsService
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(PromotionsController)
@TestMixin(DomainClassUnitTestMixin)
@Mock([Promotion, Image])
class PromotionsControllerSpec extends Specification {

    def setup(){
      mockForConstraintsTests(PromotionUpdateCommand)
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

   @Unroll
   void "Assert petitions with command #promoCommand"(){
       setup:
         PromotionUpdateCommand cmd = new PromotionUpdateCommand(promoCommand)

       when:
         controller.update(cmd)

       then:
         assert response.status == statusExpect
         assert response.errorMessage == "Validation exception.Bad Request"
         thrown(BadRequestException)

       where:
         promoCommand                       || statusExpect
         [:]                                || 400
         [date: new Date()]                 || 400
         [id:1, date: new Date().minus(100)]|| 400
         [id:1, date: new Date(), image: ""]|| 400
         [id:1, date: new Date(), description: ""]|| 400
         [id:1, date: new Date(), shortDescription: ""]|| 400

   }
}

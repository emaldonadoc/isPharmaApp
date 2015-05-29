package com.is.pharma

import com.is.pharma.model.Images
import com.is.pharma.model.Promotions
import grails.converters.JSON
import grails.test.mixin.TestFor
import grails.test.mixin.TestMixin
import grails.test.mixin.domain.DomainClassUnitTestMixin
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(PromotionsController)
@TestMixin(DomainClassUnitTestMixin)
class PromotionsControllerSpec extends Specification {

    def setup(){
        mockDomain(Promotions)
        mockDomain(Images)
    }

	void "Get promotion list"() {
      setup:
        def image = new Images(extention: 'ext', image: "hereComeImageBase64")
        def promoCreated = new Promotions(
                date: new Date(),
                description: "Test promotion description",
                shortDescription: "Test", image:image).save(flush:true)
        def promoCreated2 =new Promotions(
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
}

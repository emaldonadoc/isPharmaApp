package com.is.pharma

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
    }

	void "Get promotion list"() {
      setup:
        def promoCreated = new Promotions(
                date: new Date(),
                description: "Test promotion description",
                shortDescription: "Test").save(flush:true)
        def promoCreated2 =new Promotions(
                date: new Date(),
                description: "Test promotion description2",
                shortDescription: "Test2").save(flush:true)

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

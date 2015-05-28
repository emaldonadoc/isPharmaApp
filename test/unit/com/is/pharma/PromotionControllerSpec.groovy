package com.is.pharma

import grails.test.mixin.TestFor
import spock.lang.Specification
import grails.converters.JSON
/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(PromotionController)
class PromotionControllerSpec extends Specification {

    void "Get list promotions"() {
      when:
      def response =controller.list();

      then:
      def objJson = JSON.parse(response)
      assert objJson.promotions

    }
}

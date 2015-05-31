package com.is.pharma

import com.is.pharma.model.Image
import com.is.pharma.model.Promotion
import exceptions.BadRequestException
import grails.converters.JSON
import grails.validation.Validateable
import isphama.PromotionsService


class PromotionsController {
    static allowedMethods = [list:'GET']

    PromotionsService promotionsService

    def list(){
        def result = Promotion.getAll()
        def resp = (result as JSON).toString()
        render (contentType: 'application/json', text: resp)
    }

    def update(PromotionUpdateCommand cmd){
        def respond = ""
        if(cmd.hasErrors() || !cmd.validate()) {
            response.sendError( 400,"Validation exception.Bad Request")
            throw new BadRequestException("Validation update error", cmd.errors)
        }

        respond = (promotionsService.updatePromotion(cmd) as JSON).toString()

        render(contentType: 'application/json', text: respond)
    }

}

@Validateable
class PromotionUpdateCommand{
    Long id
    Date date
    String description
    String shortDescription
    String image

    static constraints = {
      id(nullable: false, blank:false)
      description(nullable: true)
      shortDescription(nullable: true)
      image(nullable: true)
      date(nullable: true,  validator: { val, object ->
        if(val){
          return val > new Date().minus(1) && val < new Date().plus(150)
        }
      })
    }
}
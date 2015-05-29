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
        def respond
        log.warn("COMMAND RECEIVE ---> ${cmd.dump()}")
        if(cmd.hasErrors() || !cmd.validate()) {
            response.sendError( 400,"Validation exception.Bad Request")
            throw new BadRequestException("Validation update error", cmd.errors)
        }

        //respond = promotionsService.updatePromotion(cmd)

        render(contentType: 'application/json', text: respond)
    }

}

@Validateable
class PromotionUpdateCommand{
    Long id
    Date date
    String description
    String shortDescription
    Image image

    static constraints = {
      id(nullable: false, blank:false)
      date(min: new Date().minus(15), max: new Date().plus(150))
      description(nullable:true, blank:false)
      shortDescription(nullable:true, blank:false)
      image(nullable:true, blank: false)
    }
}
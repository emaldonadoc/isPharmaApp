package com.is.pharma

import com.is.pharma.model.Image
import com.is.pharma.model.Promotion
import exceptions.BadRequestException
import grails.converters.JSON
import grails.validation.Validateable


class PromotionsController {
    static allowedMethods = [list:'GET']

    def list(){
        def result = Promotion.getAll()
        def resp = (result as JSON).toString()
        render (contentType: 'application/json', text: resp)
    }

    def update(PromotionUpdateCommand cmd){
        def respond
        if(cmd.hasErrors() || cmd.validate()) {
            response.sendError( 400,"Validation exception.Bad Request")
            throw new BadRequestException("Validation update error", cmd.errors)
        }
        render(contentType: 'application/json', text: respond)
    }

}

@Validateable
class PromotionUpdateCommand{
    long id
    Date date
    String description
    String shortDescription
    Image image

    static constraints = {
      id(nullable:false)
    }
}
package com.is.pharma
import com.is.pharma.model.Promotion
import exceptions.BadRequestException
import grails.converters.JSON
import grails.validation.Validateable

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
        respond = (promotionsService.updatePromotion(cmd.id, cmd) as JSON).toString()
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
      id(nullable: false, validator: { val, obj ->
        obj.description || obj.shortDescription || obj.image || obj.date
      })
      description(nullable:true,validator:{val->if(val){val.length()>=10}})
      shortDescription(nullable:true,validator:{val-> if(val){val.length()>=5}})
      image(nullable:true,validator:{val-> if(val){val.length()>=20}})
      date(nullable:true,  validator: { val->
        if(val){
            (val > new Date().minus(1) && val < new Date().plus(150))
        }
      })
    }
}
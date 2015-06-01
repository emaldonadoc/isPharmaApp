package com.is.pharma

import com.is.pharma.model.Promotion
import exceptions.EntityNotFoundException
import grails.transaction.Transactional

@Transactional
class PromotionsService {

    def updatePromotion(long id,PromotionUpdateCommand cmd) {
        def promo = Promotion.get(id)
        if(!promo) {
           throw new EntityNotFoundException("Promo not found", id)
        }
            if(cmd.description) promo.description = cmd.description
            if(cmd.shortDescription) promo.shortDescription = cmd.shortDescription
            if(cmd.date) promo.date = cmd.date
            if(cmd.image) promo.image.image = cmd.image
            promo.save(flush:true)


        return promo
    }
}

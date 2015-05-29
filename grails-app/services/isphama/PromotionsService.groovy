package isphama

import com.is.pharma.PromotionUpdateCommand
import com.is.pharma.model.Promotion
import grails.transaction.Transactional

@Transactional
class PromotionsService {

    def updatePromotion(PromotionUpdateCommand cmd) {
        def promo = Promotion.get(cmd.id)

        return promo
    }
}

package com.is.pharma
import com.is.pharma.model.Promotions
import grails.converters.JSON


class PromotionsController {
    static allowedMethods = [list:'GET']

    def list(){
        def result = Promotions.getAll()
        def resp = (result as JSON).toString()
        render (contentType: 'application/json', text: resp)
    }

}

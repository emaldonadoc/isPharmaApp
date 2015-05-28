package com.is.pharma

class PromotionController{

  static allowedMethods = [list:'GET']

  def list(){
    def result = Promotions.list();

    render(contentType: "application/json"){
      promotions: result
    }

  }
}

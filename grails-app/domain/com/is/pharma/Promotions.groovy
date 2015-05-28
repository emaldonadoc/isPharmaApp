package com.is.pharma

class Promotions {

    Date date
    String description
    String shortDescription

    static constraints = {
      date nullable: false
      description nullable:false, maxSize:250
      shortDescription nullable:false, maxSize:30
    }
}

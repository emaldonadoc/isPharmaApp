package com.is.pharma.model

class Promotions {

    Date date
    String description
    String shortDescription
    Images image

    static constraints = {
      date nullable: false
      description nullable:false, maxSize:250
      shortDescription nullable:false, maxSize:30
      image nullable: false, blank: false
    }
}

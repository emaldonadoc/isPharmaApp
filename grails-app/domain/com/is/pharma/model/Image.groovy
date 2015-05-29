package com.is.pharma.model

class Image {

    String image
    String extention

    static mapping = {
        image type: 'text'
    }

    static constraints = {
        image nullable: false, blank: false
        extention nullable: false, blank: false
    }
}

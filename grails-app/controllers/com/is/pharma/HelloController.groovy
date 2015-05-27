package com.is.pharma

class HelloController {

    def sayHello() {
      log.info "entry in say hello action "
      render("HELLO !!!!")
    }
}

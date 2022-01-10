package de.ald.demo.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.beans.Customizer

@RestController
class CreditScoreController {
    @PostMapping("/creditScore")
    fun getCreditScore(customizer: Customizer){

    }
}
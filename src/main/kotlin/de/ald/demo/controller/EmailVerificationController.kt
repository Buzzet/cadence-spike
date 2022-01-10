package de.ald.demo.controller

import de.ald.demo.cadence.EmailWorker
import de.ald.demo.model.EmailStatus
import org.springframework.web.bind.annotation.*

@RestController()
class EmailVerificationController {
    @PostMapping("/verification")
    fun verifyEmail(@RequestBody emailStatus: EmailStatus){
        var emailWorker: EmailWorker = EmailWorker();
        emailWorker.verifyEmail(emailStatus.email, emailStatus.verified);
    }
    @GetMapping("/test")
    fun test(): String{
        return "Hello";
    }
}
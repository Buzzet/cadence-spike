package de.ald.demo.controller

import de.ald.demo.cadence.CadenceRunner
import de.ald.demo.cadence.CreditScoreWorker
import de.ald.demo.cadence.EmailWorker
import de.ald.demo.model.Customer
import de.ald.demo.model.User
import io.netty.util.concurrent.Promise
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Async
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.*
import com.uber.cadence.workflow.Promise as Promise1


@RestController()
class EntrypointController {
    @PostMapping("/ep")
    fun entryPoint(@RequestBody customer: Customer): Customer?{
        println(customer);
        val creditScoreWorker = CreditScoreWorker();
        val customerWithCreditScore = creditScoreWorker.getScore(customer);
        val emailWorker = EmailWorker()
        val finalCustomer: Customer = emailWorker.getEmailVerification(customerWithCreditScore);
        return finalCustomer;
    }
    @PostMapping("/ba")
    fun toBasicAuth(@RequestBody user: User): String{
        return "${user.username}:${user.password}".toBase64();
    }
    fun String.toBase64(): String = Base64.getEncoder().encodeToString(this.toByteArray(Charsets.UTF_8))
}
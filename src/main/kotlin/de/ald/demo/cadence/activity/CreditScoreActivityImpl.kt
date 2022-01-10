package de.ald.demo.cadence.activity

import de.ald.demo.model.Customer
import kotlin.random.Random

class CreditScoreActivityImpl: CreditScoreActivity {
    override fun getCreditScore(customer: Customer): Customer{
        val scores = arrayOf("bad", "ok", "good", "great");
        val randomInt = Random.nextInt(0, 3);
        customer.creditScore = scores[randomInt];
        return customer;
    }
}
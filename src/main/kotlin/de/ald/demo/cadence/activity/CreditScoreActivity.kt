package de.ald.demo.cadence.activity

import com.uber.cadence.activity.ActivityMethod
import de.ald.demo.model.Customer
import kotlin.random.Random

interface CreditScoreActivity {
    @ActivityMethod(scheduleToCloseTimeoutSeconds = 2)
    fun getCreditScore(customer: Customer): Customer;
}
package de.ald.demo.cadence.Workflows

import com.uber.cadence.workflow.Workflow
import de.ald.demo.cadence.activity.CreditScoreActivity
import de.ald.demo.cadence.activity.CreditScoreActivityImpl
import de.ald.demo.model.Customer

class CreditScoreWorkflowImpl: CreditScoreWorkflow {
    private val activities: CreditScoreActivity = Workflow.newActivityStub(CreditScoreActivity::class.java)
    override fun getCreditScore(customer: Customer): Customer {
        return activities.getCreditScore(customer);
    }
}
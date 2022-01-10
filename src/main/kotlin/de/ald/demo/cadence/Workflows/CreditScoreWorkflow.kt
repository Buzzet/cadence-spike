package de.ald.demo.cadence.Workflows

import com.uber.cadence.workflow.WorkflowMethod
import de.ald.demo.model.Customer

interface CreditScoreWorkflow{
    @WorkflowMethod(executionStartToCloseTimeoutSeconds = 10, taskList = "CreditScore")
    fun getCreditScore(customer: Customer): Customer
}
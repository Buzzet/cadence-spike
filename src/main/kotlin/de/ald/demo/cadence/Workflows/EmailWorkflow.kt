package de.ald.demo.cadence.Workflows

import com.uber.cadence.workflow.SignalMethod
import com.uber.cadence.workflow.WorkflowMethod
import de.ald.demo.model.Customer

interface EmailWorkflow {
    @WorkflowMethod(executionStartToCloseTimeoutSeconds = 10, taskList = "EmailVerification")
   fun getVerificationStatus(): String?;

    @SignalMethod
    fun setVerificationStatus(status: String);

    @SignalMethod
    fun exit();
}
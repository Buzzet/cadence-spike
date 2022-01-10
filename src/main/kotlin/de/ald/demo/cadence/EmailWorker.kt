package de.ald.demo.cadence

import com.uber.cadence.client.WorkflowClient
import com.uber.cadence.client.WorkflowClientOptions
import com.uber.cadence.client.WorkflowOptions
import com.uber.cadence.serviceclient.ClientOptions
import com.uber.cadence.serviceclient.WorkflowServiceTChannel
import com.uber.cadence.workflow.Functions.Func
import de.ald.demo.cadence.Workflows.EmailWorkflow
import de.ald.demo.cadence.Workflows.EmailWorkflowImpl
import de.ald.demo.model.Customer
import org.springframework.scheduling.annotation.Async
import java.time.Duration

open class EmailWorker {

    @Async
    open fun getEmailVerification(customer: Customer): Customer {
        val workflowClient = WorkflowClient.newInstance(
            WorkflowServiceTChannel(ClientOptions.defaultInstance()),
            WorkflowClientOptions.newBuilder().setDomain("cadence-spike").build()
        )
        val workflowOptions = WorkflowOptions.Builder()
            .setTaskList("EmailVerification")
            .setExecutionStartToCloseTimeout(Duration.ofSeconds(300))
            .setWorkflowId(customer.email)
            .build()
        val workflow: EmailWorkflow = workflowClient.newWorkflowStub(
            EmailWorkflow::class.java,
            workflowOptions
        )
        WorkflowClient.start(workflow::getVerificationStatus)
        val workflowById: EmailWorkflow = workflowClient.newWorkflowStub(
            EmailWorkflow::class.java,
            customer.email
        )
//        if (!customer.emailVerification.equals("VERIFICATION_COMPLETED")){
//
//
//        }

            customer.emailVerification = workflowById.getVerificationStatus();
        return customer;
    }

    fun verifyEmail(email: String, verification: String) {
        val workflowClient = WorkflowClient.newInstance(
            WorkflowServiceTChannel(ClientOptions.defaultInstance()),
            WorkflowClientOptions.newBuilder().setDomain("cadence-spike").build()
        )
        println(email)
        val workflowOptions = WorkflowOptions.Builder()
            .setTaskList("EmailVerification")
            .setExecutionStartToCloseTimeout(Duration.ofSeconds(300))
            .setWorkflowId(email)
            .build()
        val workflow: EmailWorkflow = workflowClient.newWorkflowStub(
            EmailWorkflow::class.java,
            workflowOptions
        )
        val workflowById: EmailWorkflow = workflowClient.newWorkflowStub(
            EmailWorkflow::class.java,
            email
        )
        workflowById.setVerificationStatus(verification);
    }
}
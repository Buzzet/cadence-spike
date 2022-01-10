package de.ald.demo.cadence

import com.uber.cadence.client.WorkflowClient
import com.uber.cadence.client.WorkflowClientOptions
import com.uber.cadence.serviceclient.ClientOptions
import com.uber.cadence.serviceclient.WorkflowServiceTChannel
import com.uber.cadence.worker.WorkerFactory
import de.ald.demo.cadence.Workflows.CreditScoreWorkflow
import de.ald.demo.cadence.Workflows.CreditScoreWorkflowImpl
import de.ald.demo.cadence.Workflows.EmailWorkflowImpl
import de.ald.demo.cadence.activity.CreditScoreActivity
import de.ald.demo.cadence.activity.CreditScoreActivityImpl
import de.ald.demo.model.Customer
import org.springframework.stereotype.Service

@Service
class CadenceRunner {
   init {

        // Get a new client
        // NOTE: to set a different options, you can do like this:
        // ClientOptions.newBuilder().setRpcTimeout(5 * 1000).build();
        val workflowClient = WorkflowClient.newInstance(
            WorkflowServiceTChannel(ClientOptions.defaultInstance()),
            WorkflowClientOptions.newBuilder().setDomain("cadence-spike").build()
        )
        // Get worker to poll the task list.
        // Get worker to poll the task list.
        val factory = WorkerFactory.newInstance(workflowClient)
        val worker = factory.newWorker("CreditScore")
        // Workflows are stateful. So you need a type to create instances.
        // Workflows are stateful. So you need a type to create instances.
        worker.registerWorkflowImplementationTypes(CreditScoreWorkflowImpl::class.java)
        val worker2 = factory.newWorker("EmailVerification")
        // Workflows are stateful. So you need a type to create instances.
        // Workflows are stateful. So you need a type to create instances.
        worker2.registerWorkflowImplementationTypes(EmailWorkflowImpl::class.java)
        // Activities are stateless and thread safe. So a shared instance is used.
        // Activities are stateless and thread safe. So a shared instance is used.
        worker.registerActivitiesImplementations(CreditScoreActivityImpl())
        // Start listening to the workflow and activity task lists.
        // Start listening to the workflow and activity task lists.
        factory.start()
    }
}
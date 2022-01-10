package de.ald.demo.cadence

import com.uber.cadence.client.WorkflowClient
import com.uber.cadence.client.WorkflowClientOptions
import com.uber.cadence.serviceclient.ClientOptions
import com.uber.cadence.serviceclient.WorkflowServiceTChannel
import com.uber.cadence.workflow.WorkflowMethod
import de.ald.demo.cadence.Workflows.CreditScoreWorkflow
import de.ald.demo.model.Customer

class CreditScoreWorker {
  fun getScore(customer: Customer): Customer{
      // Get a workflow stub using the same task list the worker uses.
      val workflowClient = WorkflowClient.newInstance(
          WorkflowServiceTChannel(ClientOptions.defaultInstance()),
          WorkflowClientOptions.newBuilder().setDomain("cadence-spike").build()
      )
      // Get a workflow stub using the same task list the worker uses.
      val workflow: CreditScoreWorkflow =
          workflowClient.newWorkflowStub(CreditScoreWorkflow::class.java)
      // Execute a workflow waiting for it to complete.
      // Execute a workflow waiting for it to complete.
      val returningCustomer = workflow.getCreditScore(customer);
      println(returningCustomer);
      return returningCustomer;

  }
}
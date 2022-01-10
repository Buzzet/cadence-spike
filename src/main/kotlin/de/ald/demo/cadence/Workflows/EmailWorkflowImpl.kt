package de.ald.demo.cadence.Workflows

import com.uber.cadence.workflow.Workflow
import de.ald.demo.model.Customer

class EmailWorkflowImpl: EmailWorkflow {
    var status: String? = "WORKFLOW_VERIFICATION_PENDING";

    var exit: Boolean = false;

    override fun getVerificationStatus(): String? {
        Workflow.await { !this.status.equals("WORKFLOW_VERIFICATION_PENDING") || exit};
        return this.status;
    }

    override fun setVerificationStatus(status: String) {
        this.status = status;
    }

    override fun exit() {
        this.exit = true;
    }
}
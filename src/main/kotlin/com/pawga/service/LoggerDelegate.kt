package com.pawga.service

import jakarta.inject.Singleton
import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate
import org.slf4j.Logger
import org.slf4j.LoggerFactory


/**
 * Created by sivannikov on 27.02.2025 17:42
 */

@Singleton
class LoggerDelegate : JavaDelegate {
    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    override fun execute(execution: DelegateExecution) {
        log.info(
            "\n\n  ... LoggerDelegate invoked by "
                    + "activtyName='" + execution.getCurrentActivityName() + "'"
                    + ", activtyId=" + execution.getCurrentActivityId()
                    + ", processDefinitionId=" + execution.getProcessDefinitionId()
                    + ", processInstanceId=" + execution.getProcessInstanceId()
                    + ", businessKey=" + execution.getProcessBusinessKey()
                    + ", executionId=" + execution.getId()
                    + ", variables=" + execution.getVariables()
                    + " \n\n"
        )
    }
}
package com.pawga.controller

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue
import org.camunda.bpm.engine.DecisionService
import org.camunda.bpm.engine.ProcessEngine
import org.camunda.bpm.engine.ProcessEngines
import org.camunda.bpm.engine.variable.Variables
import org.slf4j.LoggerFactory


/**
 * Created by sivannikov on 23.02.2025 18:41
 */
@Controller(value = "/dmn")
class DmnController {

    private val log = LoggerFactory.getLogger(this::class.java)

    /**
     * for example http://localhost:8080/dmn/calculate?hour=14
     * result: [{decision=суп, mandatory=true}, {decision=салат, mandatory=false}, {decision=пюре с котлетой, mandatory=true}]
     * */
    @Get("/calculate")
    fun calculate(@QueryValue hour: Int): String {

        // Get the default ProcessEngine
        val processEngine: ProcessEngine = ProcessEngines.getDefaultProcessEngine()
        // Get the DecisionService from the ProcessEngine
        val decisionService: DecisionService = processEngine.decisionService

        // Create variables to pass to the decision
        val variables = Variables.createVariables()
            .putValue("hour", hour)
        val decisionResult = decisionService.evaluateDecisionByKey("decision_list_documents_1")
            .variables(variables)
            .evaluate()

        if (decisionResult != null && decisionResult.resultList.isNotEmpty()) {
            log.info("decisionResult.count ={}", decisionResult.size)

            for (item in decisionResult) {
                log.info("Item: $item")
            }
            return decisionResult.resultList.toString()
        } else {
            return "Не удалось получить результат!"
        }
    }
}
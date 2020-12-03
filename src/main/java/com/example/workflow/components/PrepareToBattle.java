package com.example.workflow.components;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class PrepareToBattle implements JavaDelegate {

    @Value("${maxWarriors}")
    private int maxWarriors;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        int warriors = (int) execution.getVariable("warriors");
        int enemyWarriors = (int) (Math.random() * 100);
        maxWarriors = maxWarriors == 0 ? 100 : maxWarriors;
        if (warriors < 1 || warriors > maxWarriors) {
            throw  new BpmnError("warriorsError");
        }
        List<Boolean> army = new ArrayList<>(Collections.nCopies(warriors, true));
        System.out.println("Prepare to battle Enemy army = " + enemyWarriors + " vs. our army: " + warriors);
        execution.setVariable("army", army);
        execution.setVariable("enemyWarriors", enemyWarriors);
    }
}

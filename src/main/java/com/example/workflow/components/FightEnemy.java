package com.example.workflow.components;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Random;

@Component
public class FightEnemy implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        ArrayList<Boolean> army = (ArrayList<Boolean>) execution.getVariable("army");
        int enemyWarriors = (int) execution.getVariable("enemyWarriors");

        Thread.sleep(1000);

        if (new Random().nextBoolean()) {
            enemyWarriors--;
            System.out.println("Enemy warrior killed!");
        } else {
            army.remove(army.size() - 1);
            System.out.println("Our warrior killed!");
        }
        execution.setVariable("enemyWarriors", enemyWarriors);
        execution.setVariable("warriors", army.size());
        execution.setVariable("army", army);
    }
}

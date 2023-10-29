package com.ise.patrickandjean.quizapp2.GameModes;

import com.ise.patrickandjean.quizapp2.BaseClasses.QuestionBank;

public class Elimination {
    public static void runEliminationMode() {
        QuestionBank QB = new QuestionBank();
        boolean anotherRound = true;
        int counter = 0;   //this counter allows us to end the game if the user answers all possible questions correctly
        while (anotherRound && counter < 18) {
            //anotherRound = cont.SetUIQuestion(QB.get(counter));
            if (anotherRound) {
                counter++;
            } else {
                //System.out.println("You're out!");
                break;
            }
        }
        if (counter == 18) {
            //System.out.println("Congrats!!! You got all the questions right");}

        }
    }
}

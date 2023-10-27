package com.ise.patrickandjean.quizapp2.Services;

import java.text.DecimalFormat;
public class StatisticService {

    public static double calculateMean(int[] scores) {
        float total = 0;
        for (int score : scores) {
            total += score;
        }
        //show score to 2 decimal places
        DecimalFormat df = new DecimalFormat("#.00");
        return Double.parseDouble(df.format(total / scores.length));
    }

    public static int calculateMode(int[] scores) {
        int max = 0;
        int mode = 0;
        for (int score : scores) {
            int count = 0;
            for (int occurance : scores) {
                if (occurance == score) {
                    count++;
                }
            }
            if (count >= max) {
                max = count;
                mode = score;
            }
        }
        return mode;
    }

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


package com.ise.patrickandjean.quizapp2.BaseClasses;

import java.io.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;




public class QBank {
    ArrayList<Question> questions = new ArrayList<>();
    Random random = new Random();

    public QBank() {
        try {
            Scanner scanner = new Scanner(new File("QuestionBank.csv"));
            scanner.useDelimiter("\n");
            // Skip the header line
            if (scanner.hasNext()) {
                scanner.nextLine();
            }
            while (scanner.hasNext()) {
                String line = scanner.next();
                String[] elements = line.split(",");
                if (elements.length == 4) {
                    String questionTEXT = elements[0].substring(1, elements[0].length()-1);
                    String[] answers = elements[1].substring(1, elements[1].length()-1).split(";");
                    Difficulty difficulty = Difficulty.valueOf((elements[2]).substring(1, (elements[2].length()-1)));
                    String category = elements[3];
                    Question newQuestion = new Question(questionTEXT, answers, difficulty, category);
                    questions.add(newQuestion);
                }
            }
            for (int i = questions.size() - 1; i > 0; i--) {
                int index = random.nextInt(i + 1);
                Question temp = questions.get(i);
                questions.set(i, questions.get(index));
                questions.set(index, temp);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }



    /**
     * This function takes the whole question bank and filters it based on an input difficulty level.
     * It will return a new array of Questions, except it will only contain the 6 from the specified difficulty level.
     */
    public ArrayList<Question> popQuestionByDifficulty(Difficulty difficulty, int count) {
        ArrayList<Question> chosenDifficultyQuestions = new ArrayList<Question>();
        for (Question question : questions) {
            if (question.getDifficulty().equals(difficulty)) {
                chosenDifficultyQuestions.add(question);
                if (chosenDifficultyQuestions.size() >= count) {
                    break;
                }
            }
        }

        questions.removeAll(chosenDifficultyQuestions);

        //return the arrayList
        return chosenDifficultyQuestions;
    }




    /**
     * @param count is the amount of questions we want to select
                The question bank is already shuffled on creation so now we can just pop elements off
     * @return an arrayList of questions of size count
     */
    public ArrayList<Question> popQuestionRandom(int count) {
        ArrayList<Question> chosenQuestions = new ArrayList<Question>();
        for (Question question : questions) {
            chosenQuestions.add(question);
            if (chosenQuestions.size() >= count) {
                break;
            }
        }


        questions.removeAll(chosenQuestions);

        //return the arrayList
        return chosenQuestions;
    }
}


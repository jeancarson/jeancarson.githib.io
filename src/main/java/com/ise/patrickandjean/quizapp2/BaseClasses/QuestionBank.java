package com.ise.patrickandjean.quizapp2.BaseClasses;

import java.util.ArrayList;
import java.util.Random;

public class QuestionBank {

    /**
     * This is an ArrayList of question objects. I chose to use an ArrayList instead of an Array as it made it easier
     * later on to add and remove elements during the quiz game.
     */
    private final ArrayList<Question> questions = new ArrayList<Question>();

    {
        //Novice questions

        questions.add(new Question("Which of the following is NOT a type of processor?", new String[]{"x66", "ARM", "SPARC", "Power PC"}, "Novice", "CompOrg"));
        questions.add(new Question("What is 24 in binary?", new String[]{"11000", "10110", "11101", "00111"}, "Novice", "CompOrg"));
        questions.add(new Question("What does BNF stand for?", new String[]{"Backus-Naur Form", "British National Formulary", "Baeir-Needham Form", "Babbage-Norvig Form"}, "Novice", "CompSci"));
        questions.add(new Question("Which of the following is NOT a standard way of representing a production rule when working with BNF's?", new String[]{"=", "::=", ":=", "=>"}, "Novice", "CompSci"));
        questions.add(new Question("What logical equivalence is shown here : (p AND q =  q AND p)", new String[]{"Communativity", "Distribution", "Absorption", "Transitivity"}, "Novice", "DiscreteMaths"));
        questions.add(new Question("Which of the following is NOT an example of tautology?", new String[]{"IF p THEN False", "F => p", "p OR NOT p", "p OR True"}, "Novice", "DiscreteMaths"));
        //Intermediate Questions
        questions.add(new Question("What is the biggest level of Cache?", new String[]{"Level 3", "Level 2", "Level 1", "They are all the same size"}, "Intermediate", "CompOrg"));
        questions.add(new Question("Where do the phrases 'Big Endian' and 'Little Endian' come from?", new String[]{"Gulliver's Travels", "Dalisay Endian", "India", "The creator's brother"}, "Intermediate", "CompOrg"));
        questions.add(new Question("What does []* signify when talking about Grammars?", new String[]{"Zero or more occurrences from a set", "One or more occurrences from a set", "One element from a set", "All elements from a set"}, "Intermediate", "CompSci"));
        questions.add(new Question("What is the relationship between a lattice and a partially ordered set?", new String[]{"Every lattice is a partial order", "Every partial order is a lattice", "They are unrelated", "A lattice can only have one element"}, "Expert", "CompSci"));
        questions.add(new Question("Which of the following shows the 2x2 Identity matrix?", new String[]{"1 0\n0 1", "1 1\n0 0", "0 1\n1 0", "1 0\n1 0"}, "Intermediate", "DiscreteMaths"));
        questions.add(new Question("Let the geometric series {Sn} = (4)(2)^n. What is the sum up to S4?", new String[]{"188", "184", "128", "60"}, "Intermediate", "DiscreteMaths"));
        //Expert Questions
        questions.add(new Question("What is an advantage of using 2's complement over other methods of representing negative binary numbers?", new String[]{"Only one representation of zero", "Easy to read", "Big range", "No risk of overflow"}, "Expert", "CompOrg"));
        questions.add(new Question("What word is used to describe the number of different symbols a number system has?", new String[]{"Radix", "Index", "Regex", "Base"}, "Expert", "CompOrg"));
        questions.add(new Question("Which property does not hold in a lattice?", new String[]{"Distributivity", "Associativity", "Absorption", "Commutativity"}, "Intermediate", "CompSci"));
        questions.add(new Question("Which of the following is NOT a key step in ROBDD construction?", new String[]{"Create only one decision node with each name", "Eliminate redundant tests", "Remove duplicate terminal nodes", "Merge isomorphic decision nodes"}, "Expert", "CompSci"));
        questions.add(new Question("What would you use to express the following?: 'On every football team there is a goalie'", new String[]{"Universal and Existential Quantifiers", "Universal Quantifiers only", "Existential Quantifiers only", "Predicate Logic only"}, "Expert", "DiscreteMaths"));
        questions.add(new Question("How would you express the following?: {x E R | a < x <= b}", new String[]{"]a, b]", "[a, b]", "]a, b[", "[a, b["}, "Expert", "DiscreteMaths"));

    }

    static Random random = new Random();

    /**
     * This function takes the whole question bank and filters it based on an input difficulty level.
     * It will return a new array of Questions, except it will only contain the 6 from the specified difficulty level.
     */
    public ArrayList<Question> popQuestionByDifficulty(String difficulty, int count) {
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
     * Using the Fisher Yates shuffle method, every time a new questionbank object is created,
     * the questions are automatically shuffled.
     */
    public QuestionBank() {
        for (int i = questions.size() - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            Question temp = questions.get(i);
            questions.set(i, questions.get(index));
            questions.set(index, temp);
        }

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


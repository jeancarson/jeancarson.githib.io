package com.ise.patrickandjean.quizapp2.GameModes;

import com.ise.patrickandjean.quizapp2.BaseClasses.Question;
import com.ise.patrickandjean.quizapp2.BaseClasses.QuestionBank;

import java.util.ArrayList;

public class IncreasingDifficulty {
    public static void runIncreasingDifficulty() {
        QuestionBank QB = new QuestionBank();
        ArrayList<Question> QsToAsk = new ArrayList<>();
        QsToAsk.addAll(QB.popQuestionByDifficulty("Novice", 2));
        QsToAsk.addAll(QB.popQuestionByDifficulty("Intermediate", 2));
        QsToAsk.addAll(QB.popQuestionByDifficulty("Expert", 2));

        while (QsToAsk.size() > 0) {
            // cont.SetUIQuestion(QB.get(0));
            // QB.pop(QB.get(0));
        }
    }
}

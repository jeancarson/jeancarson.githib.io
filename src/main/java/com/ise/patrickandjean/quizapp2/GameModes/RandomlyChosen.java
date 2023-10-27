package com.ise.patrickandjean.quizapp2.GameModes;

import com.ise.patrickandjean.quizapp2.BaseClasses.Question;
import com.ise.patrickandjean.quizapp2.BaseClasses.QuestionBank;

import java.util.ArrayList;

public class RandomlyChosen {
    public static void runRandomlyChosen(){
        QuestionBank QB = new QuestionBank();
        //shuffles
        ArrayList<Question> QsToAsk = QB.popQuestionRandom(6);
        while(QsToAsk.size() > 0){
            // cont.SetUIQuestion(QB.get(0));
            //QB.pop(QB.get(0));
        }


    }
}

package com.ise.patrickandjean.quizapp2.Pages.EndGamePages;

import com.ise.patrickandjean.quizapp2.BaseClasses.QuizSession;
import com.ise.patrickandjean.quizapp2.Services.SaveService;

public class TimeUp extends EndGamePage {
    @Override
    public void setViewWithSessionData(QuizSession session) {
        /// Update main title
        mainTitle.setText(String.format("Time Up! Hard luck %s!", SaveService.getCurrentUser()));
        super.setViewWithSessionData(session);
    }
}

module com.ise.patrickandjean.quizapp2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    exports com.ise.patrickandjean.quizapp2.BaseClasses;

    opens com.ise.patrickandjean.quizapp2 to javafx.fxml;
    exports com.ise.patrickandjean.quizapp2;

    exports com.ise.patrickandjean.quizapp2.Pages.Login;
    opens com.ise.patrickandjean.quizapp2.Pages.Login to javafx.fxml;

    exports com.ise.patrickandjean.quizapp2.Pages.DifficultyChooser;
    opens com.ise.patrickandjean.quizapp2.Pages.DifficultyChooser to javafx.fxml;

    exports com.ise.patrickandjean.quizapp2.Pages.QuestionAsker;
    opens com.ise.patrickandjean.quizapp2.Pages.QuestionAsker to javafx.fxml;
}
module com.ise.patrickandjean.quizapp2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.ise.patrickandjean.quizapp2 to javafx.fxml;
    exports com.ise.patrickandjean.quizapp2;
}
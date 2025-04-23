module com.example.redcrescentoopproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.redcrescentoopproject to javafx.fxml;
    exports com.example.redcrescentoopproject;
}
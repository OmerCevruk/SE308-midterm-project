module com.dbcourse.dbproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;

    opens com.dbcourse.dbproject to javafx.fxml;
    exports com.dbcourse.dbproject;
}
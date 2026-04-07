module org.com.example.javainterface {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens org.com.example.javainterface to javafx.fxml;
    exports org.com.example.javainterface;
}
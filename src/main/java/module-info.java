module org.com.example.javainterface {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires java.desktop;
    requires atlantafx.base;

    opens org.com.example.javainterface to javafx.fxml;
    exports org.com.example.javainterface;
}
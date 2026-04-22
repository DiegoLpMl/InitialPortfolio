module org.com.example.javainterface {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires java.desktop;
    requires atlantafx.base;
    requires jbcrypt;
    requires jakarta.mail;

    opens org.com.example.javainterface to javafx.fxml;
    exports org.com.example.javainterface;
    exports org.com.example.javainterface.Controllers;
    opens org.com.example.javainterface.Controllers to javafx.fxml;
    exports org.com.example.javainterface.DTBClasses;
    opens org.com.example.javainterface.DTBClasses to javafx.fxml;
    exports org.com.example.javainterface.Services;
    opens org.com.example.javainterface.Services to javafx.fxml;
}
module com.memory {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.memory to javafx.fxml;
    exports com.memory;
    exports com.memory.Controllers;
    exports com.memory.Controllers.User;
    exports com.memory.Controllers.Game;
    exports com.memory.Models;
    exports com.memory.Views;
}
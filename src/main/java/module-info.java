module com.memory {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.memory to javafx.fxml;
    exports com.memory;
    exports com.memory.Controllers;
    exports com.memory.Models;
    exports com.memory.Views;
}
module models {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens QuanLyNhanKhau.UI to javafx.fxml;
    opens QuanLyNhanKhau.controllers to javafx.fxml;
    exports QuanLyNhanKhau;
    exports QuanLyNhanKhau.controllers;
}
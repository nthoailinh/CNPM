module models {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens QuanLyNhanKhau.views to javafx.fxml;
    opens QuanLyNhanKhau.views.nhankhau to javafx.fxml;
    opens QuanLyNhanKhau.views.hokhau to javafx.fxml;
    opens QuanLyNhanKhau.controllers to javafx.fxml;
    opens QuanLyNhanKhau.controllers.nhankhau to javafx.fxml;
    opens QuanLyNhanKhau.controllers.hokhau to javafx.fxml;
    opens QuanLyNhanKhau.models to javafx.base;
    opens QuanLyNhanKhau.controllers.tables to javafx.base;
    exports QuanLyNhanKhau.controllers.nhankhau;
    exports QuanLyNhanKhau.controllers.hokhau;
    exports QuanLyNhanKhau.controllers;
    exports QuanLyNhanKhau.views;
}
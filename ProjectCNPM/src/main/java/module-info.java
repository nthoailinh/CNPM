module com.example.projectcnpm {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens QuanLyNhanKhau.views to javafx.fxml;
    opens QuanLyNhanKhau.views.home to javafx.fxml;
    opens QuanLyNhanKhau.controllers to javafx.fxml;
    opens QuanLyNhanKhau.controllers.nhankhau to javafx.fxml;
    opens QuanLyNhanKhau.controllers.hokhau to javafx.fxml;
    opens QuanLyNhanKhau.views.nhankhau to javafx.fxml;
    opens QuanLyNhanKhau.views.hokhau to javafx.fxml;
    opens QuanLyNhanKhau.models to javafx.base;
    opens QuanLyNhanKhau.controllers.tables to javafx.base;
    exports QuanLyNhanKhau.controllers;
    exports QuanLyNhanKhau.controllers.nhankhau;
    exports QuanLyNhanKhau.controllers.hokhau;
    exports QuanLyNhanKhau.controllers.tables;
    exports QuanLyNhanKhau.views;
}
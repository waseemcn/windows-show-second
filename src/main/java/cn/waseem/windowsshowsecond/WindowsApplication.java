package cn.waseem.windowsshowsecond;

import javafx.application.Application;
import javafx.stage.Stage;

public class WindowsApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // 创建窗口构建类的实例
        WindowBuilder windowBuilder = new WindowBuilder();

        // 使用窗口构建类的实例来构建窗口
        windowBuilder.build(primaryStage);
    }

    public static void main(String[] args) {
        launch();
    }
}
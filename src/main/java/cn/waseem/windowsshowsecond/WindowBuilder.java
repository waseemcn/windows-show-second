package cn.waseem.windowsshowsecond;

import cn.waseem.windowsshowsecond.config.WindowSettings;
import cn.waseem.windowsshowsecond.controller.WindowsController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

public class WindowBuilder {

    double xOffset, yOffset;
    boolean cutBackColor = false;

    public void build(Stage primaryStage) {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-radius: 4px; -fx-background-color: rgba(255, 255, 255, 0.8);");
        root.setPadding(new Insets(10));

        BorderPane header = new BorderPane();
        // 创建左边的窗体标题名字
        Label titleLabel = new Label(WindowSettings.TITLE);
        titleLabel.getStyleClass().setAll("h5", "strong");
        titleLabel.setOnMouseClicked(e -> {
            if (cutBackColor) {
                titleLabel.setStyle("");
                root.setStyle("-fx-background-radius: 4px; -fx-background-color: rgba(255, 255, 255, 0.8);");
            } else {
                titleLabel.setStyle("-fx-text-fill: white;");
                root.setStyle("-fx-background-radius: 4px; -fx-background-color: rgba(128, 128, 128, 0.8);");
            }

            cutBackColor = !cutBackColor;
        });
        header.setLeft(titleLabel);
        BorderPane.setAlignment(titleLabel, Pos.CENTER_LEFT);

        // 创建顶部右侧的按钮
        Button minimizeButton = new Button("—");
        Button closeButton = new Button("X");
        minimizeButton.getStyleClass().setAll("btn-default", "btn-sm");
        closeButton.getStyleClass().setAll("btn-danger", "btn-sm");
        // 设置按钮点击事件
        closeButton.setOnAction(e -> primaryStage.close());
        minimizeButton.setOnAction(e -> primaryStage.setIconified(true));
        HBox headerButtons = new HBox(10, minimizeButton, closeButton);
        headerButtons.setAlignment(Pos.CENTER_RIGHT);
        header.setRight(headerButtons);
        root.setTop(header);


        // 创建中间的按钮
        Button button1 = new Button("显示秒数");
        Button button2 = new Button("隐藏秒数");
        Button button3 = new Button("修复资源管理器");
        button1.getStyleClass().setAll("btn", "btn-primary", "btn-sm");
        button2.getStyleClass().setAll("btn", "btn-danger", "btn-sm");
        button3.getStyleClass().setAll("btn", "btn-info", "btn-sm");
        button1.setAlignment(Pos.CENTER);
        button2.setAlignment(Pos.CENTER);
        button3.setAlignment(Pos.CENTER);
        button1.setOnAction(WindowsController::startSecond);
        button2.setOnAction(WindowsController::stopSecond);
        button3.setOnAction(WindowsController::startExplorer);
        VBox centerButtons = new VBox(10, button1, button2, button3);
        centerButtons.setAlignment(Pos.CENTER);
        root.setCenter(centerButtons);

        // 创建底部的标签
        Label bottomLabel = new Label("版本号：1.0");
        bottomLabel.getStyleClass().setAll("lbl", "lbl-default", "pre");
        bottomLabel.setPadding(new Insets(5));
        bottomLabel.setOnMouseClicked(WindowsController::openBlog);
        root.setBottom(bottomLabel);
        BorderPane.setAlignment(bottomLabel, Pos.CENTER);

        // 点击事件监听器，用于实现拖动窗体的功能
        root.setOnMousePressed(this::handleMousePressed);
        root.setOnMouseDragged(this::handleMouseDragged);

        // 创建场景
        Scene scene = new Scene(root, 350, 200);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);

        // 添加 BootstrapFX 样式表
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());

        // 设置应用程序图标
//        primaryStage.getIcons().add(new Image("https://blog.waseem.cn/wp-content/uploads/2023/11/logo.png"));

        primaryStage.initStyle(javafx.stage.StageStyle.TRANSPARENT); // 设置无边框
        primaryStage.show();
    }

    private void handleMousePressed(MouseEvent event) {
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
    }

    private void handleMouseDragged(MouseEvent event) {
        Stage primaryStage = (Stage) ((BorderPane) event.getSource()).getScene().getWindow();
        primaryStage.setX(event.getScreenX() - xOffset);
        primaryStage.setY(event.getScreenY() - yOffset);
    }

}

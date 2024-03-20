package cn.waseem.windowsshowsecond.controller;

import cn.waseem.windowsshowsecond.config.WindowSettings;
import cn.waseem.windowsshowsecond.tools.ExecutePowerShellCommand;
import javafx.event.ActionEvent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.Optional;


public class WindowsController {

    public static void startSecond(ActionEvent actionEvent) {
        boolean e = ExecutePowerShellCommand.e(1);
        secondDialog(e, "执行显示秒数成功！");
    }

    public static void stopSecond(ActionEvent actionEvent) {
        boolean e = ExecutePowerShellCommand.e(0);
        secondDialog(e, "执行隐藏秒数成功！");
    }


    public static void startExplorer(ActionEvent actionEvent) {
        int i = ExecutePowerShellCommand.startExplorer();
        if (i == 2) {
            javafx.scene.control.Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("提示");
            dialog.setContentText("资源管理器正在运行。");
            dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
            Optional<ButtonType> result = dialog.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                System.out.println("用户点击了确认按钮，关闭通知。");
            }
        } else if (i == 1) {
            javafx.scene.control.Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("提示");
            dialog.setContentText("资源管理器执行启动。");
            dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
            Optional<ButtonType> result = dialog.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                System.out.println("用户点击了确认按钮，关闭通知。");
            }
        } else {
            javafx.scene.control.Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("提示");
            dialog.setContentText("异常错误，请联系开发者：" + WindowSettings.GITHUBURL);
            dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
            Optional<ButtonType> result = dialog.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                System.out.println("用户点击了确认按钮，关闭通知。");
            }

        }

    }

    public static void openBlog(MouseEvent event) {
//        try {
//            Desktop.getDesktop().browse(new URI(WindowSettings.SKIPURL));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        try {
            Runtime.getRuntime().exec("cmd /c start " + WindowSettings.SKIPURL);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void secondDialog(boolean e, String str) {
        if (e) {
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("提示");
//        dialog.setHeaderText("这是一个通知消息！");
            dialog.setContentText(str + "\n\n此操作会重启 Windows资源管理器(explorer.exe) 电脑会闪一下。");

            // 添加确认按钮
            dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);

            // 显示对话框并等待用户响应
            Optional<ButtonType> result = dialog.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                System.out.println("用户点击了确认按钮，关闭通知。");
            }
        } else {
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("提示");
            dialog.setContentText("操作失败，请联系开发者：" + WindowSettings.GITHUBURL);
            dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
            Optional<ButtonType> result = dialog.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                System.out.println("用户点击了确认按钮，关闭通知。");
            }
        }
    }
}

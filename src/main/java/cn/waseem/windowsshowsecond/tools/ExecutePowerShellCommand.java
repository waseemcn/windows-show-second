package cn.waseem.windowsshowsecond.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExecutePowerShellCommand {

    public static boolean e(Integer value) {
        boolean b = showSecondsInSystemClock(value);
        if (b) {
            System.out.println("5-执行成功！");
            return true;
        }
        return false;
    }

    public static boolean showSecondsInSystemClock(Integer value) {
        // 获取当前工作目录
        String currentDir = System.getProperty("user.dir");

        // 指定批处理文件的相对路径
        String filePath = currentDir + File.separator + "show.bat";
        if (value != 1) {
            filePath = currentDir + File.separator + "hide.bat";
        }

        try {
            // 创建 Runtime 实例
            Runtime runtime = Runtime.getRuntime();

            // 执行批处理文件
            Process process = runtime.exec(filePath);

            // 等待批处理文件执行完毕
            process.waitFor();

            // 输出执行结果
            int exitValue = process.exitValue();
            System.out.println("批处理文件执行完毕，退出值为: " + exitValue);
            return true;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Integer startExplorer() {

        boolean explorerRunning = isExplorerRunning();
        if (explorerRunning) {
            System.out.println("资源管理器正在运行。");
            return 2;
        } else {
//            System.out.println("资源管理器未启动。");
            try {
                Runtime.getRuntime().exec("explorer");
                System.out.println("资源管理器已启动。");
                return 1;
//                // 重新启动资源管理器
//                Process startProcess = Runtime.getRuntime().exec("explorer.exe");
//                startProcess.waitFor(); // 等待进程启动
//
//                // 检查启动进程的退出值
//                int startExitValue = startProcess.exitValue();
//                if (startExitValue == 0) {
//                    System.out.println("资源管理器已成功启动。");
//                    return true;
//                } else {
//                    System.out.println("资源管理器启动失败。");
//                }
            } catch (IOException e) {
//                throw new RuntimeException(e);
            }
        }
        return 0;
    }


    public static boolean isExplorerRunning() {
        try {
            // 执行系统命令并获取进程列表
            Process process = Runtime.getRuntime().exec(System.getenv("windir") + "\\system32\\" + "tasklist.exe /fo csv /nh");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            // 逐行读取进程列表
            String line;
            while ((line = reader.readLine()) != null) {
                // 判断是否有 explorer.exe 进程
                if (line.contains("explorer.exe")) {
                    return true;
                }
            }

            // 关闭 BufferedReader 和 Process
            reader.close();
            process.destroy();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}

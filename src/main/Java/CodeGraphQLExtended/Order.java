package CodeGraphQLExtended;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static java.nio.channels.FileChannel.open;

public class Order {
    //
    public static void main(String[] args) throws Exception {
        boolean res = CQuery(
                "E:/File/CGQL",
                "E:/File/data/c");
//        boolean res = C2Graph("E:/File/CGQL",
//                "E:/File/data/c", "target.c");
        System.out.println(res);
    }

    public static boolean C2Graph(String toolsPath, String targetPath,String fileName) throws IOException, InterruptedException {
        String C2Path = toolsPath + "/c2graph/";
        String version = System.getProperty("os.name");
        boolean res = move(C2Path + "input/", targetPath + "/" + fileName);
        if(!res)
        {
            return false;
        }
        String cmd = "java -jar ./jar/C2Graph.jar";
        ProcessBuilder processBuilder = new ProcessBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String GraphPath = targetPath + "/C2Graph" + sdf.format(System.currentTimeMillis()) + ".log";
        boolean result;
        if(version.toLowerCase().contains("windows"))
        {
            processBuilder.command("cmd.exe", "/c", cmd);

        } else if (version.toLowerCase().contains("linux")) {
            processBuilder.command("bash", "-c", cmd);
        }
        else {
            System.out.println("error");
            return false;
        }
        result = ExecProcess(C2Path, processBuilder, GraphPath);
        res = delete(C2Path + "input/" + fileName);
        if(!res)
        {
            return false;
        }
        else
            return result;
    }

    public static boolean CQuery(String toolsPath, String targetPath) throws IOException, InterruptedException {
        String CQueryPath = toolsPath + "/CQuery/";
        String version = System.getProperty("os.name");
        String cmd = "java -jar ./jar/CodeGraphQLEngine.jar";
        ProcessBuilder processBuilder = new ProcessBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String GraphPath = targetPath + "/CQuery" + sdf.format(System.currentTimeMillis()) + ".log";
        if(version.toLowerCase().contains("windows"))
        {
            processBuilder.command("cmd.exe", "/c", cmd);

        } else if (version.toLowerCase().contains("linux")) {
            processBuilder.command("bash", "-c", cmd);
        }
        else {
            System.out.println("error");
            return false;
        }
        return ExecProcess(CQueryPath, processBuilder, GraphPath);
    }

    public static boolean Java2Graph(String toolsPath, String targetPath, String fileName) throws IOException, InterruptedException {
        String JavaPath = toolsPath + "/java2graph/";

        boolean res = move(JavaPath + "input/", targetPath + "/" + fileName);
        if(!res)
        {
            return false;
        }
        String version = System.getProperty("os.name");
        String cmd = "java -jar ./jar/Java2Graph.jar";
        ProcessBuilder processBuilder = new ProcessBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String GraphFile = targetPath + "/Java2Graph" + sdf.format(System.currentTimeMillis()) + ".log";
        boolean result;
        if(version.toLowerCase().contains("windows"))
        {
            processBuilder.command("cmd.exe", "/c", cmd);
        } else if (version.toLowerCase().contains("linux")) {
            processBuilder.command("bash", "-c", cmd);
        }
        else {
            System.out.println("error");
            return false;
        }
        result = ExecProcess(JavaPath, processBuilder, GraphFile);
        res = delete(JavaPath + "input/" + fileName);
        if(!res)
        {
            return false;
        }
        else
            return result;
    }

    public static boolean JavaQuery(String toolsPath, String targetPath) throws IOException, InterruptedException {
        String JavaQueryPath = toolsPath + "/JavaQuery/";
        String version = System.getProperty("os.name");
        String cmd = "java -jar ./jar/CodeGraphQLEngine.jar";
        ProcessBuilder processBuilder = new ProcessBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String GraphPath = targetPath + "/JavaQuery" + sdf.format(System.currentTimeMillis()) + ".log";
        if(version.toLowerCase().contains("windows"))
        {
            processBuilder.command("cmd.exe", "/c", cmd);

        } else if (version.toLowerCase().contains("linux")) {
            processBuilder.command("bash", "-c", cmd);
        }
        else {
            System.out.println("error");
            return false;
        }
        return ExecProcess(JavaQueryPath, processBuilder, GraphPath);
    }

    public static boolean move(String filePath, String targetFilePath) throws IOException, InterruptedException {
        String version = System.getProperty("os.name");
        ProcessBuilder processBuilder = new ProcessBuilder();
        if(version.toLowerCase().contains("windows"))
        {
            String cmd = "copy /y " + targetFilePath.replace("/", "\\") + " " + filePath.replace("/", "\\");
            processBuilder.command("cmd.exe", "/c", cmd);
        } else if (version.toLowerCase().contains("linux")) {
            String cmd = "cp " + targetFilePath + " " + filePath;
            processBuilder.command("bash", "-c", cmd);
        }
        else {
            System.out.println("error");
            return false;
        }
        Process process = processBuilder.start();
        int exitCode = process.waitFor();
        System.out.println("Exit with " + String.valueOf(exitCode));
        if(exitCode == 0)
        {
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean delete(String targetFilePath) throws IOException, InterruptedException {
        String version = System.getProperty("os.name");
        ProcessBuilder processBuilder = new ProcessBuilder();
        if(version.toLowerCase().contains("windows"))
        {
            String cmd = "del " + targetFilePath;
            processBuilder.command("cmd.exe", "/c", cmd.replace("/", "\\"));
        } else if (version.toLowerCase().contains("linux")) {
            String cmd = "rm " + targetFilePath;
            processBuilder.command("bash", "-c", cmd);
        }
        else {
            System.out.println("error");
            return false;
        }
        Process process = processBuilder.start();
        int exitCode = process.waitFor();
        System.out.println("Exit with " + String.valueOf(exitCode));
        if(exitCode == 0)
        {
            return true;
        }
        else {
            return false;
        }
    }

    private static boolean ExecProcess(String path, ProcessBuilder processBuilder, String filename) {
        File directory = new File(path);
        processBuilder.directory(directory);
        try{
            Process process = processBuilder.start();
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            StringBuilder res = new StringBuilder();
            while((line = reader.readLine()) != null)
            {
                System.out.println(line);
                res.append(line).append("\r");
            }
            Files.write(Paths.get(filename), res.toString().getBytes());
            int exitCode = process.waitFor();
            System.out.println("Exit with " + String.valueOf(exitCode));
            if(exitCode == 0)
            {
                return true;
            }
            else {
                return false;
            }
        } catch (IOException | InterruptedException exception)
        {
            exception.printStackTrace();
            return false;
        }
    }

}

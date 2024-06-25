package CodeGraphQLExtended;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class Config extends AnAction{
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Project project = e.getData(PlatformDataKeys.PROJECT);
        ServerState server = ServerState.getInstance(project);
        InputWrapper wrapper = new InputWrapper(project);
        if(wrapper.showAndGet())
        {
            Map<String, String> in = wrapper.get();
            String ip = in.get("ip");
            int port = Integer.parseInt(in.get("port"));
            String toolsPath = in.get("path").replace("\\", "/");
            if (checkIp(ip) && checkPort(port) && checkTools(toolsPath)) {
                server.load(ip, port, toolsPath);

                ConfigJsonCheck.C2GraphConfigCheck(ip, port,
                        toolsPath + "/c2graph/input/",
                        toolsPath + "/c2graph/config.json");
                ConfigJsonCheck.Java2GraphConfig(ip, port,
                        toolsPath + "/java2graph/config.json");
                ConfigJsonCheck.CQueryConfig(ip, port,
                        toolsPath + "/CQuery/config.json");
                ConfigJsonCheck.JavaQueryConfig(ip, port,
                        toolsPath + "/JavaQuery/config.json");


                Messages.showMessageDialog(project, "Initialized successfully","Success", Messages.getInformationIcon());
            }
            else
            {
                Messages.showMessageDialog(project, "Input data incorrect, please re-enter","Error", Messages.getInformationIcon());
            }
        }
        else
        {
            Messages.showMessageDialog(project, "An error occurred during input, please re-enter","Error", Messages.getInformationIcon());
        }
    }

    public static boolean checkIp(String ip) {
        String[] ips = ip.split("\\.");
        if(ips.length != 4) {
            return false;
        }
        else
        {
            for(int i = 0; i < 4; i++)
            {
                int i1 = Integer.parseInt(ips[i]);
                if(i1 > 255 || i1 < 0)
                {
                    return false;
                }
            }
            return true;
        }
    }

    public static boolean checkPort(int port) {
        if(port < 0 || port > 65535) {
            return false;
        }
        else
            return true;
    }

    public static boolean checkTools(String toolsPath) {
        return !toolsPath.isEmpty();
    }

}

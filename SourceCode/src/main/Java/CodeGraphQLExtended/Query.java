package CodeGraphQLExtended;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class Query extends AnAction{
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Project project = e.getData(PlatformDataKeys.PROJECT);
        ServerState server = ServerState.getInstance(project);
        if(server == null || server.getState().ip == null ||server.getState().ip.isEmpty()) {
            Messages.showMessageDialog(project, "The plugin is not initialized, please initialize the config first",
                    "Error", Messages.getInformationIcon());
            return;
        }
        PsiFile psiFile = e.getData(CommonDataKeys.PSI_FILE);
        String dirPath = psiFile.getVirtualFile().getParent().getPath();
        String fileName = psiFile.getName();
        if(fileName.endsWith(".c") || fileName.endsWith(".cpp")) {
            try {
                if(Order.CQuery(server.getState().toolsPath, dirPath))
                {
                    Messages.showMessageDialog(project, "CQuery success",
                            "Success", Messages.getInformationIcon());
                }
                else
                {
                    Messages.showMessageDialog(project, "Something went wrong",
                            "Error", Messages.getInformationIcon());
                }
            } catch (IOException | InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        } else if (fileName.endsWith(".java")) {
            try {
                if(Order.JavaQuery(server.getState().toolsPath, dirPath))
                {
                    Messages.showMessageDialog(project, "JavaQuery success",
                            "Success", Messages.getInformationIcon());
                }
                else
                {
                    Messages.showMessageDialog(project, "Something went wrong",
                            "Error", Messages.getInformationIcon());
                }
            } catch (IOException | InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        } else
        {
            Messages.showMessageDialog(project, "The plugin currently does not support this file type",
                    "Error", Messages.getInformationIcon());
        }
    }
}

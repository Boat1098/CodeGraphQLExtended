package CodeGraphQLExtended;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class InputWrapper extends DialogWrapper {

    private JTextField ip = new JTextField();
    private JTextField port = new JTextField();
    private JTextField path = new JTextField();

    public InputWrapper(Project project) {
        super(project); // use current window as parent
        setTitle("Test DialogWrapper");
        init();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        JPanel dialogPanel = new JPanel(new GridLayout(3,2));
        dialogPanel.setSize(500, 200);

        JLabel ipLabel = new JLabel("IP:");
        ipLabel.setLabelFor(ip);
        ipLabel.setPreferredSize(new Dimension(50, 40));
        ip.setPreferredSize(new Dimension(200, 40));
        dialogPanel.add(ipLabel);
        dialogPanel.add(ip);


        JLabel portLabel = new JLabel("Port:");
        portLabel.setLabelFor(port);
        portLabel.setPreferredSize(new Dimension(50, 40));
        port.setPreferredSize(new Dimension(200, 40));
        dialogPanel.add(portLabel);
        dialogPanel.add(port);

        JLabel pathLabel = new JLabel("Tool Path:");
        pathLabel.setLabelFor(path);
        pathLabel.setPreferredSize(new Dimension(50, 40));
        path.setPreferredSize(new Dimension(200, 40));
        dialogPanel.add(pathLabel);
        dialogPanel.add(path);


        return dialogPanel;
    }

    public Map<String, String> get(){
        Map<String, String> map = new HashMap<>();
        map.put("ip", ip.getText());
        map.put("port", port.getText());
        map.put("path", path.getText());
        return map;
    }
}

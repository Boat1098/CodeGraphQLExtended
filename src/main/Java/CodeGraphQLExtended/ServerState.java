package CodeGraphQLExtended;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.project.Project;

@State(name = "config.xml")
class ServerState implements PersistentStateComponent<ServerState.State> {

    public static ServerState getInstance(Project project) {
        // implementation according to Application/Project level service
        return project.getService(ServerState.class);
    }

    static class State {
        public String ip;
        public int port;
        public String toolsPath;
    }

    private State myState = new State();

    @Override
    public State getState() {
        return myState;
    }

    @Override
    public void loadState(@NotNull State state) {
        myState = state;
    }

    public void load(String ip, int port, String toolsPath) {
        State newState = new State();
        newState.ip = ip;
        newState.port = port;
        newState.toolsPath = toolsPath;
        loadState(newState);
    }
}

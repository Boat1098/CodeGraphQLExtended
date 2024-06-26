package CodeGraphQLExtended;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ConfigJsonCheck {
    public static void C2GraphConfigCheck(String ip, int port, String targetPath, String configPath)
    {
        ObjectMapper mapper = new ObjectMapper();
        try {
            File file = new File(configPath);
            if(file.exists())
            {
                String json = Files.readString(Paths.get(configPath));
                C2GraphConfig config = mapper.readValue(json, C2GraphConfig.class);
                config.host = ip;
                config.port = port;
                config.project = targetPath;
                config.dir = "";
                config.includePath = new String[0];
                String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(config);
                Files.write(Paths.get(configPath), jsonString.getBytes());
            }
            else
            {
                C2GraphConfig config = new C2GraphConfig();
                config.host = ip;
                config.port = port;
                config.project = targetPath;
                config.dir = "";
                config.includePath = new String[0];
                String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(config);
                Files.write(Paths.get(configPath), jsonString.getBytes());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void Java2GraphConfig(String ip, int port, String configPath)
    {
        ObjectMapper mapper = new ObjectMapper();
        try {
            File file = new File(configPath);
            if(file.exists())
            {
                String json = Files.readString(Paths.get(configPath));
                Java2GraphConfig config = mapper.readValue(json, Java2GraphConfig.class);
                config.host = ip;
                config.port = port;
                String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(config);
                Files.write(Paths.get(configPath), jsonString.getBytes());
            }
            else
            {
                Java2GraphConfig config = new Java2GraphConfig();
                config.host = ip;
                config.port = port;
                String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(config);
                Files.write(Paths.get(configPath), jsonString.getBytes());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void CQueryConfig(String ip, int port, String configPath)
    {
        ObjectMapper mapper = new ObjectMapper();
        try {
            File file = new File(configPath);
            if(file.exists())
            {
                String json = Files.readString(Paths.get(configPath));
                CQueryConfig config = mapper.readValue(json, CQueryConfig.class);
                config.gremlin.host = ip;
                config.gremlin.port = port;
                String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(config);
                Files.write(Paths.get(configPath), jsonString.getBytes());
            }
            else
            {
                CQueryConfig config = new CQueryConfig();
                config.gremlin.host = ip;
                config.gremlin.port = port;
                config.cache.type = "none";
                config.learning.enabled = false;
                String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(config);
                Files.write(Paths.get(configPath), jsonString.getBytes());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void JavaQueryConfig(String ip, int port, String configPath)
    {
        ObjectMapper mapper = new ObjectMapper();
        try {
            File file = new File(configPath);
            if(file.exists())
            {
                String json = Files.readString(Paths.get(configPath));
                JavaQueryConfig config = mapper.readValue(json, JavaQueryConfig.class);
                config.host = ip;
                config.port = port;
                String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(config);
                Files.write(Paths.get(configPath), jsonString.getBytes());
            }
            else
            {
                JavaQueryConfig config = new JavaQueryConfig();
                config.host = ip;
                config.port = port;
                String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(config);
                Files.write(Paths.get(configPath), jsonString.getBytes());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args)
    {
        ObjectMapper mapper = new ObjectMapper();
        String configPath = "E:\\File\\CodeGraphQL-C2Graph\\src\\CodeGraphQLEngine\\config.json";
        try {
            File file = new File(configPath);
            String json = Files.readString(Paths.get(configPath));
            CQueryConfig config = mapper.readValue(json, CQueryConfig.class);
            System.out.println(config.gremlin.host);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

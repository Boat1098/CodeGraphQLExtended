package CodeGraphQLExtended;

public class CQueryConfig {
    public gremlin gremlin;
    public cache cache;
    public learning learning;
}

class gremlin {
    public String host;
    public int port;
}

class cache {
    public String type;
    public redis redis;
}

class redis {
    public String host;
    public int port;
    public String password;
    public int db;
    public int shard;
}

class learning {
    public boolean enabled;
    public String host;
    public int port;
    public String baseUrl;
}
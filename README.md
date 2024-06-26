# CodeGraphQLExtended
This is a plugin for the project [CodeGraphQL](https://github.com/hongshuobuaa/CodeGraphQL/tree/main). This is a tool for statically detecting software defects and vulnerability via code graph queries. 

## Before Move On

In the current version, this plugin requires users to configure the initial version of the detection tool themselves. Specifically, users need to store the corresponding detection tools extracted in jar format locally in the specified format, or directly use the content prepared for you。

```
-─/tools/CGQL
    ├─c2graph
    │  ├─input
    │  ├─jar
    │  └─logs
    ├─CQuery
    │  └─jar
    ├─java2graph
    │  ├─input
    │  ├─jar
    │  ├─output
    │  │  ├─AST
    │  │  ├─CFG
    │  │  ├─CG
    │  │  ├─newAST
    │  │  └─PDG
    │  └─spooned
    │      ├─a
    │      └─xxd
    └─JavaQuery
        └─jar
```

In addition, this detection tool requires you to configure the Gremlin server properly. Please configure the server according to the documentation provided in CodeGraphQL and ensure that it can be used. You can refer to this [document](./docs/如何在gremlin-server中嵌入neo4j.md)

## How to use it

Firstly, please set the IP address and port number of the server, as well as configure the local detection tool directory, such as `E:/CodeGraphQLExtended/tools/CGQL`

Subsequently, you can perform image conversion and querying on the target file

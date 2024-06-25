

public class Graph_Check {
    public static java.util.List read(java.lang.String filename) {
        try {
            java.util.List<java.util.List<java.lang.String>> list = new java.util.ArrayList<>();
            java.io.FileReader fileReader = new java.io.FileReader(filename);
            com.opencsv.CSVReader csvReader = new com.opencsv.CSVReader(fileReader);
            java.lang.String[] line = csvReader.readNext();
            while (line != null) {
                list.add(java.util.Arrays.asList(line));
                line = csvReader.readNext();
            } 
            csvReader.close();
            return list;
        } catch (java.lang.Exception exception) {
            java.lang.System.out.println(exception);
            return null;
        }
    }

    /* nodes_IDENTIFIER_header:
    0: ID
    7:LINE_NUMBER
    11:TypeFullName
    size:12
     */
    /* nodes_METHOD_PARAMETER_IN_header:
    0:ID
    1:Label
    13: Typefullname
    9: lineNumber
    size: 14
     */
    /* pointer: *
    array: [
    others: malloc,fork,calloc¡¢realloc
     */
    public static java.util.List getPointerNode(java.util.List<java.lang.String> lines) {
        java.lang.String identiferFile = "./result/nodes_IDENTIFIER_data.csv";
        java.util.List<java.util.List<java.lang.String>> identifierNode = Graph_Check.read(identiferFile);
        java.util.List<java.lang.String> node = null;
        java.lang.String fullName = null;
        java.lang.String line = null;
        java.util.List<java.util.List<java.lang.String>> pointerNode = new java.util.ArrayList<>();
        java.util.List<java.lang.String> fullNames = new java.util.ArrayList<>();
        int i = 0;
        for (i = 0; i < (identifierNode.size()); i++) {
            node = identifierNode.get(i);
            fullName = node.get(11);
            line = lines.get(((java.lang.Integer.parseInt(node.get(7))) - 1));
            if (fullName.contains("*")) {
                pointerNode.add(node);
                fullNames.add(fullName);
                continue;
            }
            if (fullName.contains("[")) {
                pointerNode.add(node);
                fullNames.add(fullName);
                continue;
            }
            if (line.contains("fork")) {
                pointerNode.add(node);
                fullNames.add(fullName);
                continue;
            }
            if (line.contains("malloc")) {
                pointerNode.add(node);
                fullNames.add(fullName);
                continue;
            }
            if (line.contains("realloc")) {
                pointerNode.add(node);
                fullNames.add(fullName);
                continue;
            }
            if (line.contains("calloc")) {
                pointerNode.add(node);
                fullNames.add(fullName);
                continue;
            }
        }
        java.lang.String paramterFile = "./result/nodes_METHOD_PARAMETER_IN_data.csv";
        java.util.List<java.util.List<java.lang.String>> paramterNode = Graph_Check.read(paramterFile);
        for (i = 0; i < (paramterNode.size()); i++) {
            node = paramterNode.get(i);
            fullName = node.get(13);
            if (fullName.contains("*")) {
                pointerNode.add(node);
                continue;
            }
            if (fullName.contains("[")) {
                pointerNode.add(node);
                continue;
            }
            if (fullNames.contains(fullName)) {
                pointerNode.add(node);
                continue;
            }
        }
        return pointerNode;
    }

    /* nodes_call_header:
    0:id
    1:label
    8: lineNumber
    14:TypeFullName
    size:15
     */
    public static java.util.List getCallNode(java.util.List pointerId) {
        java.lang.String callNodePath = "./result/nodes_CALL_data.csv";
        java.util.List<java.util.List<java.lang.String>> callNode = Graph_Check.read(callNodePath);
        java.util.List<java.lang.String> callID = new java.util.ArrayList<>();
        int i = 0;
        for (i = 0; i < (callNode.size()); i++) {
            callID.add(callNode.get(i).get(0));
        }
        java.lang.String edgesCDGPath = "./result/edges_CDG_data.csv";
        java.util.List<java.util.List<java.lang.String>> edgesCDG = Graph_Check.read(edgesCDGPath);
        java.util.List<java.util.List<java.lang.String>> node = new java.util.ArrayList<>();
        java.util.List<java.lang.String> id = new java.util.ArrayList<>();
        for (i = 0; i < (edgesCDG.size()); i++) {
            java.util.List<java.lang.String> edge = edgesCDG.get(i);
            if (callID.contains(edge.get(0))) {
                if (pointerId.contains(edge.get(1))) {
                    if (id.contains(edge.get(0))) {
                        continue;
                    } else {
                        id.add(edge.get(0));
                    }
                }
            }
        }
        java.lang.String edgesAstPath = "./result/edges_AST_data.csv";
        java.util.List<java.util.List<java.lang.String>> edgesAst = Graph_Check.read(edgesAstPath);
        for (i = 0; i < (edgesAst.size()); i++) {
            java.util.List<java.lang.String> edge = edgesAst.get(i);
            if (callID.contains(edge.get(0))) {
                if (pointerId.contains(edge.get(1))) {
                    if (id.contains(edge.get(0))) {
                        continue;
                    } else {
                        id.add(edge.get(0));
                    }
                }
            }
        }
        for (i = 0; i < (id.size()); i++) {
            node.add(callNode.get(callID.indexOf(id.get(i))));
        }
        return node;
    }

    /* nodes_IDENTIFIER_header:
    0: ID
    7:LINE_NUMBER
    11:TypeFullName
    size:12
     */
    /* nodes_METHOD_PARAMETER_IN_header:
    0:ID
    1:Label
    13: Typefullname
    9: lineNumber
    size: 14
     */
    /* nodes_call_header:
    0:id
    1:label
    8: lineNumber
    14:TypeFullName
    size:15
     */
    public static java.util.List<java.lang.Integer> getLinesNumber(java.util.List<java.util.List<java.lang.String>> pointerNode, java.util.List<java.util.List<java.lang.String>> callNode) {
        int i = 0;
        java.util.List<java.lang.Integer> linesNumber = new java.util.ArrayList<>();
        for (i = 0; i < (pointerNode.size()); i++) {
            java.util.List<java.lang.String> pointer = pointerNode.get(i);
            if ((pointer.size()) == 12) {
                if (!(linesNumber.contains(java.lang.Integer.parseInt(pointer.get(7))))) {
                    linesNumber.add(java.lang.Integer.parseInt(pointer.get(7)));
                }
            } else {
                if (!(linesNumber.contains(java.lang.Integer.parseInt(pointer.get(9))))) {
                    linesNumber.add(java.lang.Integer.parseInt(pointer.get(9)));
                }
            }
        }
        for (i = 0; i < (callNode.size()); i++) {
            java.util.List<java.lang.String> call = callNode.get(i);
            if (!(linesNumber.contains(java.lang.Integer.parseInt(call.get(8))))) {
                linesNumber.add(java.lang.Integer.parseInt(call.get(8)));
            }
        }
        java.util.Comparator<java.lang.Integer> comparator = java.util.Comparator.comparingInt(java.lang.Integer::valueOf);
        linesNumber.sort(comparator);
        return linesNumber;
    }

    public static void clean() {
        try {
            java.lang.String delcmd = "del /q /f result.txt";
            java.lang.Runtime delrun = java.lang.Runtime.getRuntime();
            java.lang.Process delpro = delrun.exec(new java.lang.String[]{ "cmd.exe", "/c", delcmd });
        } catch (java.io.IOException exception) {
            exception.printStackTrace();
        }
    }

    public static void export(java.util.List<java.lang.Integer> lines, java.util.List<java.lang.String> file) {
        try {
            int i = 0;
            java.lang.String outFile = "result.txt";
            java.io.File out = new java.io.File(outFile);
            java.io.FileWriter fileWriter = new java.io.FileWriter(out);
            for (i = 0; i < (lines.size()); i++) {
                java.lang.String line = file.get(((lines.get(i)) - 1));
                fileWriter.write(line);
                fileWriter.write("\n");
            }
            fileWriter.flush();
            fileWriter.close();
        } catch (java.lang.Exception exception) {
            exception.printStackTrace();
        } catch (java.lang.OutOfMemoryError error) {
            error.printStackTrace();
        }
    }

    public static java.util.List readFile(java.lang.String filePath) {
        try {
            java.util.List<java.lang.String> file = java.nio.file.Files.readAllLines(java.nio.file.Paths.get(filePath));
            return file;
        } catch (java.lang.OutOfMemoryError error) {
            error.printStackTrace();
            return null;
        } catch (java.io.IOException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public static void main(java.lang.String[] args) {
        java.util.List<java.lang.String> file = Graph_Check.readFile("target.c");
        if (file == null) {
            return;
        }
        java.util.List<java.util.List<java.lang.String>> pointerIdentifierNode = Graph_Check.getPointerNode(file);
        java.util.List<java.lang.String> pointerId = new java.util.ArrayList<>();
        int i = 0;
        for (i = 0; i < (pointerIdentifierNode.size()); i++) {
            pointerId.add(pointerIdentifierNode.get(i).get(0));
        }
        java.util.List<java.util.List<java.lang.String>> callNode = Graph_Check.getCallNode(pointerId);
        java.util.List<java.lang.Integer> lineNumber = Graph_Check.getLinesNumber(pointerIdentifierNode, callNode);
        Graph_Check.clean();
        Graph_Check.export(lineNumber, file);
        return;
    }
}


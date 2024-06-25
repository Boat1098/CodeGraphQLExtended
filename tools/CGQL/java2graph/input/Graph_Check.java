import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.lang.Integer;
import java.util.stream.Stream;

import com.opencsv.CSVReader;
import org.apache.velocity.util.ArrayListWrapper;


public class Graph_Check {
    public static List read(String filename)
    {
        try{
            List<List<String>> list = new ArrayList<>();
            FileReader fileReader = new FileReader(filename);
            CSVReader csvReader = new CSVReader(fileReader);
            String[] line = csvReader.readNext();
            while(line != null)
            {
                list.add(Arrays.asList(line));
                line = csvReader.readNext();
            }

            csvReader.close();
            return list;
        }
        catch(Exception exception)
        {
            System.out.println(exception);
            return null;
        }
    }
    /*
    nodes_IDENTIFIER_header:
        0: ID
        7:LINE_NUMBER
        11:TypeFullName
        size:12
    */
    /*
    nodes_METHOD_PARAMETER_IN_header:
        0:ID
        1:Label
        13: Typefullname
        9: lineNumber
        size: 14
     */
    /*
    pointer: *
    array: [
    others: malloc,fork,calloc、realloc
     */
    public static List getPointerNode(List<String> lines)
    {
        String identiferFile = "./result/nodes_IDENTIFIER_data.csv";
        List<List<String>> identifierNode = read(identiferFile);
        List<String> node = null;
        String fullName = null;
        String line = null;
        List<List<String>> pointerNode = new ArrayList<>();
        List<String> fullNames = new ArrayList<>();
        int i = 0;
        for(i = 0;i<identifierNode.size();i++)
        {
            node = identifierNode.get(i);
            fullName = node.get(11);
            line = lines.get(Integer.parseInt(node.get(7)) - 1);
            if(fullName.contains("*"))
            {
                pointerNode.add(node);
                fullNames.add(fullName);
                continue;
            }
            if(fullName.contains("["))
            {
                pointerNode.add(node);
                fullNames.add(fullName);
                continue;
            }
            if(line.contains("fork"))
            {
                pointerNode.add(node);
                fullNames.add(fullName);
                continue;
            }
            if(line.contains("malloc"))
            {
                pointerNode.add(node);
                fullNames.add(fullName);
                continue;
            }
            if(line.contains("realloc"))
            {
                pointerNode.add(node);
                fullNames.add(fullName);
                continue;
            }
            if(line.contains("calloc"))
            {
                pointerNode.add(node);
                fullNames.add(fullName);
                continue;
            }
        }
        String paramterFile = "./result/nodes_METHOD_PARAMETER_IN_data.csv";
        List<List<String>> paramterNode = read(paramterFile);
        for(i = 0;i<paramterNode.size();i++)
        {
            node = paramterNode.get(i);
            fullName = node.get(13);
            if(fullName.contains("*"))
            {
                pointerNode.add(node);
                continue;
            }
            if(fullName.contains("["))
            {
                pointerNode.add(node);
                continue;
            }
            if(fullNames.contains(fullName))
            {
                pointerNode.add(node);
                continue;
            }
        }
        return pointerNode;
    }
    /*
    nodes_call_header:
        0:id
        1:label
        8: lineNumber
        14:TypeFullName
        size:15
     */
    public static List getCallNode(List pointerId)
    {
        String callNodePath = "./result/nodes_CALL_data.csv";
        List<List<String>> callNode = read(callNodePath);
        List<String> callID = new ArrayList<>();
        int i = 0;
        for(i = 0;i < callNode.size();i++)
        {
            callID.add(callNode.get(i).get(0));
        }
        String edgesCDGPath = "./result/edges_CDG_data.csv";
        List<List<String>> edgesCDG = read(edgesCDGPath);
        List<List<String>> node = new ArrayList<>();
        List<String> id = new ArrayList<>();
        for(i = 0;i < edgesCDG.size();i++)
        {
            List<String> edge = edgesCDG.get(i);
            if(callID.contains(edge.get(0)))
            {
                if(pointerId.contains(edge.get(1)))
                {
                    if(id.contains(edge.get(0)))
                    {
                        continue;
                    }
                    else
                    {
                        id.add(edge.get(0));
                    }
                }
            }
        }
        String edgesAstPath = "./result/edges_AST_data.csv";
        List<List<String>> edgesAst = read(edgesAstPath);
        for(i = 0;i < edgesAst.size();i++)
        {
            List<String> edge = edgesAst.get(i);
            if(callID.contains(edge.get(0)))
            {
                if(pointerId.contains(edge.get(1)))
                {
                    if(id.contains(edge.get(0)))
                    {
                        continue;
                    }
                    else
                    {
                        id.add(edge.get(0));
                    }
                }
            }
        }

        for(i = 0;i < id.size();i++)
        {
            node.add(callNode.get(callID.indexOf(id.get(i))));
        }
        return node;
    }
    /*
    nodes_IDENTIFIER_header:
        0: ID
        7:LINE_NUMBER
        11:TypeFullName
        size:12
    */
    /*
    nodes_METHOD_PARAMETER_IN_header:
        0:ID
        1:Label
        13: Typefullname
        9: lineNumber
        size: 14
     */
    /*
    nodes_call_header:
        0:id
        1:label
        8: lineNumber
        14:TypeFullName
        size:15
     */
    public static List<Integer> getLinesNumber(List<List<String>> pointerNode, List<List<String>> callNode)
    {
        int i = 0;
        List<Integer> linesNumber = new ArrayList<>();
        for(i = 0;i < pointerNode.size();i++)
        {
            List<String> pointer = pointerNode.get(i);
            if(pointer.size() == 12)
            {
                if(!linesNumber.contains(Integer.parseInt(pointer.get(7))))
                {
                    linesNumber.add(Integer.parseInt(pointer.get(7)));
                }
            }
            else
            {
                if(!linesNumber.contains(Integer.parseInt(pointer.get(9))))
                {
                    linesNumber.add(Integer.parseInt(pointer.get(9)));
                }
            }
        }
        for(i = 0;i < callNode.size();i++)
        {
            List<String> call = callNode.get(i);
            if(!linesNumber.contains(Integer.parseInt(call.get(8))))
            {
                linesNumber.add(Integer.parseInt(call.get(8)));
            }
        }
        Comparator<Integer> comparator = Comparator.comparingInt(Integer::valueOf);
        linesNumber.sort(comparator);
        return linesNumber;
    }

    public static void clean()
    {
        try{
            String delcmd = "del /q /f result.txt";
            Runtime delrun = Runtime.getRuntime();
            Process delpro = delrun.exec(new String[]{"cmd.exe", "/c", delcmd});
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }
    }

    public static void export(List<Integer> lines, List<String> file)
    {
        try{
            int i = 0;
            String outFile = "result.txt";
            File out = new File(outFile);
            FileWriter fileWriter = new FileWriter(out);
            for(i = 0;i<lines.size();i++)
            {
                String line = file.get(lines.get(i) - 1);
                fileWriter.write(line);
                fileWriter.write("\n");
            }
            fileWriter.flush();
            fileWriter.close();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        catch (OutOfMemoryError error)
        {
            error.printStackTrace();
        }
    }

    public static List readFile(String filePath)
    {
        try{
            List<String> file = Files.readAllLines(Paths.get(filePath));
            return file;
        }
        catch (OutOfMemoryError error)
        {
            error.printStackTrace();
            return null;
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args)
    {
        List<String> file = readFile("target.c");
        if(file == null)
        {
            return;
        }
        List<List<String>> pointerIdentifierNode = getPointerNode(file);
        List<String> pointerId = new ArrayList<>();
        int i = 0;
        for(i = 0;i < pointerIdentifierNode.size();i++)
        {
            pointerId.add(pointerIdentifierNode.get(i).get(0));
        }
        List<List<String>> callNode = getCallNode(pointerId);
        List<Integer> lineNumber = getLinesNumber(pointerIdentifierNode, callNode);
        clean();
        export(lineNumber, file);

        return;
    }

}

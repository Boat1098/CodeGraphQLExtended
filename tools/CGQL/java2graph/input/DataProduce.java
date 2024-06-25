import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class DataProduce {
    public static List<String> readFile(String filePath)
    {
        try
        {
            if(!filePath.endsWith("txt"))
            {
                return null;
            }
            else
            {
                List<String> file = Files.readAllLines(Paths.get(filePath));
                return file;
            }
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

}

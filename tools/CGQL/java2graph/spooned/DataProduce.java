

public class DataProduce {
    public static java.util.List<java.lang.String> readFile(java.lang.String filePath) {
        try {
            if (!(filePath.endsWith("txt"))) {
                return null;
            } else {
                java.util.List<java.lang.String> file = java.nio.file.Files.readAllLines(java.nio.file.Paths.get(filePath));
                return file;
            }
        } catch (java.lang.OutOfMemoryError error) {
            error.printStackTrace();
            return null;
        } catch (java.io.IOException exception) {
            exception.printStackTrace();
            return null;
        }
    }
}


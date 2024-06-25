

public class Joern_check {
    public static boolean scan(java.lang.String filepath) {
        try {
            java.lang.String scancmd = ".\\joern-cli\\joern-parse " + filepath;
            java.lang.System.out.println(scancmd);
            java.lang.Runtime scanrun = java.lang.Runtime.getRuntime();
            java.lang.Process scanpro = scanrun.exec(new java.lang.String[]{ "cmd.exe", "/c", scancmd });
            int status = scanpro.waitFor();
            if (status != 0) {
                java.lang.System.out.println("Failed to call shell's command");
                return false;
            } else {
                java.lang.System.out.println("Scan Finished!");
                return true;
            }
        } catch (java.lang.InterruptedException exception) {
            exception.printStackTrace();
            return false;
        } catch (java.io.IOException ioException) {
            ioException.printStackTrace();
            return false;
        }
    }

    public static void clean() {
        try {
            java.lang.String delcmd = "rd /s /q result";
            java.lang.Runtime delrun = java.lang.Runtime.getRuntime();
            java.lang.Process delpro = delrun.exec(new java.lang.String[]{ "cmd.exe", "/c", delcmd });
            int delsta = delpro.waitFor();
        } catch (java.io.IOException exception) {
            exception.printStackTrace();
        }
    }

    public static boolean export() {
        try {
            java.lang.String outcmd = ".\\joern-cli\\joern-export --repr=all --format=neo4jcsv --out ./result";
            java.lang.Runtime outrun = java.lang.Runtime.getRuntime();
            java.lang.Process outpro = outrun.exec(new java.lang.String[]{ "cmd.exe", "/c", outcmd });
            int status = outpro.waitFor();
            if (status != 0) {
                java.lang.System.out.println("Failed to call shell's command");
                return false;
            } else {
                return true;
            }
        } catch (java.lang.InterruptedException exception) {
            exception.printStackTrace();
            return false;
        } catch (java.io.IOException ioException) {
            ioException.printStackTrace();
            return false;
        }
    }

    public static void main(java.lang.String[] args) {
        java.io.BufferedReader reader = null;
        try {
            reader = new java.io.BufferedReader(new java.io.InputStreamReader(java.lang.System.in));
            java.lang.System.out.println("请输入文件路径:");
            java.lang.String filepath = reader.readLine();
            if (Joern_check.scan(filepath)) {
                Joern_check.clean();
                if (Joern_check.export()) {
                    java.lang.System.out.println("Success");
                } else {
                    java.lang.System.out.println("Export Error!");
                }
            } else {
                java.lang.System.out.println("Scan Error!");
            }
        } catch (java.io.IOException ec) {
            ec.printStackTrace();
        }
    }

    public static boolean check() {
        try {
            java.lang.String filepath = "target.c";
            if (Joern_check.scan(filepath)) {
                Joern_check.clean();
                if (Joern_check.export()) {
                    java.lang.System.out.println("Success");
                    return true;
                } else {
                    java.lang.System.out.println("Export Error!");
                    return false;
                }
            } else {
                java.lang.System.out.println("Scan Error!");
                return false;
            }
        } catch (java.lang.Exception ec) {
            ec.printStackTrace();
            return false;
        }
    }
}


package microsoft.test;


import org.apache.commons.lang3.StringUtils;

public class MainClass {
    public static void main(String[] args) {
      //Compare two strings. Case Sensitive
      System.out.println(StringUtils.difference("package microsoft.test;\n" +
              "\n" +
              "\n" +
              "import org.apache.commons.lang3.StringUtils;\n" +
              "\n" +
              "public class MainClass {\n" +
              "    public static void main(String[] args) {\n" +
              "      //Compare two strings. Case Sensitive\n" +
              "      System.out.println(StringUtils.difference(\"\", \"\"));\n" +
              "\n" +
              "    }\n" +
              "}",


              "package microsoft.test;\n" +
              "\n" +
              "\n" +
              "import org.apache.commons.lang3.afdsf;\n" +
              "\n" +
              "public class MainClass {\n" +
              "    public static void main(String[] arwgs) {\n" +
              "      //Compare two strings. Case Sensitive\n" +
              "      System.out.println(SteringUtils.difference(\"\", \"\"));\n" +
              "\n" +
              "    }\n" +
              "}"));

    }
}
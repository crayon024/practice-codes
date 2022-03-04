package self.practice.codes.io.javaio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class InputOutputStream {

    public static final String FILE_PATH = "F:\\1aIdeaProject\\practice-codes\\src\\main\\resources\\static\\io\\test.txt";

    public static void main(String[] args) throws IOException {
        InputOutputStream inputOutputStream = new InputOutputStream();
        inputOutputStream.readFile();
        System.out.println("----");
        inputOutputStream.classPath();
        //File file = new File("a.txt");
        //file.createNewFile(); 相对路径基于 jvm 的启动路径而言的
    }

    public void readFile() throws IOException {
        File file = new File(".");
        System.out.println(file.getAbsolutePath());

        try (InputStream input = new FileInputStream(FILE_PATH)) {
            byte[] bytes = new byte[1024];
            int n;
            while ((n = input.read(bytes)) != -1) {
                System.out.println("byte counts: " + n);
                int i = 1;
                for (byte b : bytes) {
                    System.out.println(b);
                    if (++i > n) break;
                }
            }
        }
    }

    public void classPath() throws IOException {
        // classpath: 编译后的 classes 文件夹
        try (InputStream input = getClass().getResourceAsStream("/static/io/test.txt")) {
            int n;
            while ((n = input.read()) != -1) {
                System.out.println(n);
            }
        }
    }

    public Integer testMockito(Integer input) {

        return input * 10;
    }
}

package self.practice.codes.io.week02.gateway;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 单线程 Socket 版本
 * @author Crayon
 */
public class HttpServer01 {

    public static void main(String[] args) {
        try {
            ServerSocket socket = new ServerSocket(8081);
            while (true) {
                Socket accept = socket.accept();
                response(accept);
            }
        } catch (IOException e) {
            System.err.println("response(): " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * https://stackoverflow.com/questions/9260126/what-are-the-differences-between-char-literals-n-and-r-in-java
     * https://stackoverflow.com/questions/10788125/a-simple-http-server-with-java-socket
     */
    private static void response(Socket socket) {
        try (OutputStream outputStream = socket.getOutputStream();
             OutputStreamWriter optWriter = new OutputStreamWriter(outputStream))
        {
            String body = "hello,nio1";

            optWriter.write("HTTP/1.1 200 OK\r\n");
            optWriter.write("Content-Type:text/html;charset=utf-8\r\n");
            optWriter.write("Content-Length:" + body.getBytes().length + "\r\n");
            optWriter.write("\r\n");
            optWriter.write(body);
            optWriter.flush();
            socket.close();
        } catch (IOException e) {
            System.err.println("response(): " + e.getMessage());
            e.printStackTrace();
        }

    }

    private static void response2(Socket socket) {
        try {
            String body = "hello,nio1";

            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type:text/html;charset=utf-8");
            printWriter.println("Content-Length:" + body.getBytes().length);
            printWriter.println();
            printWriter.write(body);
            printWriter.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

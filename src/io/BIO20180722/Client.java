package io.BIO20180722;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {

        final  int DEFAULT_PORT = 5555;
        String DEFAULT_SERVER_IP = "127.0.0.1";
        Socket clientSocket = new Socket(DEFAULT_SERVER_IP,DEFAULT_PORT);

        Scanner scanner = new Scanner(System.in);

        System.out.println("说点什么吧：");
        String message = scanner.nextLine();

        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;

        try {
            out = new PrintWriter(clientSocket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            out.println(message);
            out.flush();

            System.out.println("___结果为：" + in.readLine());

        } catch (Exception e) {

        }finally{
        //一下必要的清理工作
        if(in != null){
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            in = null;
        }
        if(out != null){
            out.close();
            out = null;
        }
        if(socket != null){
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            socket = null;
        }
    }


}
}

package io.BIO20180722;

import java.io.*;
import java.net.Socket;

public class ServerHandler implements Runnable{

    private Socket socket;

    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader bufferedReader = null;
        PrintWriter out = null;


        try {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());

            String message;
            while(true){

                if((message = bufferedReader.readLine()) != null){
                    System.out.println("收到来自" +Thread.currentThread()+"客户端的信息：" +message);

                    out.write("收到消息了");
                    out.println("收到消息了");
                    out.flush();
                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            //一些必要的清理工作
            if(bufferedReader != null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                bufferedReader = null;
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

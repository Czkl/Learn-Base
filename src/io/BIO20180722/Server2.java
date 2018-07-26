package io.BIO20180722;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server2 {


    //线程池 懒汉式的单例
    private static ExecutorService executorService = Executors.newFixedThreadPool(20);

    public static void main(String[] args) throws IOException {

        final  int DEFAULT_PORT = 5555;
        ServerSocket serverSocket = null;
        int count = 0;
        try{
            //通过构造函数创建ServerSocket
            //如果端口合法且空闲，服务端就监听成功
            serverSocket = new ServerSocket(DEFAULT_PORT);

            System.out.println("服务器已启动，端口号：" + DEFAULT_PORT);
            while(true){
                Socket socket = serverSocket.accept();
                //当有新的客户端接入时，会执行下面的代码
                //然后创建一个新的线程处理这条Socket链路

                executorService.execute(new ServerHandler(socket));
            }
        }finally{
            //一些必要的清理工作
            if(serverSocket != null){
                System.out.println("服务器已关闭。");
                serverSocket.close();
                serverSocket = null;
            }
        }

    }
}

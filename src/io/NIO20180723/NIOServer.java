package io.NIO20180723;

import arithmetic.sort.sort20180720.SelectSort;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {

    private int port = 6666;
    private InetSocketAddress address = null;
    private Selector selector;

    public NIOServer(int port) {
        this.port = port;
        try {
            address = new InetSocketAddress(this.port);

            ServerSocketChannel serverSocket = ServerSocketChannel.open(); // = new ServerSocket();

            //绑定监听端口
            serverSocket.socket().bind(address);

            // 设置为非阻塞模式.
            serverSocket.configureBlocking(false);


            selector = Selector.open();

            serverSocket.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println("服务器已开启：" + this.port);
        } catch (Exception  e) {
            e.printStackTrace();
        }
    }


    public void listen(){
        try {
                //轮询

            while (true) {
                int wait = this.selector.select(); // accpet() 阻塞的，select（）也是阻塞的
                if(wait == 0){
                    continue;
                }

                //SelectionKey 代表的是客户
                Set<SelectionKey> selectionKeys = selector.selectedKeys();

                Iterator<SelectionKey> iterator = selectionKeys.iterator();

                while (iterator.hasNext()){

                    SelectionKey key = iterator.next();

                    // 然后处理io
                    process(key);

                    iterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  void process(SelectionKey key) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        if(key.isAcceptable()){ // 是否
            ServerSocketChannel server = (ServerSocketChannel) key.channel();
            //通过ServerSocketChannel的accept创建SocketChannel实例
            //完成该操作意味着完成TCP三次握手，TCP物理链路正式建立
            SocketChannel client = server.accept();
            //设置为非阻塞的
            client.configureBlocking(false);
            //注册为读
            client.register(selector, SelectionKey.OP_READ);
        } else if (key.isReadable()) {

            SocketChannel client = (SocketChannel) key.channel();

            int len = client.read(byteBuffer);

            if (len > 0) {

                byteBuffer.flip();

                String content = new String(byteBuffer.array(), 0, len);
                client.register(selector, SelectionKey.OP_WRITE);
                System.out.println(content);
            }

        } else if (key.isWritable()) {
            SocketChannel client = (SocketChannel) key.channel();

            client.write(ByteBuffer.wrap("Hello World".getBytes()));
            client.close();
        }
    }

    public static void main(String[] args) {

        new NIOServer(6666).listen();
    }
}

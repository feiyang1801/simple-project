
package cn.gy.nio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

/**
 *  bio server socket
 * @author yang.gao created on 2016/11/10 14:40
 * @version $Id$
 */
public class ServerSocketTest {

    public void ehcoServer() throws Exception{
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(8081));
        while (true){
            Socket socket = serverSocket.accept();
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String msg = reader.readLine();
            System.out.println("input : " + msg);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            writer.write("hello client");
            writer.flush();
            reader.close();
            writer.close();
            socket.close();
        }

    }

    public static void main(String[] args) throws Exception{
        new ServerSocketTest().ehcoServer();
    }

}

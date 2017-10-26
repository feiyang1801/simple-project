
package cn.gy.nio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author yang.gao created on 2016/10/24 16:58
 * @version $Id$
 */
public class Sender {

    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("10.86.36.109",2233);
        BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter os = new PrintWriter(socket.getOutputStream());
        BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String readline;
        readline = sin.readLine();
        while (!readline.equalsIgnoreCase("bye")){
            os.println(readline);
            os.flush();
            System.out.println("client: " + readline);
            System.out.println("server: " + is.readLine());
            readline = sin.readLine();
        }
        os.close();
        is.close();
        socket.close();
    }

}

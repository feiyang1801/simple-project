
package cn.gy.nio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author yang.gao created on 2016/10/24 16:58
 * @version $Id$
 */
public class SenderV2 {

    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("127.0.0.1",8081);
        BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter os = new PrintWriter(socket.getOutputStream());
        String readline;
        readline = sin.readLine();
        while (!readline.equalsIgnoreCase("bye")){
            os.print(readline);
            os.flush();
            System.out.println("client: " + readline);
            readline = sin.readLine();
        }
        os.close();
        socket.close();
    }

}

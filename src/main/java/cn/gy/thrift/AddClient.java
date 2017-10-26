
package cn.gy.thrift;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 * thrift 简单客户端
 * @author yang.gao created on 2016/12/13 16:06
 * @version $Id$
 */
public class AddClient {

    public static void main(String[] args) throws Exception{
        TTransport transport = new TSocket("localhost",9090);
        TProtocol protocol = new TBinaryProtocol(transport);
        transport.open();
        AddService.Client client = new AddService.Client(protocol);
        System.out.println(client.add(12,11));
        transport.close();
    }

}

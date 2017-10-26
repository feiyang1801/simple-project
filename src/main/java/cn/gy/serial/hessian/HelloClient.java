
package cn.gy.serial.hessian;

import com.caucho.hessian.client.HessianProxyFactory;

/**
 * @author yang.gao created on 2017/1/3 15:36
 * @version $Id$
 */
public class HelloClient {

    public static void main(String[] args) throws Exception{
        String url = "http://localhost:8080/hello";
        HessianProxyFactory factory = new HessianProxyFactory();
        Hello hello = (Hello)factory.create(Hello.class,url);
        System.out.println(hello.hello("world"));
    }

}

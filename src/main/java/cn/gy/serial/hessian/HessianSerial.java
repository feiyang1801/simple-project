
package cn.gy.serial.hessian;

import cn.gy.model.CutOrderSource;
import cn.gy.model.UserOrder;
import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;

import java.io.*;

/**
 * @author yang.gao created on 2017/1/3 16:11
 * @version $Id$
 */
public class HessianSerial {

    public static void writeFile() throws Exception{
        UserOrder userOrder = new UserOrder("18600183021",1234l);
        Hessian2Output output = new Hessian2Output(new FileOutputStream("hessian.bin"));
        output.writeObject(userOrder);
        output.close();
    }

    public static void readFile() throws Exception{
        Hessian2Input input = new Hessian2Input(new FileInputStream("hessian.bin"));
        Object object = input.readObject();
        System.out.println(object);
    }

    public static void main(String[] args) throws Exception{
        System.out.println(CutOrderSource.NUMBER.name());
    }

}

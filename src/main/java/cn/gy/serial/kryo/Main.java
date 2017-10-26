package cn.gy.serial.kryo;

import cn.gy.model.UserOrder;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.CompatibleFieldSerializer;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * kryo test
 * Created by yang.gao on 2017/4/27.
 */
public class Main {

    public static void writeObject() throws Exception{
        Kryo kryo = new Kryo();
        Output output = new Output(new FileOutputStream("file.bin"));
        UserOrder userOrder = new UserOrder("18600183021",1234455l);
        kryo.writeObject(output,userOrder);
        output.close();

        Input input = new Input(new FileInputStream("file.bin"));
        UserOrder inUser = kryo.readObject(input,UserOrder.class);
        input.close();
        System.out.println(inUser);
    }

    public static void writeWithClass() throws Exception{
        Kryo kryo = new Kryo();
        kryo.setDefaultSerializer(CompatibleFieldSerializer.class);
        Output output = new Output(new FileOutputStream("file.bin"));
        UserOrder userOrder = new UserOrder("18600183021",1234455l);
        kryo.writeClassAndObject(output,userOrder);
        output.close();
    }

    public static void main(String[] args) throws Exception{
        Kryo kryo = new Kryo();
        kryo.setDefaultSerializer(CompatibleFieldSerializer.class);
        Input input = new Input(new FileInputStream("file.bin"));
        Object inUser = kryo.readClassAndObject(input);
        input.close();
        System.out.println(inUser);
    }

}

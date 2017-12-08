package cn.gy.serial;

import cn.gy.serial.model.Person;

import java.io.*;

/**
 * Created by yang.gao on 2017/4/27.
 */
public class App {

    public static void main(String[] args) {
        String fileName = "/Users/gaoyang/tmp/file.bin";
        Person person = new Person(12, "yang", "volatile");
        writeObject(fileName,person);
        readObject(fileName);
    }

    private static void writeObject(String fileName, Object object) {
        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(fileName));
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream);
            objectOutputStream.writeInt(12);
            objectOutputStream.writeUTF("hello");
            objectOutputStream.writeObject(object);
            objectOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Object readObject(String fileName) {
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(fileName));
            ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream);
            int age = objectInputStream.readInt();
            System.out.println(age);
            String name = objectInputStream.readUTF();
            System.out.println(name);
            Object object = objectInputStream.readObject();
            System.out.println(object);
            return object;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

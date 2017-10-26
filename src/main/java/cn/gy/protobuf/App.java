
package cn.gy.protobuf;

import cn.gy.protobuf.gen.PersonOuterClass;

/**
 * protobuf test
 * @author yang.gao created on 2016/12/14 18:16
 * @version $Id$
 */
public class App {

    public static void main(String[] args) throws Exception{
        PersonOuterClass.Person person = PersonOuterClass.Person.newBuilder().setId(1)
                .setEmail("feiyang@125.com").setName("skayng").build();
        byte[] bytes = person.toByteArray();
        System.out.println(bytes);
        PersonOuterClass.Person dPerson = PersonOuterClass.Person.parseFrom(bytes);
        System.out.println(dPerson);
    }

}

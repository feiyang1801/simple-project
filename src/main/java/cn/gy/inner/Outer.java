package cn.gy.inner;

/**
 * Created by yang.gao on 2016/2/16.
 */
public class Outer {

    public static void main(String[] args) {
        Outer outer = new Outer();
//        Outer.Inner inner = outer.new Inner();
//        Inner inner = new Inner();

    }

    private String name;

    class Inner{
        public void print(){
            System.out.println(name);
        }
    }

}





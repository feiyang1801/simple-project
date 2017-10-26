package cn.gy.inner;

/**
 * Created by yang.gao on 2016/2/16.
 */
public class App {

    public static void main(String[] args) {
       App app = new App();
        app.test("shanghai");
    }

    private String name;

    public void test(final String location){
        name = "abc";
        Bird bird = new Bird() {
            @Override
            public void fly() {
                System.out.println(name + location + " fly");
            }
        };
        bird.fly();
        name = "ef";
        bird.fly();
    }

}

package cn.gy.inner;

/**
 * Created by yang.gao on 2016/2/16.
 */
public abstract class Bird {

    private String name;

    public Bird() {
    }

    public Bird(String name) {
        this.name = name;
    }

    public abstract void fly();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

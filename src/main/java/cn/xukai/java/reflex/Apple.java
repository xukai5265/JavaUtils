package cn.xukai.java.reflex;

/**
 * Created by xukai on 2017/1/20.
 */
public class Apple implements Fruit {
    private int id;
    private String name;


    public Apple() {

    }

    public Apple(int id,String name) {
        this.id = id;
        this.name = name;
    }


    public void read(String name){
        System.out.println(name);
    }

    @Override
    public void eat() {
        System.out.println(name+":爱吃"+id+"个苹果");
    }
}

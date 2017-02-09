package cn.xukai.java.reflex;

/**
 * Created by xukai on 2017/1/20.
 */
public class ReflexDemo {
    public static void main(String[] args) throws Exception{
        //使用Class类内部定义的一个static方法，主要使用
        Class<?> clazz = Class.forName("cn.xukai.java.reflex.Person");
        System.out.println(clazz.getName());

        //对象实例化方法
        Person person = (Person) clazz.newInstance();
        person.setId(1);
        person.setName("xukai");
        person.setAge(28);
        System.out.println(person.toString());

        /*
            反射方式实例化对象的作用：
               对于程序的开发模式之前一直强调：
               尽量减少耦合，而减少耦合的最好做法是使用接口，但是就算使用了接口也逃不出关键字new，所以实际上new是造成耦合的关键元凶。
         */

        //范例：回顾一下之前所编写的工厂设计模式
        Fruit f = Factory.getInstance("cn.xukai.java.reflex.Apple");
        f.eat();
    }
}

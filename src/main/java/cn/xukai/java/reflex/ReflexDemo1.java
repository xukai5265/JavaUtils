package cn.xukai.java.reflex;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by kaixu on 2017/12/21.
 */
public class ReflexDemo1 {
    public static void main(String[] args) throws Exception {
        // 获取Apple 中的eat() 方法
        Class clazz = Apple.class;
        Method eat = clazz.getMethod("eat");
        System.out.println(eat);
        Constructor[] constructors = clazz.getConstructors();
        Constructor constructor = constructors[1];
        Apple apple = (Apple) constructor.newInstance(1,"xk");
        eat.invoke(apple);

        Method read = clazz.getMethod("read", String.class);
        read.invoke(apple,"xk");
    }

    /**
     * 反射中获取Class 方式一
     */
    private static Class getClasss(int i) throws Exception {
        // 方法一
        switch (i){
            case 1:return Class.forName("cn.xukai.java.reflex.Apple");
            case 2:return Apple.class;
            case 3:Apple apple3 = new Apple();
                return apple3.getClass();
        }
        return null;
    }

    /**
     * 创建实例 方法一：调用class.newInstance()
     *
     */
    private static Object newInstance(Class clazz) throws Exception {
        return clazz.newInstance();
    }


    private static Object newInstance(Class clazz,Object obj) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor constructor = Apple.class.getConstructor(Apple.class);
        return constructor.newInstance();
    }

    private static Field[] getFileds(Class clazz){
        return clazz.getFields();
    }

}

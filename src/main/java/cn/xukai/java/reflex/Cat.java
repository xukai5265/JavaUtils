package cn.xukai.java.reflex;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * Created by kaixu on 2017/12/21.
 */
public class Cat {
    private List<String> list;
    private Map<String, Object> map;

    public static void main(String args[]) throws Exception {
        System.out.println(">>>>>>>>>>>testList>>>>>>>>>>>");
        testList();
//        System.out.println("<<<<<<<<<<<testList<<<<<<<<<<<\n");
//        System.out.println(">>>>>>>>>>>testMap>>>>>>>>>>>");
//        testMap();
//        System.out.println("<<<<<<<<<<<testMap<<<<<<<<<<<\n");
//        System.out.println(">>>>>>>>>>>testClassA>>>>>>>>>>>");
//        new Test().testClassA();
//        System.out.println("<<<<<<<<<<<testClassA<<<<<<<<<<<");
        testField1();
    }

    /***
     * 获取List中的泛型
     * getDeclaredField 获取一个类的所有字段
     * getField() 只能获取一个类的公有字段
     *
     * getGenericType() 返回一个Type对象，它表示此Field对象所表示字段的声明类型，
     * 如果Type是一个参数化类型，则返回的Type对象必须准确地反映代码中使用的实际类型参数。
     * getType() 返回一个Class 对象，它标识了此Field 对象所表示字段的声明类型。
     */
    public static void testList() throws NoSuchFieldException, SecurityException {
        Type t = Cat.class.getDeclaredField("list").getGenericType();
        if (ParameterizedType.class.isAssignableFrom(t.getClass())) {
            for (Type t1 : ((ParameterizedType) t).getActualTypeArguments()) {
                System.out.print(t1 + ",");
            }
            System.out.println();
        }
    }

    public static void testField1() throws NoSuchFieldException {
        Type t = Cat.class.getDeclaredField("list").getGenericType();
        for(Type type :((ParameterizedType)t).getActualTypeArguments()){
            System.out.println(type);
        }
    }

    public static void testField() throws NoSuchFieldException {
        Class clazz = Cat.class.getDeclaredField("list").getType();
        System.out.println(clazz);
    }
}

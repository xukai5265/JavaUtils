package cn.xukai.java.reflex;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by kaixu on 2017/12/21.
 */
public class Animal<T> {
    private T obj;

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    /**
     * 获取T的类型
     */
    public void testClass(){
        System.out.println(this.getClass().getSuperclass().getName());
        Type t = this.getClass().getGenericSuperclass();
        System.out.println(t);
        if (ParameterizedType.class.isAssignableFrom(t.getClass())) {
            System.out.print("getActualTypeArguments:");
            for (Type t1 : ((ParameterizedType) t).getActualTypeArguments()) {
                System.out.print(t1 + ",");
            }
            System.out.println();
        }
    }

}

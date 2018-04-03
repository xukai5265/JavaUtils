package cn.xukai.java.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * 跳出双层for循环
 * Created by kaixu on 2018/4/3.
 */
public class Demo4 {
    public static void main(String[] args) {
        boolean flag = false;
        List<Integer> a = new ArrayList<>();
        List<List<Integer>> b = new ArrayList<>();
        a.add(3);
        b.add(a);
        a = new ArrayList<>();
        a.add(4);
        b.add(a);
        a = new ArrayList<>();
        a.add(4);
        b.add(a);
        a = new ArrayList<>();
        a.add(5);
        b.add(a);


//        方法一：使用return 跳出双层for循环
//        for(List<Integer> c:b){
//            for(Integer d:c){
//                if(d==4){
//                    System.out.println("break!");
//                    return;
//                }
//            }
//        }

          //方法二：使用判断标志位
//        for(List<Integer> c:b){
//            for(Integer d:c){
//                if(d==4){
//                    System.out.println("break!");
//                    flag=true;
//                    break;
//                }
//            }
//            if (flag==true){
//                break;
//            }
//        }

        // 方法三：为外层循环设置标签，名字是你自定义
        xk:for(List<Integer> c:b){
            for(Integer d:c){
                if(d==4){
                    System.out.println("break!");
                    break xk;
                }
            }
        }


    }
}

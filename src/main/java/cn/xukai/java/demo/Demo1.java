package cn.xukai.java.demo;

/**
 * Created by kaixu on 2018/2/10.
 */
public class Demo1 {
    private static double lx(double baseMoney,int num,double nlv){
        return baseMoney * nlv * num;
    }

    private static double compute(double baseMoney,int num,double nlv,int nian){
        double lx = lx(baseMoney,num,nlv);
        int yue = 0;
        if(nian<=1){
            yue = nian*12;
            double bj = yue * baseMoney;
            return bj+ lx *yue;
        }else if(nian == 2){
            yue = nian * 12;
            double bj = yue * baseMoney;
            return bj+ lx *yue;
        }else if(nian == 4){
            yue = nian * 12;
            double bj = yue * baseMoney + lx(baseMoney,2,.12);
        }
        return 0.0;
    }

    public static void main(String[] args) {
        System.out.println(compute(5000, 2, .12, 2));
    }
}

package cn.xukai.java.MR;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.Tool;

/**
 * Created by kaixu on 2017/7/27.
 * 两份数据，一份比较小，全部加载到内存，按关键字建立索引。
 * 大数据文件作为map的输入文件，对map()函数每一对输入，都能够方便地和已加载到内存的小数据进行连接。
 * 把连接结果按key输出，经过shuffle阶段，reduce端得到的就是已经按key分组的，
 * 并且连接好了的数据。
 *
 * 这种方法，要使用hadoop中的DistributedCache把小数据分布到各个计算节点，
 * 每个map节点都要把小数据库加载到内存，按关键字建立索引。
 *
 * 这种方法有明显的局限性：有一份数据比较小，在map端，能够把它加载到内存，并进行join操作。
 *
 *
 * 实现：
 * http://www.cnblogs.com/ivanny/p/mapreduce_join.html
 * 有客户数据customer和订单数据orders。
 * customer
 * 客户编号	姓名	    地址	    电话
     1	    hanmeimei	ShangHai	110
     2	    leilei	    BeiJing	    112
     3	    lucy	    GuangZhou	119
 ** order**

     订单编号	客户编号	其它字段被忽略
     1	        1	        50
     2	        1	        200
     3	        3	        15
     4	        3	        350
     5	        3	        58
     6	        1	        42
     7	        1	        352
     8	        2	        1135
     9	        2	        400
     10	        2	        2000
     11	        2	        300

 要求对customer和orders按照客户编号进行连接，结果要求对客户编号分组，对订单编号排序，对其它字段不作要求
 客户编号	订单编号	订单金额	姓名	地址	电话
 1	2	200	hanmeimei	ShangHai	110
 1	6	42	hanmeimei	ShangHai	110
 1	7	352	hanmeimei	ShangHai	110
 2	8	1135	leilei	BeiJing	112
 2	9	400	leilei	BeiJing	112
 2	10	2000	leilei	BeiJing	112
 2	11	300	leilei	BeiJing	112
 3	3	15	lucy	GuangZhou	119
 3	4	350	lucy	GuangZhou	119
 3	5	58	lucy	GuangZhou	119
 */
public class MapJoinTest extends Configured implements Tool {
    @Override
    public int run(String[] strings) throws Exception {
        return 0;
    }
}

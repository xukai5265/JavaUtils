package cn.xukai.java.utils.hive;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kaixu on 2017/5/27.
 */
public class GenHiveTable {
    public final static String EXTERNAL_MODEL="CREATE EXTERNAL TABLE IF NOT EXISTS ";
    public final static String INNER_MODEL="CREATE TABLE IF NOT EXISTS ";
    public final static String JK = "/ods/srdb/jk/";
    public final static String model = ")ROW FORMAT DELIMITED FIELDS TERMINATED BY '\\001' LOCATION ";

    public static String columnFormat(String line,int hiveType){
        if(line.contains("create table ")){
            String[] words = line.split(" ");
            //是否以t_开头
            String table = words[2].toLowerCase();
            if(!table.startsWith("t_")){
                table= "t_"+table;
            }
            if(hiveType==1){
                line = INNER_MODEL + table;
            }else
                line = EXTERNAL_MODEL + table;

        }else if(line.contains("character varying")|| line.contains("timestamp")||line.contains("date")||line.contains("bytea")
                ||line.contains("character")||line.contains("varchar2")||line.contains("text")){
            //step 1 character varying 替换成 string
            String [] words = line.split(" ");
            words = distinctArray(words);
            line = words[1] +" string,";
        }else if(line.contains("integer")){
            //step 1 character varying 替换成 string
            String [] words = line.split(" ");
            words = distinctArray(words);
            line = words[1] +" int,";
        }else if(line.contains("bigint")){
            //step 1 character varying 替换成 string
            String [] words = line.split(" ");
            words = distinctArray(words);
            line = words[1] +" bigint,";
        }else if(line.contains("double precision")){
            //step 1 character varying 替换成 string
            String [] words = line.split(" ");
            words = distinctArray(words);
            line = words[1] +" double,";
        }else if(line.contains("boolean")){
            //step 1 character varying 替换成 string
            String [] words = line.split(" ");
            words = distinctArray(words);
            line = words[1] +" boolean,";
        }else if(line.contains("smallint")){
            //step 1 character varying 替换成 string
            String [] words = line.split(" ");
            words = distinctArray(words);
            line = words[1] +" int,";
        }else if(line.contains("numeric")){
            //step 1 character varying 替换成 string
            String [] words = line.split(" ");
            words = distinctArray(words);
            //numeric(15,5),
            line = words[1]+" "+words[2].replace("numeric","decimal");
            if(!line.endsWith(",")){
                line= line+",";
            }
        }else if(line.contains("number")) {
            //step 1 character varying 替换成 string
            String[] words = line.split(" ");
            words = distinctArray(words);
            //numeric(15,5),
            line = words[1] + " " + words[2].replace("number", "decimal");
            if (!line.endsWith(",")) {
                line = line + ",";
            }
        }
        return line;
    }


    /**
     * 生成hive 表
     * @param filename 数据源 power designer data model
     * @param hiveType hive 表类型 内部表 ->1  ,外部表 -> 2
     * @param desPath 生成目标文件路径
     * @return
     * @throws IOException
     */
    public static void genTable(String filename,int hiveType,String desPath) throws IOException {
        File fileCopy = new File(desPath);
        //输入流
        FileInputStream in = null;
        InputStreamReader inReader = null;
        BufferedReader bufReader = null;

        //输出流
        OutputStream out = null;

        try{
            // 目标文件创建输出流
            out = new FileOutputStream(fileCopy, true);
            // 目标文件不存在就创建
            if (!(fileCopy.exists())) {
                fileCopy.createNewFile();
            }
            //文件输入流
            in = new FileInputStream(filename);
            inReader = new InputStreamReader(in, "UTF-8");
            bufReader =  new BufferedReader(inReader);

            // 源文件读取一行内容
            String line="";

            // 临时数据表Map
            Map<Integer,String> tableMap = new HashMap<>();

            //map key 标记位
            int i =0;

            while ((line=bufReader.readLine())!=null) {
                //过滤index
                if(line.contains("drop index")){
                    continue;
                }
                //过滤 符号
                if(line.startsWith("/*")){
                    continue;
                }

                //删除语句格式化
                if(line.contains("drop table if exists")){
                    String [] tmp = line.split(" ");
                    String table = tmp[4].toLowerCase();
                    if(!table.startsWith("t_")){
                        table="t_"+table;
                    }
                    line = tmp[0]+" "+tmp[1]+" "+tmp[2]+" "+tmp[3]+" "+table;
                }
                line = columnFormat(line,hiveType);
                i++;
                //将数据放入到map中
                tableMap.put(i,line);

                if(hiveType==1){ //内

                }else{ // 外
                    //处理hive 外部表最后一行的信息。包括指定表在hdfs上的路径。
                    if(line.startsWith(")")) {
                        int lastColumnSize = tableMap.size() - 1;
                        String lastColumn = tableMap.get(lastColumnSize);
                        int index = lastColumn.lastIndexOf(",");
                        if (index != -1)
                            lastColumn = lastColumn.substring(0, index);
                        tableMap.put(lastColumnSize, lastColumn);
                        int lastSize = tableMap.size();
                        //判断表名对应的hdfs目录位置
                        for(Map.Entry<Integer,String> entry : tableMap.entrySet()) {
                            //判断表名对应的hdfs目录位置
                            String value = entry.getValue();
                            if(value.contains(EXTERNAL_MODEL)){
                                String [] tmp = value.split(" ");
                                String table = tmp[6];
                                String res = model+"'"+JK+table+"';";
                                tableMap.put(lastSize,res);
                            }
                        }
                        //过滤掉create index 语句
                        if(!(tableMap.get(2).contains("create index")||(tableMap.get(2).contains("create unique index")))){
                            //将 一个建表语句统一写入文件
                            for(Map.Entry<Integer,String> entry : tableMap.entrySet()){
                                // 目标文件中添加一行数据
                                out.write(entry.getValue().getBytes());
                                out.write("\r\n".getBytes());
                            }
                        }
                        //key 还原
                        i=0;
                        //kap 清空
                        tableMap.clear();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("读取" + filename + "出错！");
        }finally {
            bufReader.close();
            inReader.close();
            in.close();
        }
    }

    /**
     * 数组去重
     * @param array
     * @return
     */
    public static String[] distinctArray (String [] array){
        List<String> list = new ArrayList<>();
        list.add(array[0]);
        for(int i=1;i<array.length;i++){
            if(list.toString().indexOf(array[i]) == -1){
                list.add(array[i]);
            }
        }
        return (String[]) list.toArray(new String[list.size()]);
    }

    public static void main(String[] args) throws IOException {
        String srcFile = "D:\\工作\\2017年05月\\19日\\src_data.txt";
        genTable(srcFile,2,"D:\\工作\\2017年05月\\19日\\desc_hive.hql");
    }


}

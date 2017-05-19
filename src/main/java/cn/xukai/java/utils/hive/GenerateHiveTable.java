package cn.xukai.java.utils.hive;

import java.io.*;
import java.util.*;

/**
 * Created by kaixu on 2017/5/19.
 */
public class GenerateHiveTable {
    public static void main(String[] args) throws IOException {
        File file2 = new File("D:\\工作\\2017年05月\\19日\\desc_data.txt");
        String srcFile = "D:\\工作\\2017年05月\\19日\\src_data.txt";
        readLineFile(srcFile,file2);
    }

    public static void readLineFile(String filename,File fileCopy) throws IOException {
        //hdfs 文件目录
        String cj = "/ods/srdb/cj/";
        String gl = "/ods/srdb/gl/";
        String jk = "/ods/srdb/jk/";
        String kyj = "/ods/srdb/kyj/";
        //外部表模板
        String model = ")ROW FORMAT DELIMITED FIELDS TERMINATED BY '\\001' LOCATION ";

        //输入流
        FileInputStream in = null;
        InputStreamReader inReader = null;
        BufferedReader bufReader = null;

        //输出流
        OutputStream out = null;

        // 临时数据表Map
        Map<Integer,String> tableMap = new HashMap<>();

        try {
            // 目标文件不存在就创建
            if (!(fileCopy.exists())) {
                fileCopy.createNewFile();
            }
            //文件输入流
            in = new FileInputStream(filename);
            inReader = new InputStreamReader(in, "UTF-8");
            bufReader =  new BufferedReader(inReader);

            // 目标文件创建输出流
            out = new FileOutputStream(fileCopy, true);

            // 源文件读取一行内容
            String line="";

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

                line = generateTable(line);
                i++;
                //将数据放入到map中
                tableMap.put(i,line);
                //处理hive 外部表最后一行的信息。包括指定表在hdfs上的路径。
                if(line.startsWith(")")){
                    int lastColumnSize = tableMap.size()-1;
                    String lastColumn = tableMap.get(lastColumnSize).replace(",","");
                    tableMap.put(lastColumnSize,lastColumn);

                    int lastSize = tableMap.size();
                    //判断表名对应的hdfs目录位置
                    for(Map.Entry<Integer,String> entry : tableMap.entrySet()){
                        //判断表名对应的hdfs目录位置
                        String value = entry.getValue();
                        if(value.contains("CREATE EXTERNAL TABLE IF NOT EXISTS")){
                            String [] tmp = value.split(" ");
                            String table = tmp[6];
                            if(table.startsWith("t_cj")){
                                String res = model+"'"+cj+table+"';";
                                tableMap.put(lastSize,res);
                                break;
                            }else if(table.startsWith("t_gl")){
                                String res = model+"'"+gl+table+"';";
                                tableMap.put(lastSize,res);
                                break;
                            }else if(table.startsWith("t_jk")){
                                String res = model+"'"+jk+table+"';";
                                tableMap.put(lastSize,res);
                                break;
                            }else if(table.startsWith("t_kyj")){
                                String res = model+"'"+kyj+table+"';";
                                tableMap.put(lastSize,res);
                                break;
                            }
                        }
                    }

                    //将map 写入文件，且将最后一个字段的逗号去掉。
                    for(Map.Entry<Integer,String> entry : tableMap.entrySet()){
                        // 目标文件中添加一行数据
                        out.write(entry.getValue().getBytes());
                        out.write("\r\n".getBytes());
                    }
                    //key 还原
                    i=0;
                    //kap 清空
                    tableMap.clear();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("读取" + filename + "出错！");
        }finally {
            bufReader.close();
            inReader.close();
            in.close();
        }
    }

    public static String generateTable(String line){
        String model1 = "CREATE EXTERNAL TABLE IF NOT EXISTS ";
        //create table JK_OLD_CONTRACT_INFO
        if(line.contains("create table ")){
            String[] words = line.split(" ");
            //是否以t_开头
            String table = words[2].toLowerCase();
            if(!table.startsWith("t_")){
                table= "t_"+table;
            }
            line = model1 + table;
        }
        //character varying , timestamp, date  => string
        if(line.contains("character varying")|| line.contains("timestamp")||line.contains("date")){
            //step 1 character varying 替换成 string
            String [] words = line.split(" ");
            words = qc(words);
            line = words[1] +" string,";
        }

        //integer => int
        if(line.contains("integer")){
            //step 1 character varying 替换成 string
            String [] words = line.split(" ");
            words = qc(words);
            line = words[1] +" int,";
        }
        //numeric
        if(line.contains("numeric")){
            //step 1 character varying 替换成 string
            String [] words = line.split(" ");
            words = qc(words);
            //numeric(15,5),
            line = words[1]+" "+words[2].replace("numeric","decimal");
        }
        return line;
    }

    /**
     * 数组去重
     * @param array
     * @return
     */
    public static String[] qc (String [] array){
        List<String> list = new ArrayList<>();
        list.add(array[0]);
        for(int i=1;i<array.length;i++){
            if(list.toString().indexOf(array[i]) == -1){
                list.add(array[i]);
            }
        }
        return (String[]) list.toArray(new String[list.size()]);
    }
}

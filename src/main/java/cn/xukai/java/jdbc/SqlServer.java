package cn.xukai.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by kaixu on 2017/6/14.
 */
public class SqlServer {
    public static void main(String[] args) {
        String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String dbURL="jdbc:sqlserver://183.250.43.201:1433;DatabaseName=DB-TEST";
        String userName="test001";
        String userPwd="dthh6666";
        try {
            Class.forName(driverName);
            Connection dbConn = DriverManager.getConnection(dbURL,userName,userPwd);
            System.out.println("连接数据库成功");
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.print("连接失败");
        }
    }
}

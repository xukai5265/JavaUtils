package cn.xukai.java.zk.service.group;

import cn.xukai.java.zk.connect.ConnectionWatcher;
import org.apache.zookeeper.KeeperException;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by kaixu on 2017/12/18.
 */
public class DeleteGroup extends ConnectionWatcher {

    public void delete(String groupName){
        String path = "/"+ groupName;
        List<String> children;
        try {
            children = zk.getChildren(path,false);
            Iterator<String> iter = children.iterator();
            while (iter.hasNext()){
                // -1 表示可以绕过zNode版本检测，直接删除
                zk.delete(path+"/"+iter.next(),-1);
            }
            zk.delete(path,-1);
        } catch (KeeperException e) {
            System.out.println("Group "+groupName+" does not exist \n");
            System.exit(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        DeleteGroup deleteGroup = new DeleteGroup();
        deleteGroup.connect("localhost:2181");
        deleteGroup.delete("c");
        deleteGroup.close();
    }
}

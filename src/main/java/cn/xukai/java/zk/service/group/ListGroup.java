package cn.xukai.java.zk.service.group;

import cn.xukai.java.zk.connect.ConnectionWatcher;
import org.apache.zookeeper.KeeperException;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by kaixu on 2017/12/18.
 * 列出组成员
 */
public class ListGroup extends ConnectionWatcher {
    private static final String HOST1="localhost:2181";
    private static final String HOST2="192.168.56.90:2182,192.168.56.90:2183,192.168.56.90:2184";

    public void list(String groupNmae){
        String path = "/"+ groupNmae;
        try {
            List<String> children = zk.getChildren(path,false);
            if(children.isEmpty()){
                System.out.println("Group "+groupNmae+" does not exist \n");
                System.exit(1);
            }
            Iterator<String> it=children.iterator();
            while (it.hasNext()){
                String child = it.next();
                System.out.println(child);
            }
        } catch (KeeperException e) {
            System.out.println("Group "+groupNmae+" does not exist \n");
            System.exit(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        ListGroup listGroup = new ListGroup();
        listGroup.connect(HOST1);
        listGroup.list("c");
        listGroup.close();
    }
}

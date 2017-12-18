package cn.xukai.java.zk.service.group;

import cn.xukai.java.zk.connect.ConnectionWatcher;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;

import java.io.IOException;

/**
 * Created by kaixu on 2017/12/18.
 * 将成员加入到组中
 */
public class JoinGroup extends ConnectionWatcher {
    private static final String HOST1="localhost:2181";
    private static final String HOST2="192.168.56.90:2182,192.168.56.90:2183,192.168.56.90:2184";

    public void join(String groupName,String memberName) throws KeeperException, InterruptedException {
        String path = "/"+groupName+"/"+memberName;
        String createPath = zk.create(path,null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        System.out.println(createPath);
    }

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        JoinGroup joinGroup = new JoinGroup();
        joinGroup.connect(HOST1);
        joinGroup.join("c","bb");
        Thread.sleep(Long.MAX_VALUE);
    }
}

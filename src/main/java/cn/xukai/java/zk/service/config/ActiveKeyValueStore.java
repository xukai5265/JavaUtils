package cn.xukai.java.zk.service.config;

import cn.xukai.java.zk.connect.ConnectionWatcher;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.Stat;

import java.nio.charset.Charset;

/**
 * Created by kaixu on 2017/12/18.
 */
public class ActiveKeyValueStore extends ConnectionWatcher {
    private static final Charset CHARSET =Charset.forName("UTF-8");

    /**
     * 将关键字及其值写到zookeeper
     * @param path
     * @param value
     * @throws KeeperException
     * @throws InterruptedException
     */
    public void write(String path,String value) throws KeeperException, InterruptedException {
        Stat stat = zk.exists(path,false);
        if(stat==null){
            System.out.println("路径："+path+"不存在！");
            zk.create(path,value.getBytes(CHARSET), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }else {
            zk.setData(path,value.getBytes(CHARSET),-1);
        }
    }

    /**
     *
     * @param path
     * @param watcher
     * @return
     * @throws KeeperException
     * @throws InterruptedException
     */
    public String read(String path,Watcher watcher) throws KeeperException, InterruptedException {
        byte[] data = zk.getData(path,watcher,null);
        return new String(data,CHARSET);
    }
}

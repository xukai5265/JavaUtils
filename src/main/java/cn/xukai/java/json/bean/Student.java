package cn.xukai.java.json.bean;

import java.util.List;

/**
 * Created by kaixu on 2017/6/14.
 */
public class Student {
    String id;
    String name;
    List<Address> address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }
}

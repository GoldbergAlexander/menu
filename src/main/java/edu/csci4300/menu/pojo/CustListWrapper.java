package edu.csci4300.menu.pojo;

import java.util.List;

public class CustListWrapper {
    private List<Customer> list;

    public CustListWrapper() {
    }

    public CustListWrapper(List<Customer> list) {
        this.list = list;
    }

    public List<Customer> getList() {
        return list;
    }

    public void setList(List<Customer> list) {
        this.list = list;
    }
}

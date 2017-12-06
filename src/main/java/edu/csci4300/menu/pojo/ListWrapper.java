package edu.csci4300.menu.pojo;

import java.util.List;

public class ListWrapper {
    private List<Item> list;

    public ListWrapper() {
    }

    public ListWrapper(List<Item> list) {
        this.list = list;
    }

    public List<Item> getList() {
        return list;
    }

    public void setList(List<Item> list) {
        this.list = list;
    }
}

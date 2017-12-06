package edu.csci4300.menu.pojo;

import java.util.List;

public class ItemListWrapper {
    private List<Item> list;

    public ItemListWrapper() {
    }

    public ItemListWrapper(List<Item> list) {
        this.list = list;
    }

    public List<Item> getList() {
        return list;
    }

    public void setList(List<Item> list) {
        this.list = list;
    }
}

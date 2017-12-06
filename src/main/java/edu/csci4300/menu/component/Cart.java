package edu.csci4300.menu.component;

import edu.csci4300.menu.pojo.Item;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* Cart Component scoped to the session -- Imported in the controller to track users cart */
@Component
@SessionScope
public class Cart implements Serializable {
    private List<Item> itemList;

    public Cart() {
        itemList = new ArrayList<>();
    }

    public Cart(List<Item> itemList) {
        this.itemList = itemList;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public Cart setItemList(List<Item> itemList) {
        this.itemList = itemList;
        return this;
    }

    public Cart addItemToList(Item item) {
        itemList.add(item);
        return this;
    }

    public Cart removeItemFromList(Item item) {
        Iterator<Item> itemIterator = itemList.iterator();
        while (itemIterator.hasNext()) {
            Item item1 = itemIterator.next();
            if (item1.getId().equals(item.getId())) {
                itemIterator.remove();
                break;
            }
        }
        return this;
    }

    @Override
    public String toString() {
        String string = "";
        string += itemList.toString();
        return string;
    }


}

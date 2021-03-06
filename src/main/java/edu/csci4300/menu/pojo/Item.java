package edu.csci4300.menu.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.List;


@Entity
public class Item implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private float price;
    private String description;

    @ManyToMany(mappedBy = "items")
    private List<Purchase> purchases;


    public Item() {

    }

    public Item(Long id, String name, float price, String description, List<Purchase> purchases) {
        super();
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.purchases = purchases;
    }

    public Item setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Item setName(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return name;
    }

    public Item setPrice(float price) {
        this.price = price;
        return this;
    }

    public float getPrice() {
        return price;
    }

    public Item setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Item setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
        return this;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void updateItem(Item newItem) {
        this.name = newItem.getName();
        this.description = newItem.getDescription();
        this.price = newItem.getPrice();
    }


    @Override
    public String toString() {
        String string = "";
        string += "{";
        string += "\"id\":" + id + ", ";
        string += "\"name\":" + "\"" + name + "\", ";
        string += "\"price\":" + price + ", ";
        string += "\"description\":" + "\"" + description + "\"";
        string += "}";
        return string;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;

        Item item = (Item) o;
        return name != null ? !name.equals(item.name) : item.name != null;
    }
}

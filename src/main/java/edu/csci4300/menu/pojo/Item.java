package edu.csci4300.menu.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;


@Entity
public class Item implements Serializable{
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private float price;
    private String description;

    @ManyToMany(mappedBy = "items")
    private List<Purchase> purchases;


    public Item(){

    }

    public Item(Long id, String name, int price, String description, List<Purchase> purchases) {
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

    public Item setId(long id){
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Item setName(String name) {
        this.name = name;
        return this;
    }

    public float getPrice() {
        return price;
    }

    public Item setPrice(float price) {
        this.price = price;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Item setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public Item setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
        return this;
    }

    @Override
    public String toString(){
        String string = "";
        string += "{";
        string += "\"id\":" + id + ", ";
        string += "\"name\":" + "\"" + name + "\", ";
        string += "\"price\":" + price + ", ";
        string += "\"description\":" + "\"" + description +"\"";
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

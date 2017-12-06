package edu.csci4300.menu.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Purchase implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private Date date;


    @ManyToMany
    @JoinTable(
            name = "purchase_item",
            joinColumns = {@JoinColumn(name = "id")},
            inverseJoinColumns = {@JoinColumn(name = "item_id")}
    )
    private List<Item> items;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Purchase() {
        items = new ArrayList<>();
        date = new Date();
    }

    public Purchase(Long id, List<Item> items, Date date) {
        this.id = id;
        this.items = items;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public Purchase setId(Long id) {
        this.id = id;
        return this;
    }

    public List<Item> getItems() {
        return items;
    }

    public Purchase setItems(List<Item> items) {
        this.items = items;
        return this;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Purchase setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public Purchase setDate(Date date) {
        this.date = date;
        return this;
    }
}

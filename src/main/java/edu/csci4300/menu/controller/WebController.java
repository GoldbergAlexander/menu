package edu.csci4300.menu.controller;

import edu.csci4300.menu.component.Cart;
import edu.csci4300.menu.dao.CustomerRepository;
import edu.csci4300.menu.dao.ItemRepository;
import edu.csci4300.menu.dao.PurchaseRepository;
import edu.csci4300.menu.pojo.Customer;
import edu.csci4300.menu.pojo.Item;
import edu.csci4300.menu.pojo.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/* Web Controller for basic endpoints */

@Controller
public class WebController {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private Cart cart;

    @RequestMapping("/items")
    public String items(Model model) {
        List<Item> itemList = itemRepository.findAll();
        model.addAttribute("items", itemList);
        return "items";
    }

    @PostMapping("/cart")
    public String updateCart(Model model, @ModelAttribute("item") Item item, @RequestParam(required = false) String add_cart,
                             @RequestParam(required = false) String remove_cart) {
        Item foundItem = itemRepository.findOne(item.getId());
        if (foundItem != null) {
            if (add_cart != null) {
                cart.addItemToList(foundItem);
            } else if (remove_cart != null) {
                cart.removeItemFromList(foundItem);
            }
        }
        return cart(model);
    }

    @ModelAttribute("customer")
    public Customer loadEmptyCustObj() {
        return new Customer();
    }

    @GetMapping("/cart")
    public String cart(Model model) {
        List<Item> itemList = cart.getItemList();
        model.addAttribute("items", itemList);
        List<Customer> customerList = customerRepository.findAll();
        model.addAttribute("customers", customerList);
        return "cart";
    }

    @PostMapping("/save")
    public String saveCart(Model model, @ModelAttribute("customer") Customer customer, @RequestParam boolean existing) {
        /** Check if exsiting or new */
        if (existing) {
            //If the customer exists, use the ID to get the full customer object
            customer = customerRepository.findOne(customer.getId());
        } else {
            //Else clear out the customer id and save it to the repo (getting a new ID in the process)
            customer.setId(null);
            customerRepository.save(customer);
        }
        Purchase purchase = new Purchase();
        purchase.setCustomer(customer);
        purchase.setItems(cart.getItemList());
        purchaseRepository.save(purchase);
        cart.setItemList(new ArrayList<Item>());
        model.addAttribute("purchased", true);
        return "cart";
    }

    @GetMapping("/customers")
    public String getCustomers(Model model) {
        model.addAttribute("customers", customerRepository.findAll());
        return "customers";
    }

    @RequestMapping("/purchases")
    public String getPurchases(Model model, @ModelAttribute Customer customer) {
        model.addAttribute("customers", customerRepository.findAll());
        if (customer != null && customer.getId() != null) {
            customer = customerRepository.findOne(customer.getId());
            List<Purchase> purchases = customer.getPurchases();
            model.addAttribute("customer", customer);
            model.addAttribute("purchases", purchases);
        }
        return "purchases";
    }


    @RequestMapping("/")
    public String index(Model model) {
        return "index";
    }
}

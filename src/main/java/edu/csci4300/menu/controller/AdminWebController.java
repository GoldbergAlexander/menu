package edu.csci4300.menu.controller;

import edu.csci4300.menu.dao.CustomerRepository;
import edu.csci4300.menu.dao.ItemRepository;
import edu.csci4300.menu.pojo.Customer;
import edu.csci4300.menu.pojo.Item;
import edu.csci4300.menu.pojo.ListWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/* Web Controller for admin functions */

@Controller
@RequestMapping("/admin")
public class AdminWebController {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("")
    public String adminIndex(Model model) {
        List<Item> itemList = itemRepository.findAll();
        ListWrapper wrapper = new ListWrapper(itemList);

        model.addAttribute("wrapper", wrapper);
        model.addAttribute("newItem", new Item());
        return "admin/index";
    }

    @PostMapping("")
    public String adminUpdate(Model model, @ModelAttribute("wrapper") ListWrapper wrapper, @ModelAttribute("newItem") Item newItem, @RequestParam(value="edit_items", required=false) String edit_items, @RequestParam(value="delete_item", required=false) String delete_item, @RequestParam(value="new_item", required=false) String new_item) {
        if (new_item != null) {
            itemRepository.save(newItem);
        } else if (edit_items != null) {
            List<Item> newList = wrapper.getList();
            for (Item i : newList) {
                itemRepository.save(i);
            }
        } else if (delete_item != null) {
            itemRepository.delete(Long.parseLong(delete_item));
        }
        return adminIndex(model);
    }

    @GetMapping("/items")
    public String adminItems(Model model) {
        List<Item> itemList = itemRepository.findAll();
        model.addAttribute("items", itemList);
        model.addAttribute("newItem", new Item());
        return "admin/items";
    }

    @PostMapping("/items")
    public String adminItemUpdate(Model model, @ModelAttribute Item item, @RequestParam(required = false) String action) {
        System.out.println(item.toString());
        if (action != null && action.equalsIgnoreCase("remove")) {
            itemRepository.delete(item.getId());
        } else {
            itemRepository.save(item);
        }
        return adminItems(model);
    }

    @GetMapping("/customers")
    public String customers(Model model) {
        List<Customer> customerList = customerRepository.findAll();
        model.addAttribute("customers", customerList);
        return "customers";
    }
}

package edu.csci4300.menu.controller;

import edu.csci4300.menu.dao.CustomerRepository;
import edu.csci4300.menu.dao.ItemRepository;
import edu.csci4300.menu.pojo.CustListWrapper;
import edu.csci4300.menu.pojo.Customer;
import edu.csci4300.menu.pojo.Item;
import edu.csci4300.menu.pojo.ItemListWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

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
        ItemListWrapper wrapper = new ItemListWrapper(itemList);

        List<Customer> custList = customerRepository.findAll();
        CustListWrapper custWrapper = new CustListWrapper(custList);

        model.addAttribute("wrapper", wrapper);
        model.addAttribute("custWrapper", custWrapper);
        model.addAttribute("newItem", new Item());
        model.addAttribute("newCust", new Customer());
        return "admin/index";
    }

    @PostMapping("")
    public String adminUpdate(Model model, @ModelAttribute("wrapper") ItemListWrapper wrapper,
                              @ModelAttribute("custWrapper") CustListWrapper custWrapper,
                              @ModelAttribute("newItem") Item newItem, @ModelAttribute("newCust") Customer newCust,
                              @RequestParam(value = "edit_items", required = false) String edit_items,
                              @RequestParam(value = "delete_item", required = false) String delete_item,
                              @RequestParam(value = "new_item", required = false) String new_item,
                              @RequestParam(value = "edit_custs", required = false) String edit_custs,
                              @RequestParam(value = "delete_cust", required = false) String delete_cust,
                              @RequestParam(value = "new_cust", required = false) String new_cust) {
        RedirectView rv = new RedirectView("admin/index#customers", true);

        if (new_item != null) {
            itemRepository.save(newItem);
        } else if (edit_items != null) {
            List<Item> newList = wrapper.getList();
            for (Item i : newList) {
                itemRepository.save(i);
            }
        } else if (delete_item != null) {
            itemRepository.delete(Long.valueOf(delete_item));
        } else if (new_cust != null) {
            customerRepository.save(newCust);
            return "redirect:" + "/admin#customers";
        } else if (edit_custs != null) {
            List<Customer> newCustList = custWrapper.getList();
            for (Customer c : newCustList) {
                customerRepository.save(c);
            }
            return "redirect:" + "/admin#customers";
        } else if (delete_cust != null) {
            customerRepository.delete(Long.valueOf(delete_cust));
            return "redirect:" + "/admin#customers";
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

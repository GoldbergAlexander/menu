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
            try {
                itemRepository.delete(Long.valueOf(delete_item));
            } catch (org.springframework.dao.DataIntegrityViolationException ex) {
                return "redirect:" + "/admin?dberror=rmitem";
            }
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
            try {
                customerRepository.delete(Long.valueOf(delete_cust));
            } catch (org.springframework.dao.DataIntegrityViolationException ex) {
                return "redirect:" + "/admin?dberror=rmcust#customers";
            }
            return "redirect:" + "/admin#customers";
        }
        return adminIndex(model);
    }
}

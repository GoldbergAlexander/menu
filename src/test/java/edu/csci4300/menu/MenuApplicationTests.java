package edu.csci4300.menu;

import edu.csci4300.menu.dao.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MenuApplicationTests {

    @Autowired
    ItemRepository itemRepository;

    @Test
    public void contextLoads() {
    }

    @Test
    public void repoTest() {
        //Item item = new Item();
        //item.setDescription("description").setName("name").setPrice(15);
        //itemRepository.save(item);
        System.out.println(itemRepository.findAll().toString());

    }

}

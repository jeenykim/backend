package jeeny.backend.controller;

import jeeny.backend.entity.Item;
//import jeeny.backend.repository.ItemRepository;
import jeeny.backend.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ItemController {

    @Autowired
    ItemRepository itemRepository;

    @GetMapping("/api/items")
//    public List<String> getItems() {
//        List<String> items = new ArrayList<>();
//        items.add("1지니킴");
//        items.add("2지니킴");
//        items.add("3지니킴");

    public List<Item> getItems() {
        List<Item> items = itemRepository.findAll();
        return items;
    }
}

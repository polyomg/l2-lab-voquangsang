package com.poly.lab5.service;

import java.util.*;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import com.poly.lab5.model.Item;
import com.poly.lab5.controller.ShopController;

@SessionScope
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    Map<Integer, Item> map = new HashMap<>();

    @Override
    public Item add(Integer id) {
        Item template = ShopController.ITEMS.get(id);
        if (template == null) return null;
        if (map.containsKey(id)) {
            Item item = map.get(id);
            item.setQty(item.getQty() + 1);
            return item;
        } else {
            Item item = new Item(template.getId(), template.getName(), template.getPrice(), 1);
            map.put(id, item);
            return item;
        }
    }

    @Override
    public void remove(Integer id) { map.remove(id); }

    @Override
    public Item update(Integer id, int qty) {
        if (!map.containsKey(id)) return null;
        if (qty <= 0) { map.remove(id); return null; }
        Item item = map.get(id);
        item.setQty(qty);
        return item;
    }

    @Override
    public void clear() { map.clear(); }

    @Override
    public Collection<Item> getItems() { return map.values(); }

    @Override
    public int getCount() { return map.values().stream().mapToInt(Item::getQty).sum(); }

    @Override
    public double getAmount() { return map.values().stream().mapToDouble(i -> i.getPrice() * i.getQty()).sum(); }
}


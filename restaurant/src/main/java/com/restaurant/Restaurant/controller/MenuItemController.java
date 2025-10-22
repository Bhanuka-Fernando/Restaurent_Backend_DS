package com.restaurant.Restaurant.controller;

import com.restaurant.Restaurant.model.MenuItem;
import com.restaurant.Restaurant.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/menu")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class MenuItemController {

    @Autowired
    private MenuItemService menuItemService;

    @PostMapping("/add")
    public ResponseEntity<MenuItem> addMenuItem(@RequestBody MenuItem menuItem) {
        MenuItem created = menuItemService.createMenuItem(menuItem);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<List<MenuItem>> getMenuByRestaurant(@PathVariable String restaurantId) {
        List<MenuItem> items = menuItemService.getMenuItemsByRestaurant(restaurantId);
        return ResponseEntity.ok(items);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable String id) {
        menuItemService.deleteMenuItem(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<MenuItem> updateMenuItem(@PathVariable String id, @RequestBody MenuItem updatedItem) {
        MenuItem updated = menuItemService.updateMenuItem(id, updatedItem);
        return ResponseEntity.ok(updated);
    }


}

package com.restaurant.Restaurant.service;

import com.restaurant.Restaurant.model.MenuItem;
import com.restaurant.Restaurant.repo.MenuItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemService {

    @Autowired
    private MenuItemRepo menuItemRepo;

    public MenuItem createMenuItem(MenuItem menuItem) {
        return menuItemRepo.save(menuItem);
    }

    public List<MenuItem> getMenuItemsByRestaurant(String restaurantId) {
        return menuItemRepo.findByRestaurantId(restaurantId);
    }

    public void deleteMenuItem(String id) {
        menuItemRepo.deleteById(id);
    }

    public MenuItem updateMenuItem(String id, MenuItem updatedItem) {
        MenuItem existing = menuItemRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu item not found"));

        existing.setName(updatedItem.getName());
        existing.setDescription(updatedItem.getDescription());
        existing.setPrice(updatedItem.getPrice());
        existing.setImagePath(updatedItem.getImagePath());

        return menuItemRepo.save(existing);
    }



}

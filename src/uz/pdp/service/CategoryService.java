package uz.pdp.service;

import uz.pdp.model.Category;

import java.util.List;
import java.util.UUID;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class CategoryService {
    public CategoryService() {}

    private List<Category>  categories = List.of(
            new Category(UUID.randomUUID(),"Iphone 13 ",UUID.randomUUID()),
            new Category(UUID.randomUUID(),"Iphone 14 ",UUID.randomUUID()),
            new Category(UUID.randomUUID(),"Iphone 15 ",UUID.randomUUID()),
            new Category(UUID.randomUUID(),"Iphone 16 ",UUID.randomUUID())
    );
    private List<Category> categories2 = List.of(
            new Category(UUID.randomUUID(),"BMW",UUID.randomUUID()),
            new Category(UUID.randomUUID(),"Mercedes Benz",UUID.randomUUID()),
            new Category(UUID.randomUUID(),"BWD",UUID.randomUUID()),
            new Category(UUID.randomUUID(),"Rolls-Royce",UUID.randomUUID()),
            new Category(UUID.randomUUID(),"Bugatti",UUID.randomUUID())
    );
    public List<Category> getSubCategories(UUID parentId){
        return getAllCategories().stream()
                .filter(c-> c.getParentId() != null && c.getParentId().equals(parentId))
                .collect(Collectors.toList());
    }
    public List<Category> getAllCategories() {
        List<Category> allCategories = new ArrayList<>();
        allCategories.addAll(categories);
        allCategories.addAll(categories2);
        return allCategories;
    }
    public Category getCategoryByName(String name) {
        Category fromFirst = categories.stream()
                .filter(c -> c.getName().equals(name))
                .findFirst()
                .orElse(null);

        if (fromFirst == null) {
            return categories2.stream()
                    .filter(c -> c.getName().equals(name))
                    .findFirst()
                    .orElse(null);
        }
        return fromFirst;
    }
}
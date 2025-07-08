package uz.pdp.model;

import java.util.UUID;

public class Product {
    private UUID id;
    private String name;
    private boolean active;
    private UUID categoryId;

    public Product(UUID id, String name, boolean active, UUID categoryId) {
        this.id = id;
        this.name = name;
        this.active = active;
        this.categoryId = categoryId;
    }

    public Product() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public UUID getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(UUID categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", active=" + active +
                ", categoryId=" + categoryId +
                '}';
    }
}

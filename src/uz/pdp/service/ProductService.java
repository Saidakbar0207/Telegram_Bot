package uz.pdp.service;

import uz.pdp.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProductService {
    public ProductService() {
    }

    private List<Product> products = new ArrayList<>(List.of(
            new Product(UUID.randomUUID(),"Iphone",true,UUID.randomUUID())
    ));
    public List<Product> getAllProducts(){
        return products;
    }
    public Product getProductById(UUID id){
        return products.stream().filter(p->p.getId().equals(id)).findFirst().orElse(null);
    }
    public Product getProductByName(String name){
        return products.stream().filter(p->p.getName().equals(name)).findFirst().orElse(null);
    }
    public Product addProduct(Product product){
        product.setId(UUID.randomUUID());
        products.add(product);
        return product;
    }

    public List<Product> getProductsByCategory(UUID id) {
        return products.stream()
                .filter(p -> p.getCategoryId().equals(id))
                .toList();
    }
}

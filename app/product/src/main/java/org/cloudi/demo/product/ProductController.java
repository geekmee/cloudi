package org.cloudi.demo.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @GetMapping("/products")
    public Product greeting(@RequestParam(value = "id", defaultValue = "1") long id) {
        return new Product(id, "Fedora", "The Fedora Project is maintained and driven by the community and sponsored by Red Hat.");
    }
}

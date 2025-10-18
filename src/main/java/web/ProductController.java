package web;

import entities.Category;
import entities.Product;
import metier.CategoryDaoImpl;
import metier.ProductDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductDaoImpl productDao;

    @Autowired
    private CategoryDaoImpl categoryDao;

    // Afficher tous les produits
    @GetMapping
    public String listProducts(Model model) {
        List<Product> products = productDao.findAll();
        model.addAttribute("products", products);
        return "products"; // view name (products.jsp ou Thymeleaf)
    }

    // Afficher le formulaire d’ajout
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryDao.findAll());
        return "addProduct";
    }

    // Enregistrer un produit
    @PostMapping("/save")
    public String saveProduct(@ModelAttribute Product product) {
        productDao.create(product);
        return "redirect:/products";
    }
}

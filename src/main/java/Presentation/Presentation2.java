package presentation;

import dao.IDao;
import entities.Category;
import entities.Product;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import util.HibernateConfig;

public class Presentation2 {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);

        // Get the DAO beans
        IDao<Category> categoryDao = (IDao<Category>) context.getBean("categoryDaoImpl");
        IDao<Product> productDao = (IDao<Product>) context.getBean("productDaoImpl");

        // Create a new Category
        Category category = new Category("Electronics");
        categoryDao.create(category);
        System.out.println("Category saved: " + category.getName());

        // Create a new Product linked to the category
        Product product = new Product();
        product.setName("Smartphone");
        product.setPrice(1500.0);
        product.setCategory(category); // Link to category

        productDao.create(product);
        System.out.println("Product saved: " + product.getName() + " (Category: " + product.getCategory().getName() + ")");
    }
}

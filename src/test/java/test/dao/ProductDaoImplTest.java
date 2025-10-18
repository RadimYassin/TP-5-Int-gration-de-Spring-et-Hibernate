package test.dao;

import entities.Category;
import entities.Product;
import metier.CategoryDaoImpl;
import metier.ProductDaoImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = test.config.TestConfig.class)
@Transactional
public class ProductDaoImplTest {

    @Autowired
    private ProductDaoImpl productDao;

    @Autowired
    private CategoryDaoImpl categoryDao;

    @Test
    public void testCreateAndFindByCategory() {
        Category cat = new Category();
        cat.setName("Electronics");
        categoryDao.create(cat);

        Product p1 = new Product();
        p1.setName("Laptop");
        p1.setCategory(cat);
        productDao.create(p1);

        Product p2 = new Product();
        p2.setName("Phone");
        p2.setCategory(cat);
        productDao.create(p2);

        List<Product> products = productDao.findByCategoryId(cat.getId());
        assertEquals(2, products.size());
    }
}

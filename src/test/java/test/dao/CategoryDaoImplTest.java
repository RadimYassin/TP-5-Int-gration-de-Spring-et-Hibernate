package test.dao;

import metier.CategoryDaoImpl;
import entities.Category;
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
public class CategoryDaoImplTest {

    @Autowired
    private CategoryDaoImpl categoryDao;

    @Test
    public void testCreateAndFindById() {
        Category cat = new Category();
        cat.setName("Electronics");

        boolean created = categoryDao.create(cat);
        assertTrue(created);

        Category fetched = categoryDao.findById(cat.getId());
        assertNotNull(fetched);
        assertEquals("Electronics", fetched.getName());
    }

    @Test
    public void testFindAll() {
        Category cat1 = new Category();
        cat1.setName("Books");
        categoryDao.create(cat1);

        Category cat2 = new Category();
        cat2.setName("Toys");
        categoryDao.create(cat2);

        List<Category> categories = categoryDao.findAll();
        assertEquals(2, categories.size());
    }

    @Test
    public void testUpdate() {
        Category cat = new Category();
        cat.setName("Old Name");
        categoryDao.create(cat);

        cat.setName("New Name");
        boolean updated = categoryDao.update(cat);
        assertTrue(updated);

        Category fetched = categoryDao.findById(cat.getId());
        assertEquals("New Name", fetched.getName());
    }

    @Test
    public void testDelete() {
        Category cat = new Category();
        cat.setName("Delete Me");
        categoryDao.create(cat);

        boolean deleted = categoryDao.delete(cat);
        assertTrue(deleted);

        Category fetched = categoryDao.findById(cat.getId());
        assertNull(fetched);
    }
}

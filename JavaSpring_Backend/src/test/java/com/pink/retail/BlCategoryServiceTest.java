package com.pink.retail;

import com.pink.retail.category.BlCategoryService;
import com.pink.retail.category.Category;
import com.pink.retail.category.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.FieldSetter;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class BlCategoryServiceTest {

    private BlCategoryService service;
    private CategoryRepository categoryRepository;
    private Category category;

    @Before
    public void initialize() throws NoSuchFieldException {

        service = new BlCategoryService();
        categoryRepository = Mockito.mock(CategoryRepository.class);

        FieldSetter.setField(service, service.getClass().getDeclaredField("categoryRepository"),
                categoryRepository);

        List<Category> categoryList = new LinkedList<>();
        category = new Category(0,"categ", Collections.emptyList());
        categoryList.add(category);
        category = new Category(1,"categ2", Collections.emptyList());
        categoryList.add(category);
        category = new Category(100,"categ3", Collections.emptyList());
        categoryList.add(category);

        Mockito.when(service.getByCategoryId(0)).thenReturn(categoryList.get(0));
        Mockito.when(service.getByCategoryId(1)).thenReturn(categoryList.get(1));
        Mockito.when(service.getByCategoryId(100)).thenReturn(categoryList.get(2));

        Mockito.when(service.getByCategoryName("categ")).thenReturn(categoryList.get(0));
        Mockito.when(service.getByCategoryName("cate2")).thenReturn(categoryList.get(1));
        Mockito.when(service.getByCategoryName("categ3")).thenReturn(categoryList.get(2));

        Mockito.when(service.getAllCategory()).thenReturn(categoryList);
    }

    @Test
    public void getCategory(){
        //getAllCategory
        assertEquals(3, service.getAllCategory().size());

        //getByCategoryId
        assertEquals(category, service.getByCategoryId(100));
        Mockito.verify(categoryRepository).findByCategoryId(100);
        assertNull(service.getByCategoryId(-3322));
        Mockito.verify(categoryRepository).findByCategoryId(-3322);

        //getByCategoryName
        assertEquals(category, service.getByCategoryName("categ3"));
        assertNull(service.getByCategoryName(""));
    }

    @Test
    public void insertCateg(){
        service.insertCategory(category);
        Mockito.verify(categoryRepository).save(category);
    }

}

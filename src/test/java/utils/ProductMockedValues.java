package utils;

import com.spring.adapters.vo.request.ProductRequestVO;
import com.spring.domain.entity.Product;
import com.spring.domain.enums.ProductCategory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class ProductMockedValues {

    public static ProductRequestVO getProductRequestVO(String name){
        return new ProductRequestVO(name, ProductCategory.NOTEBOOK, new BigDecimal(20));
    }

    public static List<Product> getMockedProductsList(){
        Product product1 = new Product();
        product1.setValue(new BigDecimal(10));
        product1.setProductCategory(ProductCategory.NOTEBOOK);
        product1.setName("Notebook IntegrationTest");

        Product product2 = new Product();
        product2.setValue(new BigDecimal(100));
        product2.setProductCategory(ProductCategory.TV);
        product2.setName("TV IntegrationTest");

        return new ArrayList<>(Arrays.asList(product1, product2));
    }

    public static List<Product> getMockedProductsListWithId(){
        List<Product> products = getMockedProductsList();
        products.get(0).setId(UUID.randomUUID().toString());
        products.get(1).setId(UUID.randomUUID().toString());
        return  products;
    }

}

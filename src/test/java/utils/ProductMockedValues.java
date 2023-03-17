package utils;

import com.spring.adapters.vo.request.ProductRequestVO;
import com.spring.domain.enums.ProductCategory;

import java.math.BigDecimal;

public class ProductMockedValues {

    public static ProductRequestVO getProductRequestVO(String name){
        return new ProductRequestVO(name, ProductCategory.NOTEBOOK, new BigDecimal(20));
    }

}

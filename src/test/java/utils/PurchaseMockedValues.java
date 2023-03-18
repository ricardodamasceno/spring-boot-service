package utils;

import com.spring.adapters.vo.request.PurchaseRequestVO;
import com.spring.domain.entity.Product;
import com.spring.domain.entity.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PurchaseMockedValues {

    public static PurchaseRequestVO getPurchaseRequestVO() {
        List<Product> mockedProducts = ProductMockedValues.getMockedProductsListWithId();
        User mockedBuyer = UserMockedValues.getMockedUser();

        PurchaseRequestVO request = new PurchaseRequestVO();
        request.setBuyerId(mockedBuyer.getId());
        request.setProducts(
                new ArrayList<>(Arrays.asList(mockedProducts.get(0).getId(), mockedProducts.get(1).getId()))
        );

        return request;
    }

}

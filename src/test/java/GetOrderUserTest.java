import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;
import static org.hamcrest.CoreMatchers.is;

public class GetOrderUserTest extends BaseApi{
    OrderApi orderApi= new OrderApi();
    UserApi userApi=new UserApi();

    @Before
    public void setUp(){
        openUrl();
        userApi.registration(GeneratorUser.getCreateUser());
    }
    @Test
    @DisplayName("Получение списка заказов пользователя с авторизацией")
    public void getUserOrdersWithAuthorization(){
        orderApi.getOrder(userApi.getToken())
                .then()
                .statusCode(SC_OK)
                .body("success", is(true));
    }

    @Test
    @DisplayName("Получение списка заказов пользователя без авторизации")
    public void getUserOrdersWithNonAuthorization(){
        orderApi.getOrder("")
                .then()
                .statusCode(SC_UNAUTHORIZED)
                .body("success", is(false));
    }
    @After
    public void deleteUser(){
        userApi.deleteUser();
    }
















}

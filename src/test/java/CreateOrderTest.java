import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CreateOrderTest extends BaseApi{
    UserApi userApi= new UserApi();
    OrderApi orderApi=new OrderApi();
    AssertOrder assertOrder=new AssertOrder();


    @Before
    public void setUp(){
        openUrl();
        userApi.registration(GeneratorUser.getCreateUser());
        orderApi.getIngredients();
            }

    @Test
    @DisplayName("Создание заказа авторизованного пользователя")
    public void createOrderAuthorization(){
        Response status= orderApi.createOrder(userApi.getToken(), orderApi.getIngredientsValid());
        assertOrder.statusOk(status);
    }
    @Test
    @DisplayName("Создание заказа авторизованного пользователя без ингредиентов")
    public void createOrderWithAuthorization(){
        Response status=orderApi.createOrder(userApi.getToken(),orderApi.getNullIngredients());
        assertOrder.statusBadRequest(status);
    }

    @Test
    @DisplayName("Создание заказа авторизованного пользователя с невалидным хэшем")
    public void createOrderWithBadHashAndAuthorization(){
        Response status=orderApi.createOrder(userApi.getToken(),orderApi.getInvalIdIngredients());
        assertOrder.statusInternalServerError(status);
    }
    @Test
    @DisplayName("Создание заказа неавторизованного пользователя")
    public void createOrderNonAuthorization() {
        Response status = orderApi.createOrder("", orderApi.getIngredientsValid());
        assertOrder.statusOk(status);
    }
    @Test
    @DisplayName("Создание заказа неавторизованного пользователя без ингредиентов")
    public void createOrderWithNonAuthorization() {
        Response status = orderApi.createOrder("", orderApi.getNullIngredients());
        assertOrder.statusBadRequest(status);
    }
    @Test
    @DisplayName("Создание заказа неавторизованного пользователя с невалидным хэшем")
    public void createOrderWithBadHashAndNonAuthorization(){
        Response status = orderApi.createOrder("", orderApi.getInvalIdIngredients());
        assertOrder.statusInternalServerError(status);
    }

    @After
    public void deleteUser(){
        userApi.deleteUser();
    }






}

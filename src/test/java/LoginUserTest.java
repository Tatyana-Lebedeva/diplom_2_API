import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class LoginUserTest extends BaseApi{
    UserApi userApi=new UserApi();
    AssertUser assertUser= new AssertUser();


    @Before
    public void setUp(){
        openUrl();
        userApi.registration(GeneratorUser.getCreateUser());
    }

    @Test
    @DisplayName("Проверка возможности успешного логина")
    public void successLoginUser(){
        Response status = userApi.loginUser(GeneratorUser.getUserLogin());
        assertUser.statusOk(status);
    }
    @Test
    @DisplayName("Проверка возможности логина c неправильным Email")
    public void loginUserWithBadEmail(){

        Response status= userApi.loginUser(GeneratorUser.getUserBadEmail());
        assertUser.statusUnauthorized(status);
    }
    @Test
    @DisplayName("Проверка возможности логина c неправильным паролем")
    public void loginUserWithBadPassword(){
        Response status= userApi.loginUser(GeneratorUser.getUserBadPassword());
        assertUser.statusUnauthorized(status);
    }

    @After
    public void deleteUser(){
        userApi.deleteUser();
    }
}
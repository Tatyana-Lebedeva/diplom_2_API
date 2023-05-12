import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import jdk.jfr.Registered;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RenameUserTest extends BaseApi{
    UserApi userApi=new UserApi();
    AssertUser assertUser= new AssertUser();

    @Before
    public void setUp(){
        openUrl();
        userApi.registration(GeneratorUser.getCreateUser());}

    @Test
    @DisplayName("Проверка возможности изменения Email авторизованного пользователя")
    public void renameEmailWithAuthorization(){
        Response status = userApi.rename("email",GeneratorUser.getNewEmailFaker(), userApi.getToken());
        assertUser.statusOk(status);
    }

    @Test
    @DisplayName("Проверка возможности изменения имени авторизованного пользователя")
    public void renameNameWithAuthorization(){
        Response status = userApi.rename("name",GeneratorUser.getNewNameFaker(), userApi.getToken());
        assertUser.statusOk(status);
    }
    @Test
    @DisplayName("Проверка возможности изменения Email неавторизованного пользователя")
    public void renameEmailNonAuthorization(){
        Response status = userApi.rename("email",GeneratorUser.getNewEmailFaker(), "");
        assertUser.statusOk(status);
    }
    @Test
    @DisplayName("Проверка возможности изменения имени неавторизованного пользователя")
    public void renameNameNonAuthorization(){
        Response status = userApi.rename("name",GeneratorUser.getNewNameFaker(), userApi.getToken());
        assertUser.statusOk(status);
    }
    @After
    public void deleteUser(){
        userApi.deleteUser();
    }

}

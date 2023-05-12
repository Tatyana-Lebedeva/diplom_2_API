import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CreateUserTest extends BaseApi {
    UserApi userApi = new UserApi();
    AssertUser assertUser= new AssertUser();

    @Before
    public void setUp(){
        openUrl();
    }

    @Test
    @DisplayName("Проверка возможности создать нового пользователя")
    public void successfulRegistrationNewUser(){
        Response status=userApi.registration(GeneratorUser.getCreateUser());
        assertUser.statusOk(status);
    }

    @Test
    @DisplayName("Попытка создать пользователя с повторяющимся именем")
    public void registrationDuplicateUser(){
        userApi.registration(GeneratorUser.getCreateUser());
        Response status= userApi.registrationWhithoutExtractToken(GeneratorUser.getCreateUser());
        assertUser.statusForbidden (status);
    }
    @Test
    @DisplayName("Попытка создания пользователя без заполненного email")
    public void registrationUserWithoutEmail(){
        Response status= userApi.registrationWhithoutExtractToken(GeneratorUser.getCreateUserWithoutEmail());
        assertUser.statusForbidden(status);
    }
    @Test
    @DisplayName("Попытка создания пользователя без заполненного Password")
    public void registrationUserWithoutPassword(){
        Response status= userApi.registrationWhithoutExtractToken(GeneratorUser.getCreateUserWithoutPassword());
        assertUser.statusForbidden(status);
    }
    @Test
    @DisplayName("Попытка создания пользователя без заполненного имени пользователя")
    public void registrationUserWithoutUserName(){
        Response status= userApi.registrationWhithoutExtractToken(GeneratorUser.getCreateUserWithoutUserName());
        assertUser.statusForbidden(status);
    }

    @After
    public void deleteUser(){
        userApi.deleteUser();
            }











}

import io.restassured.RestAssured;

public class BaseApi {
    public void openUrl(){
        RestAssured.baseURI= "https://stellarburgers.nomoreparties.site/";
    };
}

import com.github.javafaker.Faker;

public class GeneratorUser {
    static Faker faker = new Faker();
    private static final String emailFaker = faker.internet().emailAddress();
    private static final String passwordFaker = faker.internet().password(8, 10);
    private static final String userNameFaker = faker.name().firstName();
    private static final String newEmailFaker = "FMldmfksd@mail.com";
    private static final String newNameFaker ="NNNN";
    private static final  String newpasword = "1111112";
    static CreateUser createUser = new CreateUser(userNameFaker, passwordFaker, emailFaker);
    static CreateUser createUserWithoutEmail = new CreateUser(userNameFaker, passwordFaker, "");
    static CreateUser createUserWithoutPassword = new CreateUser(userNameFaker, "", emailFaker);
    static CreateUser createUserWithoutUserName = new CreateUser("", passwordFaker, emailFaker);

    public static String getNewEmailFaker() {
        return emailFaker;
    }

    public static String getNewNameFaker() {
        return userNameFaker;
    }

    static User userLogin = new User(newEmailFaker, newpasword);

    static User userBadEmail = new User(emailFaker.substring(1), passwordFaker);
    static User userBadPassword = new User(emailFaker, passwordFaker.substring(1));

    public static CreateUser getCreateUser() {
        return createUser;
    }

    public static CreateUser getCreateUserWithoutEmail() {
        return createUserWithoutEmail;
    }

    public static CreateUser getCreateUserWithoutPassword() {
        return createUserWithoutPassword;
    }

    public static  CreateUser getCreateUserWithoutUserName() {
        return createUserWithoutUserName;
    }

    public static User getUserLogin() {
        return userLogin;
    }

    public static User getUserBadEmail() {
        return userBadEmail;
    }

    public static User getUserBadPassword() {
        return userBadPassword;
    }
}
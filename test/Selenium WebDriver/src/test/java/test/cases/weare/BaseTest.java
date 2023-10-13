package test.cases.weare;

import com.github.javafaker.Faker;
import com.telerikacademy.testframework.UserActions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import pages.weare.LoginPage;
import pages.weare.PostPage;
import pages.weare.RegistrationPage;

public class BaseTest {

    protected String username;
    protected String email;
    protected String password = "P@ssw0rd";
    protected String publicPostMessage = "This is my new post";
    protected String privatePostMessage = "This is my private post";
    protected String postMessageOneCharacters = "T";
    protected String postMessage999Characters = "fblxypsnsvcihvizunfphvxwhiytufqgeeyooaibxsdyeypocyrndsschsksqojmrrvtzneusnpbfxpbdqbqdvxsbputercjaxrynsczwtkqdvrnslhvisvqubzkexmzlicpicycvtwlragbgrmzffpabreobgegwvwqmryylifqvdlqnovtlywisptidfxmbpvriccrnzddextznhvobkqktzkaduoxsdnjujxvnnyjdzmwznbjcliyjmgenlwxobjbditmglqbumzgqeopqyeiqszvpmltlnepkisukrrtnqivrnhejqnltlbgtglzwwfcphdzqtrfmfffqeavuvpzvvhmhohcsipxsgfstugfnieuzecxfejknkzyokpynuzxaupxfvpbxdewfropuuvtziwuhvkuklzjvtkjvlfflrendzwjmeoddwulteahkvjjtbshhoddhmjwwvggjzkmclcbwcwfxufhhrmibqbtpzwgfisugokqefapnrjlkkhvegokhztyytomzsdvnosqmsoddfnlbdgxppubucjlqshcwifqafstrpzkaseqysyzubbyxaagvtaqnujoyuxrznqrgehvtxviukbchhtneyeizpkepnzsottmemiegkqwjxsuufvrfmpamqnymrirwjwmsohpaovvgnjkrgxprkwjndlplsppwiboeaifrojphtkrqjhizoyrdeiqjksdeymaeabajuiopwzgxjtbnpwgkzqywymjuuusuufvplrwtlscivhgotqtrccsxhpvslaudpqstvnbmxvklgunkkzgurxsfkxycwagmcqpjgowvtpikihwkydcmvfyofdzafvlyshovudyxouautkqmhperpxrbkrdsohbgqskwpjrrjrnmcftwuemirmtdwhyydamgvqepwihgvwexrhfcydyeoylzkdbggfrntljonpjwtahauxgashvxachtuqhljlvraobjchrcye";
    protected String editedPostMessage = "This is my edited post";
    UserActions actions = new UserActions();

    RegistrationPage registrationPage = new RegistrationPage(actions.getDriver());
    LoginPage loginPage = new LoginPage(actions.getDriver());
    PostPage postPage = new PostPage(actions.getDriver());

    @BeforeEach
    public void setUp() {

        UserActions.loadBrowser("weare.homepage");
        Faker faker = new Faker();
        username = generateRandomLowercaseUsername(faker);
        email = generateRandomEmail(faker);

        register(username, email, password);
    }

    @AfterEach
    public void tearDown() {
        logout();
        UserActions.quitDriver();
    }
    private String generateRandomLowercaseUsername(Faker faker) {
        String randomUsername = faker.name().username();
        return removeNonLowercaseLetters(randomUsername);
    }

        private String removeNonLowercaseLetters(String input) {
        StringBuilder result = new StringBuilder();
        for (char c : input.toCharArray()) {
            if (Character.isLowerCase(c)) {
                result.append(c);
            }
        }
        return result.toString();
    }

    protected String generateRandomEmail(Faker faker) {
        return faker.internet().emailAddress();
    }

    protected void register(String username, String email, String password) {
        registrationPage.userRegistration(username, email, password);
        registrationPage.assertUserRegistered();
    }

    protected void login(String username, String password) {
        loginPage.loginUser(username, password);
        loginPage.assertUserIsLoggedIn();
    }
    protected void logout() {
        loginPage.logoutUser();
        loginPage.assertUserIsLoggedOut();
    }
}


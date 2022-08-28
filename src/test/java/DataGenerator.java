import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.Value;
import java.util.Locale;

import static io.restassured.RestAssured.given;


public class DataGenerator {

    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();
    private static final Faker faker = new Faker(new Locale("en"));

    private static void sendRequest(RegistrationDto user) {
        // сам запрос
        given() // "дано"
                .spec(requestSpec) // указываем, какую спецификацию используем
                .body(user) // передаём в теле объект, который будет преобразован в JSON
                .when() // "когда"
                .post("/api/system/users") // на какой путь, относительно BaseUri отправляем запрос
                .then() // "тогда ожидаем"
                .statusCode(200); // код 200 OK
    }

    private DataGenerator() {
    }

    public static String generateLogin() {
        String login = faker.name().username();
        return login;
    }

    public static String generatePassword() {
        String password = faker.internet().password();
        return password;
    }

    public static class RegistrationDto {
        private RegistrationDto(String login, String password, String status) {
            super();
        }

        public static RegistrationDto generateUser(String status) {
            RegistrationDto user = new RegistrationDto(generateLogin(), generatePassword(), status);
            return user;
        }
    }

    @Value
    public static class UserInfo {
        String login;
        String password;
        String status;
    }
}

import Utils.JsonMapper;
import model.credential.Token.ShiroLoginToken;

/**
 * hat is http entity: https://stackoverflow.com/questions/9197745/what-exactly-is-an-http-entity
 * sentin json ob in postman: https://stackoverflow.com/questions/47754392/send-entity-model-in-postman
 * faster xml error (property of parent class): https://stackoverflow.com/questions/4486787/jackson-with-json-unrecognized-field-not-marked-as-ignorable
 */

public class Test {
    public static void main(String[] args) {
        ShiroLoginToken usernamePasswordToken = new ShiroLoginToken();
        usernamePasswordToken.setUsername("dfdf");
        usernamePasswordToken.setPassword("fdf".toCharArray());
        JsonMapper.ObjToJsonString(usernamePasswordToken);
        JsonMapper.JsonStringToObj(JsonMapper.ObjToJsonString(usernamePasswordToken), ShiroLoginToken.class);
    }
}

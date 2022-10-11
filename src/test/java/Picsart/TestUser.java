package Picsart;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Map;

@UtilityClass
public class TestUser {

    public static User normalUser;

    @SneakyThrows
    public static void initializeUsers() {

        Map<String, Object> config = new YamlReader().read(System.getProperty("configFilePath"));
        List<Map<String, String>> users = (List<Map<String, String>>) config.get("uiTestUsers");

        normalUser = new User(users.get(0));
    }
}

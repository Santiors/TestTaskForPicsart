package API;

import org.junit.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class SchemaValidatorAPITest {

    @Test
    public void givenUrl_whenJsonResponseConformsToSchema_thenCorrect() {
        get("https://api.picsart.com/localizations/en/messages?project=reusable_components,photo_editor")
                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("get_component_editor.json"));
    }
}

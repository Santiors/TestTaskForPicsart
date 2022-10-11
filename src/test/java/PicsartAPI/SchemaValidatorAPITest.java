package PicsartAPI;

import org.junit.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class SchemaValidatorAPITest {

    @Test
    public void givenRequest_should_match_JSONSchema_when_get_request() {
        get("https://api.picsart.com/localizations/en/messages?project=reusable_components,photo_editor")
                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("get_component_editor.json"));
    }
}

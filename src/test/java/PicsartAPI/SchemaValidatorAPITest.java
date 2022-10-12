package PicsartAPI;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.platform.engine.TestExecutionResult;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class SchemaValidatorAPITest {

    public static Logger log = LogManager.getLogger();

    @Test
    public void givenRequest_should_match_JSONSchema_when_get_request() {
        try {
            get("https://api.picsart.com/localizations/en/messages?project=reusable_components,photo_editor")
                    .then()
                    .assertThat()
                    .body(matchesJsonSchemaInClasspath("get_component_editor.json"));
            log.info(TestExecutionResult.successful());
        } catch (Exception e) {
            log.info(TestExecutionResult.failed(e));
        }
        //test
    }
}

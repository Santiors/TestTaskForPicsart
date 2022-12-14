package Picsart.Tests;

import Picsart.FunctionalTest;
import Picsart.Page.CreatePage;
import Picsart.RetryRunner;
import Picsart.User;
import lombok.SneakyThrows;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.runner.RunWith;
import org.junitpioneer.jupiter.RetryingTest;

import static Picsart.TestUser.normalUser;

@RunWith(RetryRunner.class)
public class SuccessfulNewProjectCreationUITest extends FunctionalTest {

    private User testUser;
    private CreatePage createPage;

    @Before
    public void setUp() {
        testUser = normalUser;
        createPage = CreatePage
                .getCreatePage(testUser)
                .switchToEditorTab();
    }

    @Test
    public void newProject_creation_shouldBe_successful_when_user_in_loggedIn_state(){
        try {
            createPage
                    .checkDefaultPageState()
                    .closeHints()
                    .checkDefaultCategory();
            log.info(TestExecutionResult.successful());
        } catch (Exception e) {
            log.info(TestExecutionResult.failed(e));
        }
    }

    @SneakyThrows
    @After
    public void tearDown() {
        createPage.logOut();
    }

}

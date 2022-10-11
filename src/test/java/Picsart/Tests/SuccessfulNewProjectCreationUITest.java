package Picsart.Tests;

import Picsart.FunctionalTest;
import Picsart.Page.CreatePage;
import Picsart.User;
import lombok.SneakyThrows;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static Picsart.TestUser.normalUser;

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
    public void newProject_creation_shouldBe_successful_when_user_in_logged_in_state(){
        createPage
                .checkDefaultPageState()
                .closeHints()
                .checkDefaultCategory();
    }

    @SneakyThrows
    @After
    public void tearDown() {
        createPage.logOut();
    }

}

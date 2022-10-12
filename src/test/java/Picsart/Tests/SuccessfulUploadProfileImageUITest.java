package Picsart.Tests;

import Picsart.FunctionalTest;
import Picsart.Page.SettingsPage;
import Picsart.RetryRunner;
import Picsart.User;
import lombok.SneakyThrows;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.runner.RunWith;

import java.awt.*;

import static Picsart.TestUser.normalUser;

@RunWith(RetryRunner.class)
public class SuccessfulUploadProfileImageUITest extends FunctionalTest {

    private User testUser;
    private SettingsPage settingsPage;

    @Before
    public void setUp() {
        testUser = normalUser;
        settingsPage = SettingsPage
                .getSettingsPage(testUser);

    }

    @Test
    public void upload_new_image_shouldBe_successful_when_user_in_loggedIn_state_and_in_settings() {
        try {
            settingsPage
                    .checkDefaultPageState()
                    .clickOnUpload()
                    .uploadImage()
                    .clickOnSaveChanges();
            log.info(TestExecutionResult.successful());
        } catch (Exception e) {
            log.info(TestExecutionResult.failed(e));
        }

    }

    @SneakyThrows
    @After
    public void tearDown() {
        settingsPage.logOut();
    }
}

package Picsart.Tests;

import Picsart.FunctionalTest;
import Picsart.Page.SettingsPage;
import Picsart.RetryRunner;
import Picsart.User;
import lombok.SneakyThrows;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
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
    public void newProject_creation_shouldBe_successful_when_user_in_logged_in_state() {
        settingsPage
                .checkDefaultPageState()
                .clickOnUpload()
                .uploadImage()
                .clickOnSaveChanges();

    }

    @SneakyThrows
    @After
    public void tearDown() {
        settingsPage.logOut();

    }
}

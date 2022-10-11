package Picsart.Tests;

import Picsart.FunctionalTest;
import Picsart.Page.SettingsPage;
import Picsart.User;
import lombok.SneakyThrows;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static Picsart.TestUser.normalUser;

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
    public void newProject_creation_shouldBe_successful_when_user_in_logged_in_state()
            throws AWTException {
        settingsPage
                .checkDefaultPageState()
                .clickOnUpload()
                .uploadImage();

    }

    @SneakyThrows
    @After
    public void tearDown() {

    }
}

package tests;

import com.codeborne.selenide.Configuration;
import utils.attach;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class TestBase {

    @BeforeAll
    static void setUP() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";

        Configuration.browserSize = System.getProperty("browserSize");
        Configuration.remote = System.getProperty("remoteURL");
        Configuration.browser = System.getProperty("browser");
        Configuration.browserVersion = System.getProperty("browserVersion");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        //Configuration.holdBrowserOpen = true;

    }

    @AfterEach
    void setDown(){
        attach.screenshotAs("Last screenshot");
        attach.pageSource();
        attach.browserConsoleLogs();
        attach.addVideo();
    }
}
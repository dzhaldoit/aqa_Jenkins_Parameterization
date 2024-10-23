
package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import utils.RandomUtils;

@DisplayName("Тест регистрации")
@Tag("demoqa")
public class RegistrationDemoQATests extends TestBase {

    private final RegistrationPage registrationPage = new RegistrationPage();
    private final RandomUtils random = new RandomUtils();

    String
            firstName = random.FirstName(),
            lastName = random.LastName(),
            userEmail = random.UserEmail(),
            gender = random.Gender(),
            phoneNumber = random.PhoneNumber(),
            dayOfBirth = random.DayOfBirth(),
            monthOfBirth = random.MonthOfBirth(),
            yearOfBirth = random.YearOfBirth(),
            subjects = random.Subjects(),
            hobbies = random.Hobbies(),
            picName = "avva.jpg",
            currentAddress = random.CurrentAddress(),
            state = random.State(),
            city = random.City(state);

    @Test
    @DisplayName("Позитивный тест регистрации")
    void successfulRegistrationTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        registrationPage
                .openPage()
                .removeBanners()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setUserEmail(userEmail)
                .setGender(gender)
                .setUserNumber(phoneNumber)
                .setDateOfBirth(dayOfBirth, monthOfBirth, yearOfBirth)
                .setSubjects(subjects)
                .setHobbies(hobbies)
                .setPicture(picName)
                .setCurrentAddress(currentAddress)
                .setState(state)
                .setCity(city)
                .clickSubmit();

        registrationPage
                .checkResult("Student Name", firstName + " " + lastName)
                .checkResult("Student Email", userEmail)
                .checkResult("Gender", gender)
                .checkResult("Mobile", phoneNumber)
                .checkResult("Date of Birth",dayOfBirth + " " + monthOfBirth + "," + yearOfBirth)
                .checkResult("Subjects", subjects)
                .checkResult("Hobbies", hobbies)
                .checkResult("Picture", picName)
                .checkResult("Address", currentAddress)
                .checkResult("State and City", state + " " + city);
    }

    @Test
    @DisplayName("Позитивный тест регистрации с минимальными данными")
    void successfulRegistrationWithMinDataTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());


        registrationPage
                .openPage()
                .removeBanners()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender(gender)
                .setUserNumber(phoneNumber)
                .clickSubmit();

        registrationPage
                .checkResult("Student Name", firstName + " " + lastName)
                .checkResult("Gender", gender)
                .checkResult("Mobile", phoneNumber);
    }

    @Test
    @DisplayName("Негативный тест регистрации")
    void negativeRegistrationTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        registrationPage.openPage();
        registrationPage.removeBanners();
        registrationPage.clickSubmit();

        registrationPage.negativeCheck();
    }
}

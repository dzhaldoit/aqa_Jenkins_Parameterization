
package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.ChoiceDateCalendar;
import pages.components.RegistrationResults;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {

    private final SelenideElement
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            genderInput = $("#genterWrapper"),
            userNumberInput = $("#userNumber"),
            dateOfBirth = $("#dateOfBirthInput"),
            subjectsInput = $("#subjectsInput"),
            hobbiesInput = $("#hobbiesWrapper"),
            pictureInput = $("#uploadPicture"),
            addressCurrentInput = $("#currentAddress"),
            stateInput = $("#state"),
            cityInput = $("#city"),
            stateCityWrapper = $("#stateCity-wrapper"),
            submitInput = $("#submit");

    @Step("Открываем страницу регистрации")
    public RegistrationPage openPage() {
        open("/automation-practice-form");
        return this;
    }

    @Step("Удаляем баннер")
    public RegistrationPage removeBanners() {
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;
    }

    @Step("Заполняем форму регистрации Имя: {value}")
    public RegistrationPage setFirstName(String value) {
        firstNameInput.setValue(value);
        return this;
    }

    @Step("Заполняем форму регистрации Фамилия: {value}")
    public RegistrationPage setLastName(String value) {
        lastNameInput.setValue(value);
        return this;
    }

    @Step("Заполняем форму регистрации Email: {value}")
    public RegistrationPage setUserEmail(String value) {
        userEmailInput.setValue(value);
        return this;
    }

    @Step("Заполняем форму регистрации Пол: {value}")
    public RegistrationPage setGender(String value) {
        genderInput.$(byText(value)).click();
        return this;
    }

    @Step("Заполняем форму регистрации Номер: {value}")
    public RegistrationPage setUserNumber(String value) {
        userNumberInput.setValue(value);
        return this;
    }

    @Step("Заполняем форму регистрации Дата рождения: {day}-{month}-{year}")
    public RegistrationPage setDateOfBirth(String day, String month, String year) {
        dateOfBirth.click();
        new ChoiceDateCalendar().setDate(day, month, year);
        return this;
    }

    @Step("Заполняем форму регистрации Предметы: {value}")
    public RegistrationPage setSubjects(String value) {
        subjectsInput.setValue(value).pressEnter();
        return this;
    }

    @Step("Заполняем форму регистрации Хобби: {value}")
    public RegistrationPage setHobbies(String value) {
        hobbiesInput.$(byText(value)).click();
        return this;
    }

    @Step("Заполняем форму регистрации Фото: {value}")
    public RegistrationPage setPicture(String value) {
        pictureInput.uploadFromClasspath(value);
        return this;
    }

    @Step("Заполняем форму регистрации Адрес: {value}")
    public RegistrationPage setCurrentAddress(String value) {
        addressCurrentInput.setValue(value);
        return this;
    }

    @Step("Заполняем форму регистрации Город: {value}")
    public RegistrationPage setState(String value) {
        stateInput.click();
        stateCityWrapper.$(byText(value)).click();
        return this;
    }

    @Step("Заполняем форму регистрации Страна: {value}")
    public RegistrationPage setCity(String value) {
        cityInput.click();
        stateCityWrapper.$(byText(value)).click();
        return this;
    }

    @Step("Нажимаем кнопку Submit")
    public void clickSubmit() {
        submitInput.click();
    }

    @Step("Проверяем результат регистрации")
    public RegistrationPage checkResult(String key, String value) {
        new RegistrationResults().checkResult(key, value);
        return this;
    }

    @Step("Проверяем негативный результат регистрации")
    public void negativeCheck() {
        new RegistrationResults().negativeCheck();
    }
}

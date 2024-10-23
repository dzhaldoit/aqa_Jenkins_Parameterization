package utils;

import com.github.javafaker.Faker;

import java.time.LocalDate;

public class RandomUtils {
    Faker faker = new Faker();

    public String FirstName() {
        return faker.name().firstName();
    }

    public String LastName() {
        return faker.name().lastName();
    }

    public String UserEmail() {
        return faker.internet().emailAddress();
    }

    public String Gender() {
        return faker.options().option("Male", "Female", "Other");
    }

    public String PhoneNumber() {
        return faker.phoneNumber().subscriberNumber(10);
    }

    public String DayOfBirth() {
        return String.format("%02d", faker.number().numberBetween(1,28));
    }

    public String MonthOfBirth() {
        return faker.options().option("January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December");
    }

    public String YearOfBirth() {
        return String.valueOf(faker.number().numberBetween(1900, LocalDate.now().getYear()));
    }

    public String Subjects() {
        return faker.options().option("Economics", "English", "Arts", "Social Studies");
    }

    public String Hobbies() {
        return faker.options().option("Sports", "Reading", "Music");
    }

    public String CurrentAddress() {
        return faker.address().streetAddress();
    }

    public String State() {
        return faker.options().option("NCR", "Haryana", "Uttar Pradesh", "Rajasthan");
    }

    public String City(String state) {
        return switch (state) {
            case "NCR" -> faker.options().option("Delhi", "Gurgaon", "Noida");
            case "Uttar Pradesh" -> faker.options().option("Lucknow", "Merrut", "Agra");
            case "Haryana" -> faker.options().option("Panipap", "Karnal");
            case "Rajasthan" -> faker.options().option("Jaipur", "Jaisalmer");
            default -> "";
        };
    }

}
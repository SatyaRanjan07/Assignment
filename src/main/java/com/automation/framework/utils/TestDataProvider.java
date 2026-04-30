package com.automation.framework.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.FileReader;
import java.io.IOException;

public class TestDataProvider {
    private static final String TEST_DATA_PATH = "src/test/resources/testdata.json";
    private static JsonObject testData;

    static {
        loadTestData();
    }

    private static void loadTestData() {
        try (FileReader reader = new FileReader(TEST_DATA_PATH)) {
            Gson gson = new Gson();
            testData = gson.fromJson(reader, JsonObject.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getEcommerceValidEmail() {
        return testData.getAsJsonObject("ecommerce")
                .getAsJsonObject("validUser")
                .get("email").getAsString();
    }

    public static String getEcommerceValidPassword() {
        return testData.getAsJsonObject("ecommerce")
                .getAsJsonObject("validUser")
                .get("password").getAsString();
    }

    public static String getEcommerceNewUserName() {
        return testData.getAsJsonObject("ecommerce")
                .getAsJsonObject("newUser")
                .get("name").getAsString();
    }

    public static String getEcommerceNewUserEmail() {
        String email = testData.getAsJsonObject("ecommerce")
                .getAsJsonObject("newUser")
                .get("email").getAsString();
        return email.replace("{{timestamp}}", String.valueOf(System.currentTimeMillis()));
    }

    public static String getEcommerceNewUserPassword() {
        return testData.getAsJsonObject("ecommerce")
                .getAsJsonObject("newUser")
                .get("password").getAsString();
    }

    public static String getInvalidEmail() {
        return testData.getAsJsonObject("ecommerce")
                .getAsJsonObject("invalidInputs")
                .get("invalidEmail").getAsString();
    }

    public static String getEmptyEmail() {
        return testData.getAsJsonObject("ecommerce")
                .getAsJsonObject("invalidInputs")
                .get("emptyEmail").getAsString();
    }

    public static String getShortPassword() {
        return testData.getAsJsonObject("ecommerce")
                .getAsJsonObject("invalidInputs")
                .get("shortPassword").getAsString();
    }

}

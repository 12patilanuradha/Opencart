package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.HomePage;
import PageObjects.RegistrationPage;
import testBase.BaseClass;

public class TC_01AccountRegistrationPage extends BaseClass {

    @Test
    public void verify_account_registration() {

        logger.info("***** Starting Account Registration Test *****");

        try {
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            logger.info("Clicked on MyAccount");

            hp.clickRegister();
            logger.info("Clicked on Register");

            RegistrationPage regpage = new RegistrationPage(driver);

            logger.info("Providing customer details...");

            regpage.setFirstName(randomString().toUpperCase());
            regpage.setLastName(randomString().toUpperCase());
            regpage.setEmail(randomString() + "@gmail.com");
            regpage.setTelephone(randomNumber());

            String password = randomAlphaNumeric();

            regpage.setPassword(password);
            regpage.setConfirmPassword(password);

            regpage.setPrivacyPolicy();
            regpage.clickContinue();

            logger.info("Validating expected message...");

            String confmsg = regpage.getConfirmationMsg();

            Assert.assertEquals(confmsg, "Your Account Has Been Created!");

            logger.info("Test Passed");

        } catch (Exception e) {

            logger.error("Test failed", e);
            Assert.fail("Test Failed");
        }
    }

    // 🔹 Utility methods
    public String randomString() {
        return org.apache.commons.lang3.RandomStringUtils.randomAlphabetic(5);
    }

    public String randomNumber() {
        return org.apache.commons.lang3.RandomStringUtils.randomNumeric(10);
    }

    public String randomAlphaNumeric() {
        return org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric(8);
    }
}
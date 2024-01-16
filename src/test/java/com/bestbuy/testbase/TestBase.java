package com.bestbuy.testbase;

import com.bestbuy.utils.PropertyReader;
import com.bestbuy.utils.TestUtils;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class TestBase extends TestUtils {
    @BeforeClass
    public void inIt() {
        RestAssured.baseURI = PropertyReader.getInstance().getProperty("baseURI");

    }
}

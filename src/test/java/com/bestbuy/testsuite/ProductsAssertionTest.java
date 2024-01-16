package com.bestbuy.testsuite;

import com.bestbuy.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ProductsAssertionTest extends TestBase {
    static ValidatableResponse response;

    @BeforeClass
    public static void response() {
        response = given()
                .when()
                .get("/products")
                .then().statusCode(200);
        response.log().all();
    }

    //1. Verify the if the total is equal to 51957
    @Test
    public void verifyTheTotal() {
        response.body("total", equalTo(51957));
    }

    //2. Verify the if the stores of limit is equal to 10
    @Test
    public void verifyLimitOfStore() {
        response.body("limit", equalTo(10));
    }

    //3. Check the single ‘Name’ in the Array list (Duracell - AAA Batteries (4-Pack))
    @Test
    public void verifyName() {
        response.body("data.name", hasItem("Duracell - AAA Batteries (4-Pack)"));
    }

    //4. Check the multiple ‘Names’ in the ArrayList (Duracell - AA 1.5V CopperTop Batteries (4-Pack),
    // Duracell - AA Batteries (8-Pack), Energizer - MAX Batteries AA (4-Pack))
    @Test
    public void verifyNames() {
        response.body("data.name", hasItems(("Duracell - AA 1.5V CopperTop Batteries (4-Pack)"),
                ("Duracell - AA Batteries (8-Pack)"), ("Energizer - MAX Batteries AA (4-Pack)")));
    }

    //5. Verify the productId=150115 inside categories of the third name is “Household Batteries”
    @Test
    public void verifyTheProductId() {
        response.body("data[0].categories[2].name", equalTo("Household Batteries"));
    }

    //6. Verify the categories second name = “Housewares” of productID = 333179
    @Test
    public void verifySecondCategoryName() {
        response.body("data[8].categories[1].name", equalTo("Housewares"));
    }

    //7. Verify the price = 4.99 of forth product
    @Test
    public void verifyPrice() {
        response.body("data[3].price", equalTo(4.99f));
    }

    //8. Verify the Product name = Duracell - D Batteries (4-Pack) of 6th
    @Test
    public void verifyTheProductName() {
        response.body("data[5].name", equalTo("Duracell - D Batteries (4-Pack)"));
    }

    //9. Verify the ProductId = 333179 for the 9th product
    @Test
    public void verifyTheProductIdOf9thProduct() {
        response.body("data[8].id", equalTo(333179));
    }

    //10. Verify the prodctId = 346575 have 5 categories
    @Test
    public void verifyCategories() {
        response.body("data[9].categories", hasSize(5));
    }
}

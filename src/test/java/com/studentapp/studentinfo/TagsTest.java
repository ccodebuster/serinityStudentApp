package com.studentapp.studentinfo;

import com.studentapp.testbase.TestBase;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by bhavesh
 */
@RunWith(SerenityRunner.class)
public class TagsTest extends TestBase {
    @WithTag("studentfeature:NEGATIVE")
    @Title("provide 405 status code with incorrect method")
    @Test
    public void invalidMethod() {
        SerenityRest.rest().given()
                .when()
                .post("list")
                .then()
                .statusCode(405)
                .log()
                .all();
    }
   /* @WithTag("studentfeature:POSITIVE")
    @Title("This will verify the status code 200")
    @Test
    public void verifyIfTheStatusCodeIs200() {
        SerenityRest.rest().given()
                .when()
                .post("list")
                .then()
                .statusCode(200)
                .log()
                .all();
    }*/

}

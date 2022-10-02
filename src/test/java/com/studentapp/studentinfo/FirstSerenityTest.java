package com.studentapp.studentinfo;

import com.studentapp.constants.EndPoints;
import com.studentapp.testbase.TestBase;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Manual;
import net.thucydides.core.annotations.Pending;
import net.thucydides.core.annotations.Title;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static io.restassured.RestAssured.given;

/**
 * Created by bhavesh
 */
@RunWith(SerenityRunner.class)// required if you would like to generate reports
public class FirstSerenityTest extends TestBase {

    @Test
    public void getAllStudents(){
        SerenityRest.given()
                .when()
                .get(EndPoints.GET_ALL_STUDENT)
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void thisIsFailingTest(){
        SerenityRest.given()
                .when()
                .get(EndPoints.GET_ALL_STUDENT)
                .then()
                .log().all()
                .statusCode(201);
    }

    @Pending
    @Test
    public void thisIsPending(){
        System.out.println("working on codes");
    }

    @Ignore
    @Test
    public void thisIsSkipped(){
        System.out.println("ignore my test");
    }

    @Manual
    @Test
    public void manual(){

    }

    @Title("This is will get all information of all students")
    @Test
    public void test001(){
        given()
                .when().get(EndPoints.GET_ALL_STUDENT)
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void thisIsCompromised() throws FileNotFoundException {
        File file=new File("E://file.txt");
        FileReader fr =new FileReader(file);
    }

}

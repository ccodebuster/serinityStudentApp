package com.studentapp.studentinfo;

import com.studentapp.testbase.TestBase;
import com.studentapp.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.hasValue;

/**
 * Created by bhavesh
 */
@RunWith(SerenityRunner.class)
public class StudentCURDTestWithSteps extends TestBase {

    static String firstName = "bhavesh" + TestUtils.getRandomValue();//bhav123
    static String lastName = "patel" + TestUtils.getRandomValue();
    static String programme = "api testing";
    static String email = TestUtils.getRandomValue() + "user@gmail.com";
    //static String email= "user" + TestUtils.getRandomValue()+"@gmail.com";
    static int studentId;

    @Steps
    StudentSteps studentSteps;

    @Title("This will create a new student")
    @Test
    public void test001() {
        List<String> courseList = new ArrayList<>();
        courseList.add("java");
        courseList.add("api");
        ValidatableResponse response = studentSteps.createStudent(firstName, lastName, email, programme, courseList);
        response.log().all().statusCode(201);
    }

    @Title("verify if student is created")
    @Test
    public void test002() {
        //studentSteps.getAllStudentInfo();
        HashMap<String, Object> studentMap = studentSteps.getStudentInfoByFirstName(firstName);
        Assert.assertThat(studentMap, hasValue(firstName));
        studentId = (int) studentMap.get("id");
        System.out.println(studentId);
    }

    @Title("update the user information")
    @Test
    public void test003() {
        firstName = firstName + "updated";
        //  firstName= bhav123updated;
        List<String> courseList = new ArrayList<>();
        courseList.add("java1");
        courseList.add("api1");
        studentSteps.updateStudent(studentId, firstName, lastName, email, programme, courseList);
        HashMap<String, Object> studentMap = studentSteps.getStudentInfoByFirstName(firstName);
        Assert.assertThat(studentMap, hasValue(firstName));
    }
    @Title("Delete student info by StudentID and verify its deleted")
    @Test
    public void test004(){
        studentSteps.deleteStudentInfoByID(studentId).log().all().statusCode(204);
        studentSteps.getStudentInfoByStudentId(studentId).log().all().statusCode(404);
    }

}

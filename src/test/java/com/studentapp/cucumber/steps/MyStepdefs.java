package com.studentapp.cucumber.steps;

import com.studentapp.studentinfo.StudentSteps;
import com.studentapp.utils.TestUtils;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.hasValue;


public class MyStepdefs {
    static ValidatableResponse response;
    @Steps
    StudentSteps studentSteps;
    static String email=null;
    static String firstName;

    @When("^User sends a GET request to list endpoint$")
    public void userSendsAGETRequestToListEndpoint() {
        response =studentSteps.getAllStudentInfo();
    }

    @Then("^User must get back a valid status code 200$")
    public void userMustGetBackAValidStatusCode() {
        response.statusCode(200);
    }

    @When("^I create a new student by providing the information firstName \"([^\"]*)\" lastName \"([^\"]*)\" email \"([^\"]*)\" programme \"([^\"]*)\" courses \"([^\"]*)\"$")
    public void iCreateANewStudentByProvidingTheInformationFirstNameLastNameEmailProgrammeCourses(String firstName, String lastName, String _email, String programme, String courses)  {
        List<String>courseList=new ArrayList<>();
        courseList.add(courses);
        email= TestUtils.getRandomValue()+_email;
       // firstName=firstName+"updated";
        response=studentSteps.createStudent(firstName,lastName,email,programme,courseList);
    }

    @Then("^I verify that the student with \"([^\"]*)\" is created$")
    public void iVerifyThatTheStudentWithIsCreated(String _email)  {
       response.statusCode(201);
        HashMap<String,Object> studentMap =studentSteps.getStudentInfoByEmail(email);
        Assert.assertThat(studentMap,hasValue(email));
    }

    /*@Then("^I verify that the student with \"([^\"]*)\" is created$")
    public void iVerifyThatgetStudentInfoByFirstName(String firstName)  {
        response.statusCode(201);
        HashMap<String,Object> studentMap =studentSteps.getStudentInfoByFirstName(firstName);
        Assert.assertThat(studentMap,hasValue(firstName));
    }*/

}

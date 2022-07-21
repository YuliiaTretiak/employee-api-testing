package employeeTests;

import employee.api.clients.BaseClient;
import employee.api.clients.EmployeeClient;
import employee.dto.Employee;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static employee.api.clients.EmployeeClient.*;
import static io.restassured.RestAssured.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.*;

public class E2EPositiveTest {

    @Test
    public void createEmployee (){
        Employee employee = new Employee();
        employee.setId(1058);
        employee.setName("Alex");
        employee.setPassportNumber("BR741852");
        employee.setEducation("College");

        String employeeResponse = new EmployeeClient().createEmployee(employee);

        assertThat(employeeResponse)
                .isNotNull()
                .contains("has been added");
    }

    @Test
    public void checkBodyResponse(){
        Employee [] employees =   new EmployeeClient().getEmployees();
        assertThat(employees).extracting(Employee::getName).contains("Alex");
    }



}

package employee;

import employee.api.clients.EmployeeClient;
import employee.dto.Employee;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static employee.api.factory.EmployeeTemplateFactory.*;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class E2EPositiveTest {
    Employee postEmployee;
    List<Employee> postEmployeeList;
    EmployeeClient client;
    Employee putEmployee;
    Employee patchEmployee;
    Employee getEmployee;

    @BeforeAll
    public void EmployeeRequest() throws IOException {
        client = new EmployeeClient();
        postEmployeeList = createEmployeeListTemplate();
        postEmployee = createEmployeeTemplate();
        putEmployee = putEmployeeTemplate();
        patchEmployee = patchEmployeeTemplate();
        getEmployee = getEmployeeResponseTemplate();
    }

    @Test
    @AfterAll
    public void deleteAll() {
        client.deleteAllEmployees()
                .assertThat()
                .statusCode(HTTP_OK)
                .header("Content-Type", equalTo("text/plain;charset=UTF-8"))
                .header("Content-Length", equalTo("45"))
                .body(equalTo("All employees have been successfully deleted!"));
    }


    @Test
    public void checkThatEmployeeIsCreated() {
        int testId = postEmployee.getId();

        client.postEmployee(postEmployee)
                .assertThat()
                .statusCode(HTTP_OK)
                .header("Content-Type", equalTo("text/plain;charset=UTF-8"))
                .header("Content-Length", equalTo("38"))
                .body(equalTo(String.format("Employee with id = %s has been added!", testId)));

        client.getEmployeeById(testId)
                .assertThat()
                .statusCode(HTTP_OK)
                .body("id", equalTo(postEmployee.getId()))
                .body("name", equalTo(postEmployee.getName()))
                .body("passportNumber", notNullValue())
                .body("education", equalTo(postEmployee.getEducation()));
    }

    @Test
    public void checkThatEmployeeListIsCreated() {
        List<Integer> idList = postEmployeeList
                .stream()
                .map(Employee::getId)
                .collect(Collectors.toList());

        client.postEmployeeList(postEmployeeList)
                .assertThat()
                .statusCode(HTTP_OK)
                .header("Content-Type", equalTo("application/json;charset=UTF-8"))
                .header("Transfer-Encoding", equalTo("chunked"))
                .body("", hasSize((int) postEmployeeList.stream().count()))
                .body("id", hasItems(idList.toArray()));

        JsonPath jsonPath = client.getAllEmployees()
                .assertThat()
                .statusCode(HTTP_OK)
                .body("id", hasItems(idList.toArray()))
                .extract().body()
                .jsonPath();

        List<Employee> employees = jsonPath.getList("", Employee.class).stream().toList();
        assertThat(employees.contains(postEmployeeList));
    }

    @Test
    public void checkPutEmployeeRequest() {
        client.putEmployee(putEmployee)
                .assertThat()
                .statusCode(HTTP_OK)
                .header("Content-Type", equalTo("text/plain;charset=UTF-8"))
                .header("Content-Length", equalTo("40"))
                .body(equalTo(String.format( "Employee with id = %s has been updated!", putEmployee.getId())));

        client.getEmployeeById(putEmployee.getId())
                .assertThat()
                .statusCode(HTTP_OK)
                .body("id", equalTo(putEmployee.getId()))
                .body("name", equalTo(putEmployee.getName()))
                .body("passportNumber", equalTo(putEmployee.getPassportNumber()))
                .body("education", equalTo(putEmployee.getEducation()));
    }

    @Test
    public void checkPatchEmployeeRequest() {
        client.patchEmployee(patchEmployee)
                .assertThat()
                .statusCode(HTTP_OK)
                .header("Content-Type", equalTo("text/plain;charset=UTF-8"))
                .header("Content-Length", equalTo("50"))
                .body(equalTo(String.format( "Employee with id = %s has been partially updated!", patchEmployee.getId())));

        client.getEmployeeById(patchEmployee.getId())
                .assertThat()
                .statusCode(HTTP_OK)
                .body("id", equalTo(patchEmployee.getId()))
                .body("name", equalTo(patchEmployee.getName()))
                .body("passportNumber", equalTo(patchEmployee.getPassportNumber()))
                .body("education", equalTo(patchEmployee.getEducation()));
    }

    @Test
    public void deleteEmployeeByIdCheck() {
        Employee testId = postEmployeeList.stream()
                .filter(e -> "John".equals(e.getName()))
                .findFirst()
                .get();

        client.deleteEmployeeById(testId.getId())
                .assertThat()
                .statusCode(HTTP_OK)
                .header("Content-Type", equalTo("text/plain;charset=UTF-8"))
                .header("Content-Length", equalTo("53"))
                .body(equalTo(String.format( "Employee with id = %s has been successfully deleted!", testId.getId() )));
    }

}

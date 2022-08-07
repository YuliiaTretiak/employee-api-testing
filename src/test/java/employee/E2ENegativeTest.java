package employee;

import employee.api.clients.EmployeeClient;
import employee.dto.Employee;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.util.List;

import static employee.api.factory.EmployeeTemplateFactory.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static java.net.HttpURLConnection.HTTP_INTERNAL_ERROR;
import static org.hamcrest.Matchers.equalTo;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class E2ENegativeTest {

    Employee employee;
    EmployeeClient client;
    Employee putEmployee;
    List<Employee> postEmployeeList;

    @BeforeAll
    public void EmployeeRequest() throws IOException {
        client = new EmployeeClient();
        employee = createEmployeeTemplate();
        putEmployee = putEmployeeTemplate();
        postEmployeeList = createEmployeeListTemplate();
    }

    @Test
    public void checkResponseForDeletedObjectsById() {
        Integer testId = postEmployeeList.stream()
                .filter(e -> "John".equals(e.getName()))
                .findFirst()
                .get()
                .getId();

        client.getEmployeeById(testId)
                .assertThat()
                .statusCode(HTTP_INTERNAL_ERROR)
                .header("Content-Type", equalTo("application/json;charset=UTF-8"))
                .header("Transfer-Encoding", equalTo("chunked"))
                .header("Connection", equalTo("close"))
                .body(matchesJsonSchemaInClasspath(filePathNotFoundResponse));

        client.deleteEmployeeById(testId)
                .assertThat()
                .statusCode(HTTP_INTERNAL_ERROR)
                .header("Content-Type", equalTo("application/json;charset=UTF-8"))
                .header("Transfer-Encoding", equalTo("chunked"))
                .header("Connection", equalTo("close"))
                .body(matchesJsonSchemaInClasspath(filePathNotFoundResponse));
    }

    @Test
    public void checkPutEmployeeRequestNegative() {
        client.putEmployee(putEmployee)
                .assertThat()
                .statusCode(HTTP_INTERNAL_ERROR)
                .header("Content-Type", equalTo("application/json;charset=UTF-8"))
                .body(matchesJsonSchemaInClasspath(filePathDoesNotExistResponse));
    }
}

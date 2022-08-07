package employee.api.clients;

import employee.api.config.Configuration;
import employee.api.config.ConfigurationManager;
import employee.dto.Employee;
import io.restassured.response.ValidatableResponse;

import java.util.ArrayList;
import java.util.List;

public class EmployeeClient extends BaseClient {

    private static Configuration config = ConfigurationManager.getConfiguration();

    public EmployeeClient() {
        super(config.baseURI(), config.employeePath());
    }

    public ValidatableResponse postEmployee(Employee employee) {
        return post(employee);
    }

    public ValidatableResponse postEmployeeList(List<Employee> employees) {
        return postList(new ArrayList<Object>(employees), config.employeeListPath());
    }

    public ValidatableResponse getAllEmployees() {
        return getResponseBody();
    }

    public ValidatableResponse getEmployeeById(Integer id){
        return getById(id);
    }

    public ValidatableResponse putEmployee(Employee employee) {
        return put(employee);
    }

    public ValidatableResponse patchEmployee(Employee employee) {
        return patch(employee);
    }

    public ValidatableResponse deleteEmployeeById(Integer id) {
        return deleteByID(id);
    }

    public ValidatableResponse deleteAllEmployees() {
        return delete();
    }


}

package employeeTests;

import employee.api.clients.EmployeeClient;
import employee.dto.Employee;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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

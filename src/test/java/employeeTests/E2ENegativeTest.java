package employeeTests;

import employee.api.clients.EmployeeClient;
import employee.dto.Employee;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class E2ENegativeTest {

    @Test
    public void createEmployeeDuplicate (){
        Employee employee = new Employee();
        employee.setId(1018);
        employee.setName("Alex");
        employee.setPassportNumber("BR741852");
        employee.setEducation("College");

        EmployeeClient cl = new EmployeeClient();
        String employeeResponse = cl.createEmployee(employee);
        String employeeResponse2 = cl.createEmployee(employee);

        assertThat(employeeResponse)
                .isNotNull()
                .contains("has been added");

        assertThat(employeeResponse2)
                .isNotNull()
                .contains("already exists");
    }

}

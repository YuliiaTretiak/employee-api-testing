package employee.api.clients;
import employee.dto.Employee;

import java.util.List;

//Rest + get put post delete patch methods для employee, перевикористовуючи BaseClient
public class EmployeeClient extends BaseClient {

    public EmployeeClient()
    {
        super("http://localhost:9090/", "employee/");
    }

    public String createEmployee(Employee employee) {
        return super.create(employee);
    }

    public Employee getEmployee() {
        return super.get().as(Employee.class);
    }

    public Employee[] getEmployees() {
        return  super.getList().as(Employee[].class);
    }
}

package employee.api.factory;

import employee.dto.Employee;

import java.io.IOException;
import java.util.List;

public class EmployeeTemplateFactory {

    public static String filePathCreateEmployee = "src/test/resources/templates/gcp/post_employee/request.json";
    private static String filePathCreateEmployeeList = "src/test/resources/templates/gcp/post_employees/request.json";
    private static String filePathGetEmployee = "src/test/resources/templates/gcp/get_employee/request.json";
    private static String filePathGetEmployeeList = "src/test/resources/templates/gcp/get_employees/request.json";
    private static String filePathPutEmployee = "src/test/resources/templates/gcp/put_employee/request.json";
    private static String filePathPatchEmployee = "src/test/resources/templates/gcp/patch_employee/request.json";
    private static String filePathDeleteEmployee = "src/test/resources/templates/gcp/delete_employee/request.json";
    private static String filePathDeleteEmployees = "src/test/resources/templates/gcp/delete_employees/request.json";

    public static String filePathGetEmployeeResponse = "src/test/resources/templates/gcp/get_employee/response.json";
    public static String filePathGetEmployeeListResponse = "templates/gcp/get_employees/response.json";
    public static String filePathCreateEmployeeResponse = "src/test/resources/templates/gcp/post_employee/response.json";
    public static String filePathCreateEmployeesResponse = "src/test/resources/templates/gcp/post_employees/response.json";
    public static String filePathDeleteEmployeesResponse = "src/test/resources/templates/gcp/delete_employees/response.json";
    public static String filePathNotFoundResponse = "templates/gcp/get_employee/not_found_response.json";
    public static String filePathDoesNotExistResponse = "templates/gcp/put_employee/doent_exist_response.json";
    public static String filePathPostEmployeeRequest = "templates/gcp/post_employee/request.json";

    public static Employee createEmployeeTemplate() throws IOException {
        return Employee.deserializeTemplate(filePathCreateEmployee);
    }

    public static List<Employee> createEmployeeListTemplate() throws IOException {
        return Employee.deserializeArrayTemplate(filePathCreateEmployeeList);
    }

    public static List<Employee> getEmployeeListResponseTemplate() throws IOException {
        return Employee.deserializeArrayTemplate(filePathGetEmployeeListResponse);
    }

    public static Employee getEmployeeTemplate() throws IOException {
        return Employee.deserializeTemplate(filePathGetEmployee);
    }

    public static Employee getEmployeeListTemplate() throws IOException {
        return Employee.deserializeTemplate(filePathGetEmployeeList);
    }

    public static Employee putEmployeeTemplate() throws IOException {
        return Employee.deserializeTemplate(filePathPutEmployee);
    }

    public static Employee patchEmployeeTemplate() throws IOException {
        return Employee.deserializeTemplate(filePathPatchEmployee);
    }

    public static Employee deleteEmployeeTemplate() throws IOException {
        return Employee.deserializeTemplate(filePathDeleteEmployee);
    }

    public static List<Employee> deleteEmployeeListTemplate() throws IOException {
        return Employee.deserializeArrayTemplate(filePathDeleteEmployees);
    }

    public static Employee getEmployeeResponseTemplate() throws IOException {
        return Employee.deserializeTemplate(filePathGetEmployeeResponse);
    }

}

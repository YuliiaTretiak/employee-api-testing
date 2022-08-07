package employee.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Employee implements Serializable {
    private Integer id;
    private String name;
    private String passportNumber;
    private String education;

    public static Employee deserializeTemplate(String filePath) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.fromJson(new FileReader(filePath), Employee.class);
    }

    public static List<Employee> deserializeArrayTemplate(String filePath) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return Arrays.stream(gson.fromJson(new FileReader(filePath), Employee[].class)).toList();
    }
}

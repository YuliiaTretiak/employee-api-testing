package employee.api.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;
import org.aeonbits.owner.Config.Sources;

@LoadPolicy(LoadType.MERGE)
@Sources({"system:properties",
        "classpath:employeeapplication.properties"})
public interface Configuration extends Config {

    @Key("api.baseUri")
    String baseURI();

    @Key("api.employeePath")
    String employeePath();

    @Key("api.employeeListPath")
    String employeeListPath();

}

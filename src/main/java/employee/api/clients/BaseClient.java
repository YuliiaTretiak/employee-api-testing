package employee.api.clients;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ResponseBodyExtractionOptions;
import io.restassured.specification.RequestSpecification;


import static io.restassured.RestAssured.*;

//Rest + get put post delete patch methods

public class BaseClient {

    public BaseClient(String base, String basePass)
    {
        this.REQ_SPEC = new RequestSpecBuilder()
                .setBaseUri(base)
                .setBasePath(basePass)
                .setContentType(ContentType.JSON)
                .build();
    }

    private RequestSpecification REQ_SPEC;

    public ResponseBodyExtractionOptions get() {
        return given()
                .spec(REQ_SPEC)
                .when().get()
                .then().extract().body();
    }

    public String create(Object body) {
        return given().spec(REQ_SPEC).body(body).post().then().extract().body().asString();
    }

    public <List> ResponseBodyExtractionOptions getList() {
        return given()
                .spec(REQ_SPEC)
                .when().get()
                .then().extract().body();
    }
}
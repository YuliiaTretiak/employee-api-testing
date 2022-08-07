package employee.api.clients;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.util.List;

import static io.restassured.RestAssured.given;

public abstract class BaseClient {

    private RequestSpecification REQ_SPEC;

    public BaseClient(String base, String basePath) {
        this.REQ_SPEC = new RequestSpecBuilder()
                .setBaseUri(base)
                .setBasePath(basePath)
                .setContentType(ContentType.JSON)
                .build();
    }

    public ValidatableResponse getResponseBody() {
        return given()
                .spec(REQ_SPEC)
                .when().get()
                .then();
    }

    public ValidatableResponse getById(Integer id){
        return given()
                .spec(REQ_SPEC)
                .when().get(String.valueOf(id))
                .then();
    }

    public ValidatableResponse post(Object body) {
        return given().spec(REQ_SPEC).body(body).post().then();
    }

    public ValidatableResponse postList(List<Object> body, String listPath) {
        return given()
                .spec(REQ_SPEC)
                .body(body)
                .when().post(listPath)
                .then();
    }

    public ValidatableResponse getList() {
        return given()
                .spec(REQ_SPEC)
                .when().get()
                .then();
    }

    public ValidatableResponse put(Object object) {
        return given()
                .spec(REQ_SPEC)
                .body(object)
                .when().put()
                .then();
    }

    public ValidatableResponse patch(Object object) {
        return given()
                .spec(REQ_SPEC)
                .body(object)
                .when().patch()
                .then();
    }

    public ValidatableResponse deleteByID(Integer id) {
        return given()
                .spec(REQ_SPEC)
                .when().delete(String.valueOf(id))
                .then();
    }

    public ValidatableResponse delete() {
        return given()
                .spec(REQ_SPEC)
                .when().delete()
                .then();
    }
}
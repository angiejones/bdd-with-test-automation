package utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ServicesUtils {

    public enum HttpMethod {
        GET("get"), POST("post");

        private String method;
        private HttpMethod(String method){
            this.method = method;
        }

        public String getMethod() {
            return method;
        }
    }

    private static RequestSpecification request = given().accept(ContentType.JSON);

    public static Response execute(String endpoint, HttpMethod method){
        return execute(endpoint, method, true);
    }

    public static Response execute(String endpoint,
                                   HttpMethod method,
                                   boolean verifyStatusCode){

        System.out.println(endpoint);
        Response response = null;

        switch(method){
            case GET:
                response = request.get(endpoint);
                break;

            case POST:
                response = request.post(endpoint);
                break;
        }

        response.then().log().all();

        if(verifyStatusCode){
            response.then().statusCode(200);
        }

        return response;
    }
}

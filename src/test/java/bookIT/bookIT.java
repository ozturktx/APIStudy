package bookIT;

import POJO.Rooms;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;

public class bookIT {


    @Before
    public void setUp()
{
    RestAssured.baseURI="https://cybertek-reservation-api-qa.herokuapp.com/";
}

    @Test
    public void getTokenWithCorrectEmail()
    {
        given().queryParam("email","teacherva5@gmail.com")
                .queryParam("password","maxpayne")
                .then().log().all()
                .when().get("sign")
                .then().log().all().assertThat().statusCode(200);
    }
    @Test
    public void getTokenWithWrongEmail()
    {
        given().queryParam("email","teacherva51@gmail.com")
                .queryParam("password","maxpayne")
                .then().log().all()
                .when().get("sign")
                .then().log().all().assertThat().statusCode(422);
    }
    @Test
    public void getTokenWithWrongPassword()
    {
        given().queryParam("email","teacherva5@gmail.com")
                .queryParam("password","maxpayne1")
                .then().log().all()
                .when().get("sign")
                .then().log().all().assertThat().statusCode(422)
                .body(is("invalid password."));
    }

    @Test
    public void getAllRooms()
    {
        String token = GetToken.getTeacherToken();
        Response response=given().header("Authorization", token).get("api/rooms");

        /*Rooms rooms = response.as(Rooms.class);
        System.out.println("r1.getName() = " + rooms.getId());*/

        //Deserialization
       // Gson gson = new Gson();
       // Rooms p2 = gson.fromJson(response.asString(), Rooms.class);
        //System.out.println("p2 = " + p2);

        JsonPath jsonPath = response.jsonPath();
        List<String> idsString = jsonPath.get("id");
        int size1=idsString.size();
        Set<String>ids=new HashSet<String>(idsString);
        int size2=ids.size();
        Assert.assertEquals(size1,size2);
        System.out.println(idsString);


    }

}

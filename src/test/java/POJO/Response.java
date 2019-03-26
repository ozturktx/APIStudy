package POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


public class Response {

    public List<Rooms> getRooms()
    {
        return rooms;
    }

    private List<Rooms> rooms;
}

package model;

import com.lynden.gmapsfx.javascript.object.LatLong;

import java.util.HashMap;
import java.util.Map;

public class Village {
    private String name;
    private LatLong location;

    public Village(String name, LatLong location) {
        this.name = name;
        this.location = location;
    }

    public LatLong getLocation() {
        return location;
    }

    public void setLocation(LatLong location) {
        this.location = location;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

package model;

import com.lynden.gmapsfx.javascript.object.LatLong;

import java.util.Map; 

public class Village {
    private String name;
    private LatLong location;
    private Map<Village,Double> villageMap;
    private boolean hasBin;

    public Village(String name, LatLong location, Map<Village, Double> villageMap, boolean hasBin) {
        this.name = name;
        this.location = location;
        this.villageMap = villageMap;
        this.hasBin = hasBin;
    }

    public LatLong getLocation() {
        return location;
    }

    public void setLocation(LatLong location) {
        this.location = location;
    }

    public Map<Village, Double> getVillageMap() {
        return villageMap;
    }

    public void setVillageMap(Map<Village, Double> villageMap) {
        this.villageMap = villageMap;
    }

    public boolean isHasBin() {
        return hasBin;
    }

    public void setHasBin(boolean hasBin) {
        this.hasBin = hasBin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

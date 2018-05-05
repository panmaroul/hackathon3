package controllers;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.*;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import other.Controllers;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable, MapComponentInitializedListener {


    public AnchorPane anchorPane;
    public GoogleMapView mapView;
    private GoogleMap map;


    @Override
    public void mapInitialized() {
        List<LatLong> coordinates = new ArrayList<>();

        LatLong Serres = new LatLong(41.092083, 23.541016);
        LatLong Provatas = new LatLong(41.068238, 23.390686);
        LatLong Ano_Kamila = new LatLong(41.058320, 23.424134);
        LatLong Katw_Kamila = new LatLong(41.020431, 23.483293);
        LatLong Katw_Mitrousi	 = new LatLong(41.058680, 23.457547);
        LatLong Koumaria = new LatLong(41.016434, 23.434656);
        LatLong Skoutari = new LatLong(41.020032, 23.520701);
        LatLong Adelfiko = new LatLong(41.014645, 23.457354);
        LatLong Agia_Eleni	 = new LatLong(41.003545, 23.559196);
        LatLong Peponia = new LatLong(40.988154, 23.516756);
        coordinates.add(Serres);
        coordinates.add(Provatas);
        coordinates.add(Ano_Kamila);
        coordinates.add(Katw_Kamila);
        coordinates.add(Katw_Mitrousi);
        coordinates.add(Koumaria);
        coordinates.add(Skoutari);
        coordinates.add(Adelfiko);
        coordinates.add(Agia_Eleni);
        coordinates.add(Peponia);



        //Set the initial properties of the map.
        MapOptions mapOptions = new MapOptions();

        mapOptions.center(new LatLong(47.6097, -122.3331))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(12);

        map = mapView.createMap(mapOptions);
        showMarkers(coordinates);
        //Add markers to the map

//        InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
//        infoWindowOptions.content("<h2>Serres</h2>"
//                + "Current Location: Safeway<br>"
//                + "ETA: 45 minutes" );
//
//        InfoWindow fredWilkeInfoWindow = new InfoWindow(infoWindowOptions);
//        fredWilkeInfoWindow.open(map, SerresMarker);
    }

    public void showMarkers(List<LatLong> coordinates){
        for (LatLong coordinate : coordinates){
            MarkerOptions markerOptions1 = new MarkerOptions();
            markerOptions1.position(coordinate);
            map.addMarker(new Marker(markerOptions1));
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mapView.addMapInializedListener(this);
    }


    public void initialize() {
        Controllers.setMainController(this);

//        anchorPane.setOnMouseClicked(event -> {
//            System.out.println("pressed");
//        });
//        GeoApiContext context = new GeoApiContext.Builder()
//                .apiKey("AIzaSyBbPpzHzennC1dYKkhXf6vl3cWEZ1DDGrM")
//                .build();
//        GeocodingResult[] results = new GeocodingResult[0];
//        try {
//            results = GeocodingApi.geocode(context,
//                    "1600 Amphitheatre Parkway Mountain View, CA 94043").await();
//        } catch (ApiException | IOException | InterruptedException e) {
//            e.printStackTrace();
//        }
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        System.out.println(gson.toJson(results[0].formattedAddress));


    }


}

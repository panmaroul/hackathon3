package controllers;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.*;
import com.lynden.gmapsfx.shapes.Polyline;
import com.lynden.gmapsfx.shapes.PolylineOptions;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.LocalAttribute;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import other.Controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static com.sun.org.apache.xalan.internal.lib.ExsltStrings.split;

public class MainController implements Initializable, MapComponentInitializedListener {


    public AnchorPane anchorPane;
    public GoogleMapView mapView;
    private GoogleMap map;
    private static Stage stage;


    @Override
    public void mapInitialized() {
        List<LatLong> coordinates = new ArrayList<>();

        LatLong serres = new LatLong(41.092083, 23.541016);
        LatLong provatas = new LatLong(41.068238, 23.390686);
        LatLong ano_Kamila = new LatLong(41.058320, 23.424134);
        LatLong katw_Kamila = new LatLong(41.020431, 23.483293);
        LatLong katw_Mitrousi = new LatLong(41.058680, 23.457547);
        LatLong koumaria = new LatLong(41.016434, 23.434656);
        LatLong skoutari = new LatLong(41.020032, 23.520701);
        LatLong adelfiko = new LatLong(41.014645, 23.457354);
        LatLong agia_Eleni = new LatLong(41.003545, 23.559196);
        LatLong peponia = new LatLong(40.988154, 23.516756);
        coordinates.add(serres);
        coordinates.add(provatas);
        coordinates.add(ano_Kamila);
        coordinates.add(katw_Kamila);
        coordinates.add(katw_Mitrousi);
        coordinates.add(koumaria);
        coordinates.add(skoutari);
        coordinates.add(adelfiko);
        coordinates.add(agia_Eleni);
        coordinates.add(peponia);

        //Set the initial properties of the map.
        MapOptions mapOptions = new MapOptions();

        mapOptions.center(serres)
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
//        infoWindowOptions.content("<h2>serres</h2>"
//                + "Current Location: Safeway<br>"
//                + "ETA: 45 minutes" );
//
//        InfoWindow fredWilkeInfoWindow = new InfoWindow(infoWindowOptions);
//        fredWilkeInfoWindow.open(map, SerresMarker);
        MVCArray pathArray = new MVCArray();
        pathArray.push(serres);
        pathArray.push(provatas);
        map.addMapShape(new Polyline(new PolylineOptions().path(pathArray).strokeColor("#fc4c02")));

    }

    private void showMarkers(List<LatLong> coordinates) {
        for (LatLong coordinate : coordinates) {
            MarkerOptions markerOptions1 = new MarkerOptions();
            markerOptions1.position(coordinate);
            map.addMarker(new Marker(markerOptions1));
        }
    }

    public void selectFile() {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);
        try (Stream<String> stream = Files.lines(Paths.get(file.getCanonicalPath()))) {

            stream.forEach(s -> {
                String[] parts = s.split(" ");
                //example input 60,20 50,21 500
                //origins Latlong [space] destination Latlong [space] distance double
                String[] originLatLongString = parts[0].split(",");
                String[] destinationLatLongString = parts[1].split(",");
                LatLong originLatLong = new LatLong(Double.parseDouble(originLatLongString[0]),
                        Double.parseDouble(originLatLongString[1]));
                LatLong destinationLatLong = new LatLong(Double.parseDouble(destinationLatLongString[0]),
                        Double.parseDouble(destinationLatLongString[1]));
                double distance = Double.parseDouble(parts[3]);
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mapView.addMapInializedListener(this);
    }


    public void initialize() {
        Controllers.setMainController(this);
    }

    public static void setStage(Stage stage) {
        MainController.stage = stage;
    }
}

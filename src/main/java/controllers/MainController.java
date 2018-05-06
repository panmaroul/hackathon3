package controllers;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.*;
import com.lynden.gmapsfx.shapes.Polyline;
import com.lynden.gmapsfx.shapes.PolylineOptions;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Pair;
import other.Algorithm;
import other.Controllers;
import model.Village;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;


public class MainController implements Initializable, MapComponentInitializedListener {


    public AnchorPane anchorPane;
    public GoogleMapView mapView;
    public Button chooseFileButton;
    private GoogleMap map;
    private static Stage stage;
    private static Map<Pair,Double> connections = new HashMap<>();


    @Override
    public void mapInitialized() {

        Algorithm.test();

        Controllers.setMainController(this);
        chooseFileButton.setOnAction(event -> selectFile());

        List<LatLong> coordinates = new ArrayList<>();

        LatLong serres = new LatLong(41.092083, 23.541016);
//        LatLong provatas = new LatLong(41.068238, 23.390686);
//        LatLong ano_Kamila = new LatLong(41.058320, 23.424134);
//        LatLong katw_Kamila = new LatLong(41.020431, 23.483293);
//        LatLong katw_Mitrousi = new LatLong(41.058680, 23.457547);
//        LatLong koumaria = new LatLong(41.016434, 23.434656);
//        LatLong skoutari = new LatLong(41.020032, 23.520701);
//        LatLong adelfiko = new LatLong(41.014645, 23.457354);
//        LatLong agia_Eleni = new LatLong(41.003545, 23.559196);
//        LatLong peponia = new LatLong(40.988154, 23.516756);
//
//        Village serresVillage = new Village("serres",serres);
//        Village provatasVillage = new Village("provatas",provatas);
//        Village anoKamilaVillage = new Village("ano_kamila",ano_Kamila);
//        Village katwKamilaVillage = new Village("katw_kamila",katw_Kamila);
//        Village koumariaVillage = new Village("koumaria",koumaria);
//        Village skoutariVillage = new Village("skoutari",skoutari);
//        Village agiaEleniVillage = new Village("agia_eleni",agia_Eleni);
//        Village peoponiaVillage = new Village("peponia",peponia);
//        Village adelfikoVillage = new Village("adelfiko",adelfiko);

//        connections.put(new Pair("serres","provatas"),serres.distanceFrom(provatas));
//        connections.put(new Pair("serres","katw_mitrousi"),serres.distanceFrom(katw_Mitrousi));
//        connections.put(new Pair("serres","skoutari"),serres.distanceFrom(skoutari));
//        connections.put(new Pair("provatas","anw_kamila"),provatas.distanceFrom(ano_Kamila));
//        connections.put(new Pair("katw_mitrousi","anw_kamila"),katw_Mitrousi.distanceFrom(ano_Kamila));
//        connections.put(new Pair("katw_mitrousi","katw_kamila"),katw_Mitrousi.distanceFrom(katw_Kamila));
//        connections.put(new Pair("skoutari","katw_kamila"),skoutari.distanceFrom(katw_Kamila));
//        connections.put(new Pair("skoutari","agia_eleni"),skoutari.distanceFrom(agia_Eleni));
//        connections.put(new Pair("skoutari","peponia"),skoutari.distanceFrom(peponia));
//        connections.put(new Pair("agia_eleni","peponia"),agia_Eleni.distanceFrom(peponia));
//        connections.put(new Pair("peponia","adelfiko"),peponia.distanceFrom(adelfiko));
//        connections.put(new Pair("koumaria","adelfiko"),koumaria.distanceFrom(adelfiko));
//        connections.put(new Pair("anw_kamila","koumaria"),ano_Kamila.distanceFrom(koumaria));
//        connections.put(new Pair("katw_kamila","koumaria"),katw_Kamila.distanceFrom(koumaria));
//        connections.put(new Pair("katw_mitrousi","koumaria"),katw_Mitrousi.distanceFrom(koumaria));

//        coordinates.add(serres);
//        coordinates.add(provatas);
//        coordinates.add(ano_Kamila);
//        coordinates.add(katw_Kamila);
//        coordinates.add(katw_Mitrousi);
//        coordinates.add(koumaria);
//        coordinates.add(skoutari);
//        coordinates.add(adelfiko);
//        coordinates.add(agia_Eleni);
//        coordinates.add(peponia);

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
//        showMarkers(coordinates);
        //Add markers to the map

//        InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
//        infoWindowOptions.content("<h2>serres</h2>"
//                + "Current Location: Safeway<br>"
//                + "ETA: 45 minutes" );
//
//        InfoWindow fredWilkeInfoWindow = new InfoWindow(infoWindowOptions);
//        fredWilkeInfoWindow.open(map, SerresMarker);
//        MVCArray pathArray = new MVCArray();
//        pathArray.push(serres);
//        pathArray.push(provatas);
//        map.addMapShape(new Polyline(new PolylineOptions().path(pathArray).strokeColor("#fc4c02")));

        //Algorithm.test();
    }

    private void showMarkers(List<LatLong> coordinates) {
        System.out.println(coordinates.size());
        for (LatLong coordinate : coordinates) {
            showMarker(coordinate);
        }
    }
    private void showMarker(LatLong location){
        MarkerOptions markerOptions1 = new MarkerOptions();
        markerOptions1.position(location);
        map.addMarker(new Marker(markerOptions1));
    }

    private void selectFile() {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);
        if (file == null) return;

        try (Stream<String> stream = Files.lines(Paths.get(file.getCanonicalPath()))) {
            stream.forEach(line -> {
                String[] lineNodes = line.split(";");
                String[] originTokens = lineNodes[0].split(":");
                String originCoordinates = originTokens[1].replaceAll("<","").replaceAll(">","");
                String [] orLatLong = originCoordinates.split(",");
                LatLong originLatLong = new LatLong(Double.parseDouble(orLatLong[0]),
                        Double.parseDouble(orLatLong[1]));
                String originName = originTokens[0];

                String[] destinationTokens = lineNodes[1].split(":");
                String destinationCoordinates = destinationTokens[1].replaceAll("<","").replaceAll(">","");
                String[] destLatLong = destinationCoordinates.split(",");
                String destName = destinationTokens[0];



                LatLong destinationLatLong = new LatLong(Double.parseDouble(destLatLong[0]),
                        Double.parseDouble(destLatLong[1]));
                //right now does nothing
                String[] distanceToken = lineNodes[2].split(":");
                String distance = distanceToken[1].replaceAll("<","").replaceAll(">","");
                double dist = Double.parseDouble(distance);

                connections.put(new Pair(originName,destName),dist);
                //TODO: remove later
                System.out.println(originLatLong.toString()+" "+ destinationLatLong.toString()+" "+dist);
                showMarker(destinationLatLong);
                showMarker(originLatLong);
                addPolyline(originLatLong,destinationLatLong);
            });



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void addPolyline(LatLong origin, LatLong destination){
        MVCArray pathArray = new MVCArray();
        pathArray.push(origin);
        pathArray.push(destination);
        map.addMapShape(new Polyline(new PolylineOptions().path(pathArray).strokeColor("#fc4c02")));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mapView.addMapInializedListener(this);
    }


    public void initialize() {

    }

    public static void setStage(Stage stage) {
        MainController.stage = stage;
    }
}

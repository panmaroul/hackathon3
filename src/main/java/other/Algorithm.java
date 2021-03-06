package other;


import com.graphhopper.jsprit.core.algorithm.VehicleRoutingAlgorithm;
import com.graphhopper.jsprit.core.algorithm.box.Jsprit;
import com.graphhopper.jsprit.core.algorithm.state.StateManager;
import com.graphhopper.jsprit.core.problem.Location;
import com.graphhopper.jsprit.core.problem.VehicleRoutingProblem;
import com.graphhopper.jsprit.core.problem.constraint.ConstraintManager;
import com.graphhopper.jsprit.core.problem.constraint.HardActivityConstraint;
import com.graphhopper.jsprit.core.problem.constraint.HardRouteConstraint;
import com.graphhopper.jsprit.core.problem.job.Service;
import com.graphhopper.jsprit.core.problem.misc.JobInsertionContext;
import com.graphhopper.jsprit.core.problem.solution.VehicleRoutingProblemSolution;
import com.graphhopper.jsprit.core.problem.solution.route.activity.TourActivity;
import com.graphhopper.jsprit.core.problem.vehicle.VehicleImpl;
import com.graphhopper.jsprit.core.problem.vehicle.VehicleType;
import com.graphhopper.jsprit.core.problem.vehicle.VehicleTypeImpl;
import com.graphhopper.jsprit.core.reporting.SolutionPrinter;
import com.graphhopper.jsprit.core.util.Coordinate;
import com.graphhopper.jsprit.core.util.Solutions;
import com.lynden.gmapsfx.javascript.object.LatLong;
import sun.misc.Contended;

import java.util.Arrays;
import java.util.Collection;

public class Algorithm {

    static LatLong serres = new LatLong(41.092083, 23.541016);
    static LatLong provatas = new LatLong(41.068238, 23.390686);
    static LatLong ano_Kamila = new LatLong(41.058320, 23.424134);
    static LatLong katw_Kamila = new LatLong(41.020431, 23.483293);
    static LatLong katw_Mitrous = new LatLong(41.058680, 23.457547);
    static LatLong koumaria = new LatLong(41.016434, 23.434656);
    static LatLong skoutari = new LatLong(41.020032, 23.520701);
    static LatLong adelfiko = new LatLong(41.014645, 23.457354);
    static LatLong agia_Eleni	 = new LatLong(41.003545, 23.559196);
    static LatLong peponia = new LatLong(40.988154, 23.516756);

    public static void test(){

        final int WEIGHT_INDEX = 0;
        VehicleTypeImpl.Builder vehicleTypeBuilder = VehicleTypeImpl.Builder.newInstance("vehicleType").addCapacityDimension(WEIGHT_INDEX,8);
        VehicleType vehicleType = vehicleTypeBuilder.build();

        VehicleImpl.Builder vehicleBuilder = VehicleImpl.Builder.newInstance("vehicle");
        vehicleBuilder.setStartLocation(Location.newInstance(serres.getLatitude(),serres.getLongitude()));
        vehicleBuilder.setEndLocation(Location.newInstance(adelfiko.getLatitude(),adelfiko.getLongitude()));
        vehicleBuilder.setType(vehicleType);
        VehicleImpl vehicle = vehicleBuilder.build();

        Service service1 = Service.Builder.newInstance("provatas").addSizeDimension(WEIGHT_INDEX,1).
                setLocation(Location.newInstance(provatas.getLatitude(),provatas.getLongitude())).build();

        Service service2 = Service.Builder.newInstance("ano_Kamila").addSizeDimension(WEIGHT_INDEX,1).
                setLocation(Location.newInstance(ano_Kamila.getLatitude(),ano_Kamila.getLongitude())).build();

        Service service3 = Service.Builder.newInstance("skoutari").addSizeDimension(WEIGHT_INDEX,1).
                setLocation(Location.newInstance(skoutari.getLatitude(),skoutari.getLongitude())).build();

        Service service4_1 = Service.Builder.newInstance("peponia").addSizeDimension(WEIGHT_INDEX,1).
                setLocation(Location.Builder.newInstance().setCoordinate(new Coordinate(peponia.getLatitude(),peponia.getLongitude())).setId("4").build()).build();

        Service service5_1 = Service.Builder.newInstance("agia_Eleni").addSizeDimension(WEIGHT_INDEX,1).
                setLocation(Location.Builder.newInstance().setCoordinate(new Coordinate(agia_Eleni.getLatitude(),agia_Eleni.getLongitude())).setId("5").build()).build();

        Service service6 = Service.Builder.newInstance("katw_Kamila").addSizeDimension(WEIGHT_INDEX,1).
                setLocation(Location.newInstance(katw_Kamila.getLatitude(),katw_Kamila.getLongitude())).build();

        Service service7 = Service.Builder.newInstance("katw_Mitrous").addSizeDimension(WEIGHT_INDEX,1).
                setLocation(Location.newInstance(katw_Mitrous.getLatitude(),katw_Mitrous.getLongitude())).build();

        Service service8 = Service.Builder.newInstance("koumaria").addSizeDimension(WEIGHT_INDEX,1).
                setLocation(Location.newInstance(koumaria.getLatitude(),koumaria.getLongitude())).build();

        HardActivityConstraint constraint = (iFacts, prevAct, newAct, nextAct, prevActDepTime) -> {
            if (newAct.getLocation().getId().equals("4") && prevAct.getLocation().getId().equals("5")){
                return HardActivityConstraint.ConstraintsStatus.NOT_FULFILLED_BREAK;
            }
            return HardActivityConstraint.ConstraintsStatus.FULFILLED;
        };

        VehicleRoutingProblem.Builder vrpBuilder = VehicleRoutingProblem.Builder.newInstance();
        vrpBuilder.addVehicle(vehicle);
        vrpBuilder.addAllJobs(Arrays.asList(service1,service2,service3,service4_1,service5_1,service6,service7,service8));

        VehicleRoutingProblem problem = vrpBuilder.build();

        StateManager stateManager = new StateManager(problem);
        ConstraintManager constraintManager = new ConstraintManager(problem, stateManager);
        constraintManager.addConstraint(constraint,ConstraintManager.Priority.CRITICAL);

        VehicleRoutingAlgorithm algorithm = Jsprit.Builder.newInstance(problem).setStateAndConstraintManager(stateManager,constraintManager).buildAlgorithm();
        Collection<VehicleRoutingProblemSolution> solutions = algorithm.searchSolutions();

        VehicleRoutingProblemSolution bestSolution = Solutions.bestOf(solutions);
        SolutionPrinter.print(problem, bestSolution, SolutionPrinter.Print.VERBOSE);


        System.out.println("done");
    }


}

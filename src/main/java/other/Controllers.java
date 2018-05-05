package other;

import controllers.MainController;

/**
 * @author Nikiforos Archakis
 *         Date: 11/5/2017
 *         email: nikiarch@teiser.gr
 */

/**
 * Stores access references to all the View Controllers
 */
public class Controllers
{
    private static MainController mainController;

    public static void setMainController(MainController mainController) {
        Controllers.mainController = mainController;
    }

    public static MainController getMainController() {
        return mainController;
    }

}

package ch.esne;

import ch.esne.console.Controller;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Diogo C
 * @version 1.0
 */
@SuppressWarnings("checkstyle:hideutilityclassconstructor")
public class Main {

    /**
     *
     * @param args
     */
    public static void main(final String[] args) throws SQLException, ClassNotFoundException, IOException {
        Controller controller = new Controller(Controller.State.INIT); //Controller en mode initialisation
    }
}

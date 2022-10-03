import org.json.JSONObject;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Connection connection = Connection.getInstance();
        String authKey = connection.start(1);


        JSONParser jsonParser = new JSONParser();
        ArrayList<Location> locations;
        ArrayList<Truck> trucks;

        for (int i = 0; i < 720; i++) {
            locations = jsonParser.getLocations(connection.locations(authKey));
            trucks = jsonParser.getTrucks(connection.trucks(authKey));

            ArrayList<Command> commands = new ArrayList<Command>();
            ArrayList<Integer> action = new ArrayList<Integer>();
            action.add(0);
            Command command = new Command(0, action);
            commands.add(command);
            JSONObject ret = connection.simulate(authKey, commands);
            System.out.println("ret = " + ret);
        }
        float score = connection.score(authKey);

        System.out.println("score = " + score);
    }
}

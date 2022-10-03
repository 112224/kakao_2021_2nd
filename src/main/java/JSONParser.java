import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class JSONParser {
    public ArrayList<Location> getLocations(JSONObject response) {
        ArrayList<Location> locations = new ArrayList<Location>();

        JSONArray jsonArray = response.getJSONArray("locations");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonLocation = jsonArray.getJSONObject(i);
            Location location = new Location(jsonLocation.getInt("id")
                    , jsonLocation.getInt("located_bikes_count"));
            locations.add(location);
        }
        return locations;
    }

    public ArrayList<Truck> getTrucks(JSONObject reponse) {
        ArrayList<Truck> trucks = new ArrayList<Truck>();

        JSONArray jsonArray = reponse.getJSONArray("trucks");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonTruck = jsonArray.getJSONObject(i);
            Truck truck = new Truck(jsonTruck.getInt("id")
                    , jsonTruck.getInt("location_id")
                    , jsonTruck.getInt("loaded_bikes_count"));
            trucks.add(truck);
        }
        return trucks;
    }
}

public class Truck {
    private int id;
    private int locationId;
    private int locatedBikesCount;

    public Truck(int id, int locationId, int locatedBikesCount) {
        this.id = id;
        this.locationId = locationId;
        this.locatedBikesCount = locatedBikesCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public int getParkedBikeCnt() {
        return locatedBikesCount;
    }

    public void setParkedBikeCnt(int locatedBikesCount) {
        this.locatedBikesCount = locatedBikesCount;
    }
}

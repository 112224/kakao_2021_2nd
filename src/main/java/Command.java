import java.util.ArrayList;

public class Command {
    private int truckId;
    private ArrayList<Integer> command;

    public Command(int truckId, ArrayList<Integer> command) {
        this.truckId = truckId;
        this.command = command;
    }

    public int getTruckId() {
        return truckId;
    }

    public void setTruckId(int truckId) {
        this.truckId = truckId;
    }

    public ArrayList<Integer> getCommand() {
        return command;
    }

    public void setCommand(ArrayList<Integer> command) {
        this.command = command;
    }
}

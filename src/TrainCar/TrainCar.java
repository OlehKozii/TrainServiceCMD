package TrainCar;
import Passenger.Passenger;
import java.util.ArrayList;

public class TrainCar {
    public ArrayList<Passenger> passengers = new ArrayList<Passenger>();
    public int MaxPassengers;
    protected int CarClass;
    protected int Number;
    protected String ConductorName;

    TrainCar(int Number , String ConductorName) {
        this.Number = Number;
        this.ConductorName = ConductorName;
    }

    public void showInfo() {
        System.out.println("№" + this.Number + "\t" + this.ConductorName + "\t" + this.CarClass + " class\t" + this.passengers.size() + "/" + this.MaxPassengers);
    }

    public void addPassenger(String Name, double LuggageKg, int Seat) {
        if(Seat > this.MaxPassengers || Seat < 1) {
            System.out.println("No such seat");
            return;
        }
        if(this.passengers.size() < this.MaxPassengers) {
            this.passengers.add(new Passenger(Name, LuggageKg, Seat));
        } else {
            System.out.println("No free seats");
        }
    }

    public void removePassenger(int Seat) {
        for(int i = 0; i < this.passengers.size(); i++) {
            if(this.passengers.get(i).getSeat() == Seat) {
                this.passengers.remove(i);
                System.out.println("Passenger.Passenger removed");
                return;
            }
        }
        System.out.println("No such passenger");
    }

    public void showPassengers() {
        System.out.println("\nPassengers of car №"+this.Number+":");
        for(int i = 0; i < this.passengers.size(); i++) {
            System.out.println(this.passengers.get(i).toString());
        }
    }


    public double getTotalLuggage() {
        double totalLuggage = 0;
        for(int i = 0; i < this.passengers.size(); i++) {
            totalLuggage += this.passengers.get(i).getLuggageKg();
        }
        return totalLuggage;
    }

    public int getNumber() {
        return this.Number;
    }
}


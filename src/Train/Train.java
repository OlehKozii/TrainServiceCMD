package Train;
import TrainCar.*;
import java.util.ArrayList;

public class Train {
    public ArrayList<TrainCar> cars = new ArrayList<TrainCar>();
    private String From;
    private String To;
    private int Number;

    public Train(String From , String To , int Number) {
        this.From = From;
        this.To = To;
        this.Number = Number;
    }

    public int getNumber() {
        return this.Number;
    }

    public TrainCar getCar(int Number) {
        for(int i = 0; i < this.cars.size(); i++) {
            if(this.cars.get(i).getNumber() == Number) {
                return this.cars.get(i);
            }
        }
        System.out.println("No such car");
        return null;
    }

    public void showCars(){
        for(int i = 0; i < this.cars.size(); i++) {
            this.cars.get(i).showInfo();
        }
    }

    public void showCarsByPassangersRange(int min, int max) {
        System.out.println();
        for(int i = 0; i < this.cars.size(); i++) {
            if(this.cars.get(i).passengers.size() >= min && this.cars.get(i).passengers.size() <= max) {
                System.out.println(this.cars.get(i).toString());
            }
        }
    }
    

    public void showShortInfo() {
        System.out.println("№" + this.Number + "\t" + this.From + " - " + this.To);
    }
    public void showInfo() {
        System.out.println("№" + this.Number + "\t" + this.From + " - " + this.To + "\nCars available: " + this.cars.size());
        showSummary();
    }

    public void addCar(int confClass, int Number, String ConductorName) {
        for(int i = 0; i < this.cars.size(); i++) {
            if(this.cars.get(i).getNumber() == Number) {
                System.out.println("Car with this number already exists");
                return;
            }
        }
        if(confClass == 1) {
            this.cars.add(new FirstClassCar(Number, ConductorName));
        } else if(confClass == 2) {
            this.cars.add(new SecondClassCar(Number, ConductorName));
        } else if(confClass == 3) {
            this.cars.add(new ThirdClassCar(Number, ConductorName));
        }
    }

    public void removeCar(int Number) {
        this.cars.remove(this.getCar(Number));
    }

    public void sortCars(){
        for(int i = 0; i < this.cars.size(); i++) {
            for(int j = 0; j < this.cars.size(); j++) {
                if(this.cars.get(i).MaxPassengers < this.cars.get(j).MaxPassengers ||  (this.cars.get(i).MaxPassengers == this.cars.get(j).MaxPassengers && this.cars.get(i).passengers.size() < this.cars.get(j).passengers.size())) {
                    TrainCar temp = this.cars.get(i);
                    this.cars.set(i, this.cars.get(j));
                    this.cars.set(j, temp);
                }
            }
        }
    }


    public void showSummary() {
        double totalLuggage = 0;
        int totalPassengers = 0;
        int maxPassengers = 0;
        for(int i = 0; i < this.cars.size(); i++) {
            maxPassengers += this.cars.get(i).MaxPassengers;
            totalLuggage += this.cars.get(i).getTotalLuggage();
            totalPassengers += this.cars.get(i).passengers.size();
        }
        System.out.println("Total passengers: " + totalPassengers+"/"+maxPassengers);
        System.out.println("Total luggage: " + totalLuggage+"kg");
    }

    public ArrayList<TrainCar> getCars(){
        return this.cars;
    }
}

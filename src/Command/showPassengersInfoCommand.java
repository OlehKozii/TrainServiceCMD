package Command;

import TrainCar.TrainCar;

public class showPassengersInfoCommand implements Command {
    private TrainCar car;

    public showPassengersInfoCommand(TrainCar car) {
        this.car = car;
    }

    public void execute() {
        car.showPassengers();
    }
}

package Command;

import TrainCar.TrainCar;

public class showCarInfoCommand implements Command {
    private TrainCar car;

    public showCarInfoCommand(TrainCar car) {
        this.car = car;
    }

    public void execute() {
        car.showInfo();
    }
}

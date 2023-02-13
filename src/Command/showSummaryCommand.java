package Command;

import Train.Train;
import TrainCar.TrainCar;

public class showSummaryCommand implements Command {
    private Train train;
    private TrainCar car;

    public showSummaryCommand(Train train) {
        this.train = train;
    }

    public void execute() {
        train.showInfo();
    }
}

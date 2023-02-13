import Command.*;
import Train.Train;

import java.util.*;

import TrainCar.*;

/*Транспорт. Визначити ієрархію рухомого складу залізничного транспорту.
Створити пасажирський потяг. Підрахувати загальну кількість пасажирів та багажу.
Здійснити cортування вагонів поїзда на основі рівня комфортності.
Знайти вагони в поїзді, що відповідають заданому діапазону параметрів кількості пасажирів.
*/

public class Main {
    static boolean isNumber(String str) {
        if (str == null || str.isEmpty()) return false;
        for (int i = 0; i < str.length(); i++) {
            if (! Character.isDigit(str.charAt(i))) return false;
        }
        return true;
    }

    static void addTrain(ArrayList<Train> trains) {
        String trainFrom, trainTo, trainNumber;
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter train number");
        while (true) {
            trainNumber = myObj.nextLine();
            if (! isNumber(trainNumber)) {
                System.out.println(trainNumber + " is not a valid input");
                return;
            }
            for (int i = 0; i < trains.size(); i++) {
                if (trains.get(i).getNumber() == Integer.parseInt(trainNumber)) {
                    System.out.println("Train.Train with this number already exists");
                    return;
                }
            }
            break;
        }
        System.out.println("Enter train from");
        while (true) {
            trainFrom = myObj.nextLine();
            break;
        }

        System.out.println("Enter train to");
        trainTo = myObj.nextLine();
        trains.add(new Train(trainFrom, trainTo, Integer.parseInt(trainNumber)));
    }

    static void addCar(Train train, user myUser) {
        int confClass, carNumber;
        String conductorName;
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter car number");
        carNumber = myObj.nextInt();

        System.out.println("Enter car class");
        confClass = myObj.nextInt();

        System.out.println("Enter conductor name");
        while (true) {
            conductorName = myObj.nextLine();
            if (conductorName != "") {
                break;
            }
        }
        myUser.setCommand(new addCarCommand(train, confClass, carNumber, conductorName));
        myUser.executeCommand();
    }


    static void trainsMenu(ArrayList<Train> trains) {
        while (true) {
            System.out.println("\nWelcome to the trains menu");
            for (int i = 0; i < trains.size(); i++) {
                System.out.print(i + 1 + ". ");
                trains.get(i).showShortInfo();
            }
            System.out.println(trains.size() + 1 + ". Add train\n" + (trains.size() + 2) + ". Return to main menu");
            String choice;
            Scanner myObj = new Scanner(System.in);
            choice = myObj.nextLine();
            if (! isNumber(choice) || Integer.parseInt(choice) < 0 || Integer.parseInt(choice) > (trains.size() + 2)) {
                System.out.println("Wrong input");
                continue;
            }
            if (Integer.parseInt(choice) == (trains.size() + 1)) {
                addTrain(trains);
            } else if (Integer.parseInt(choice) == (trains.size() + 2)) {
                break;
            } else {
                carsMenu(trains, trains.get(Integer.parseInt(choice) - 1));
            }
        }
    }

    static void carsMenu(ArrayList<Train> trains, Train train) {
        while (true) {
            user myUser = new user();
            System.out.println("\nWelcome to the train №" + train.getNumber() + " menu\n1. Show train cars\n" +
                    "2. Add train car\n3. Show train cars by range\n4. Sort train cars\n5. Show info about train\n6. Remove train\n7. Return to trains menu");
            String choice;
            Scanner myObj = new Scanner(System.in);
            choice = myObj.nextLine();
            switch (choice) {
                case "1":
                    CarList(train, myUser);
                    break;
                case "2":
                    addCar(train, myUser);
                    break;
                case "3":
                    int min, max;
                    System.out.println("Enter min");
                    min = myObj.nextInt();
                    System.out.println("Enter max");
                    max = myObj.nextInt();
                    myUser.setCommand(new showCarsByPassangersRangeCommand(train, min, max));
                    myUser.executeCommand();
                    break;
                case "4":
                    myUser.setCommand(new sortCarsCommand(train));
                    myUser.executeCommand();
                    break;
                case "5":
                    myUser.setCommand(new showSummaryCommand(train));
                    myUser.executeCommand();
                    break;
                case "6":
                    trains.remove(train);
                case "7":
                    return;
                default:
                    System.out.println("Wrong input");
                    break;
            }
        }
    }

    static void CarList(Train train, user myUser) {
        System.out.println("\nList of cars");
        String choice;
        Scanner myObj = new Scanner(System.in);
        myUser.setCommand(new showCarsCommand(train));
        myUser.executeCommand();
        System.out.println(train.getCars().size() + 1 + ". Return to main menu");
        choice = myObj.nextLine();
        if (! isNumber(choice) || Integer.parseInt(choice) < 0 || Integer.parseInt(choice) > (train.getCars().size() + 1)) {
            System.out.println("Wrong input");
            return;
        }
        if (Integer.parseInt(choice) == (train.getCars().size() + 1)) {
            return;
        } else {
            CarMenu(train, train.getCars().get(Integer.parseInt(choice) - 1), myUser);
        }
    }

    static void CarMenu(Train train, TrainCar car, user myUser) {
        while (true) {
            System.out.println("\nWelcome to the car №" + car.getNumber() + " menu\n1. Show car info\n" +
                    "2. Add passenger\n3. Remove passenger\n4. Show passengers\n" +
                    "5. Remove car\n6. Return to cars menu");
            String choice;
            Scanner myObj = new Scanner(System.in);
            choice = myObj.nextLine();
            String name;
            int seat;
            double luggage;
            switch (choice) {
                case "1":
                    myUser.setCommand(new showCarInfoCommand(car));
                    myUser.executeCommand();
                    break;
                case "2":
                    System.out.println("Enter passenger name");
                    name = myObj.nextLine();
                    System.out.println("Enter passenger seat number");
                    seat = myObj.nextInt();
                    System.out.println("Enter passenger luggage in kg");
                    luggage = myObj.nextDouble();
                    myUser.setCommand(new addPassengerCommand(car, name, luggage, seat));
                    myUser.executeCommand();
                    break;
                case "3":
                    System.out.println("Enter passenger seat number");
                    seat = myObj.nextInt();
                    myUser.setCommand(new removePassengerCommand(car, seat));
                    myUser.executeCommand();
                    break;
                case "4":
                    myUser.setCommand(new showPassengersInfoCommand(car));
                    myUser.executeCommand();
                    break;
                case "5":
                    myUser.setCommand(new removeCarCommand(train, car.getNumber()));
                    myUser.executeCommand();
                case "6":
                    return;
                default:
                    System.out.println("Wrong input");
                    break;
            }
        }
    }

    static void showFAQ() {
        System.out.println("F.A.Q");
    }

    public static void main(String[] args) {
        ArrayList<Train> arrTrain = new ArrayList<Train>();
        arrTrain.add(new Train("Lviv", "Ternopil", 86));
        String choice;
        Scanner myObj = new Scanner(System.in);
        while (true) {
            System.out.print("\nWelcome to the menu" + "\n1. Show trains\n2. F.A.Q\n3. Exit\nYour choice is ");
            choice = myObj.nextLine();
            switch (choice) {
                case "1":
                    trainsMenu(arrTrain);
                    break;
                case "2":
                    showFAQ();
                    break;
                case "3":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Wrong input");
                    break;
            }
        }
    }
}
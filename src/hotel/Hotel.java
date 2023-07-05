/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hotel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import hotel.Room.RoomType;
import hotel.Staff.JobTitle;

import java.util.Collections;
import java.util.HashMap;

/**
 *
 * @author ahmed
 */
public class Hotel {

    private static Hotel instance;
    private List<Room> availableRooms;
    private List<Room> bookedRooms;
    private RoomAvailabilityTask availabilityTask; // The background task
    private List<Manager> managers;
    private List<Employee> employees;
    private static HashMap<String, HashMap<Float, Integer>> offers;

    private Hotel() {
        availableRooms = new ArrayList<>();
        bookedRooms = new ArrayList<>();
        availabilityTask = new RoomAvailabilityTask(bookedRooms);
        managers = new ArrayList<>();
        employees = new ArrayList<>();
        offers = new HashMap<>();
    }

    public static Hotel getInstance() {
        if (instance == null) {
            instance = new Hotel();
        }
        return instance;
    }

    public void startBackgroundTask() {
        // Create a scheduled executor service with a single thread
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

        // Schedule the availability task to run periodically every second
        executor.scheduleAtFixedRate(availabilityTask, 0, 12, TimeUnit.HOURS);
    }

    public HashMap<String, HashMap<Float, Integer>> getOffers() {
        return offers;
    }

    public List<Room> getAvailableRooms() {
        return availableRooms;
    }

    public boolean isCEOAdded() {
        for (Manager manager : managers) {
            if (manager.jobTitle == JobTitle.CEO) {
                return true;
            }
        }
        return false;
    }

    public void addManager(Manager manager) {
        if (manager.jobTitle == JobTitle.CEO && isCEOAdded()) {
            System.err.println("There can only be one CEO.");
        } else {
            managers.add(manager);
        }
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void addAvailableRoom(Room room) {
        availableRooms.add(room);
        room.setIsAvailable(true);
    }

    public List<Room> getBookedRooms() {
        return bookedRooms;
    }

    public List<Manager> getManagers() {
        return managers;
    }

    // function to diplsay all managers
    public void displayManagers() {
        for (Manager manager : managers) {
            System.out.println(manager.toString());
        }
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    // function to display all employees
    public void displayEmployees() {
        for (Employee employee : employees) {
            System.out.println(employee.toString());
        }
    }

    public void removeAvailableRoom(Room room) {
        availableRooms.remove(room);
        room.setIsAvailable(false);
    }

    public boolean isRoomAvailable(RoomType roomType) {
        for (Room room : availableRooms) {
            if (room.getRoomType() == roomType) {
                room.setIsAvailable(true);
                return room.getIsAvailable();
            }
        }
        return false;
    }

    public void addBookedRoom(Room room) {
        bookedRooms.add(room);

        Collections.sort(bookedRooms,
                (Room obj1, Room obj2) -> obj1.getCheckOutDate().compareTo(obj2.getCheckOutDate()));

        for (Room obj : bookedRooms) {
            // print to show how the program works
            System.out.println(obj.toString());
            obj.toString();
        }
    }

    public synchronized Room getAvailableRoomByType(RoomType roomType) {
        for (Room room : availableRooms) {
            if (room.getRoomType() == roomType) {
                return room;
            }
        }
        return null;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Hotel hotel = Hotel.getInstance();
        hotel.startBackgroundTask();

        Manager M1 = new Manager(500, 5000, 200, JobTitle.CEO, "2023", ""
                + "Ali", "ALX", "0123456789",
                "ahmedmang@gmail.com", "23");

        Manager M2 = new Manager(500, 5000, 200, JobTitle.GENERAL_MANAGER, "2023", ""
                + "Osama", "ALX", "0123456789",
                "Osama_mang@gmail.com", "23");

        Manager M3 = new Manager(500, 5000, 200, JobTitle.FRONT_DESK_MANAGER, "2023", ""
                + "Osama", "ALX", "0123456789",
                "Osama_mang@gmail.com", "23");

        hotel.addManager(M1);
        hotel.addManager(M2);
        hotel.addManager(M3);

        hotel.addManager(new Manager(500, 5000, 200, JobTitle.CEO, "2023", "Ahmed", "Cairo", "0123456789",
                "ahmedmang@gmail.com", "23"));
        hotel.addManager(new Manager(500, 5000, 200, JobTitle.GENERAL_MANAGER, "2023", "Shahd", "ALX", "0123456789",
                "ahmedmang@gmail.com", "23"));
        hotel.addManager(new Manager(500, 5000, 200, JobTitle.GENERAL_MANAGER, "2023", "Raghad", "ALX",
                "0123456789", "ahmedmang@gmail.com", "23"));
        hotel.addManager(new Manager(500, 5000, 200, JobTitle.FRONT_DESK_MANAGER, "2023", "Saif", "ALX", "0123456789",
                "ahmedmang@gmail.com", "23"));

        hotel.displayManagers();
        System.out.println();

        hotel.addEmployee(new Employee(4000, 100, JobTitle.FRONT_DESK_CLERK, "20231", "Ahmed", "Cairo", "0123456789",
                "Emp_A@outlook", "28"));
        hotel.addEmployee(new Employee(4000, 100, JobTitle.FRONT_DESK_CLERK, "20232", "Nancy", "Cairo", "0123456789",
                "Emp_B@outlook", "26"));
        hotel.addEmployee(new Employee(4000, 100, JobTitle.HOUSEKEEPER, "20233", "Sara", "Cairo", "0123456789",
                "Emp_c@outlook", "23"));
        hotel.addEmployee(new Employee(4000, 100, JobTitle.HOUSEKEEPER, "20234", "Nour", "Cairo", "0123456789",
                "Emp_d@outlook", "18"));
        hotel.addEmployee(new Employee(4000, 100, JobTitle.HOUSEKEEPER, "20235", "Karen", "Cairo", "0123456789",
                "Emp_e@outlook", "19"));
        hotel.addEmployee(new Employee(4000, 100, JobTitle.HOUSEKEEPER, "20236", "Mariam", "Cairo", "0123456789",
                "Emp_f@outlook", "22"));

        Employee E = new Employee(4000, 100, JobTitle.HOUSEKEEPER, "20236", "Farah", "Cairo", "0123456789",
                "Emp_f@outlook", "22");

        System.out.println("\n");
        M2.hireEmployee(E);
        M2.fireEmployee(E);
        M1.promoteEmployeeToManager(E, 500, JobTitle.FRONT_DESK_MANAGER);
        hotel.displayManagers();
        System.out.println("\n");
        hotel.displayEmployees();
        System.out.println("\n");

        float price = 100;
        hotel.addAvailableRoom(new Room("1", RoomType.SINGLE, price));
        hotel.addAvailableRoom(new Room("2", RoomType.DOUBLE, price));
        hotel.addAvailableRoom(new Room("3", RoomType.TRIPLE, price));
        hotel.addAvailableRoom(new Room("4", RoomType.SUITE, price));

        Guest guest1 = new Guest("Ahmed", "Cairo", "0123456789",
                "ahmedalyapany@gmail.com", "20", "Sea view");
        Guest guest2 = new Guest("Mohamed", "Cairo", "0123456789",
                "mohamed@gmail.com", "20", "Sea view", 65,
                LocalDate.of(2023, 2, 2));
        Guest guest3 = new Guest("Ali", "Cairo", "0123456789", "ali@gmail.com", "20",
                "Sea view", 65,
                LocalDate.of(2023, 2, 2));
        Guest guest4 = new Guest("Nono", "Cairo", "0123456789",
                "mohamed@gmail.com", "20", "Sea view", 65,
                LocalDate.of(2023, 2, 2));

        hotel.getAvailableRoomByType(RoomType.SINGLE);

        guest1.setCheckInDate(LocalDate.of(2023, 5, 1));

        guest1.bookRoom(RoomType.SINGLE, guest1.getCheckInDate(), 6);
        guest2.bookRoom(RoomType.SINGLE, guest2.getCheckInDate(),
                guest2.getStayDuration());

        System.out.println(guest3.getAvailableRoomInfo());

        guest2.bookRoom(RoomType.DOUBLE, guest2.getCheckInDate(),
                guest2.getStayDuration());
        guest3.bookRoom(RoomType.TRIPLE, guest3.getCheckInDate(),
                guest2.getStayDuration());
        guest4.bookRoom(RoomType.SUITE, guest4.getCheckInDate(),
                guest2.getStayDuration());

        System.out.println(guest1.toString());
        System.out.println(guest2.toString());
        System.out.println(guest3.toString());
        System.out.println(guest4.toString());

        System.out.println();
        System.out.println();
        M2.addOffer("alyapany", 10, 3);

        System.out.println(guest1.getRoom().getPrice());
        guest1.useCoupon("alyapany", guest1.getRoom());
        System.out.println(guest1.getRoom().getPrice() + "\n");

        System.out.println(guest2.getRoom().getPrice());
        guest2.useCoupon("alyapany", guest2.getRoom());
        System.out.println(guest2.getRoom().getPrice() + "\n");

        System.out.println(guest3.getRoom().getPrice());
        guest3.useCoupon("alyapany", guest3.getRoom());
        System.out.println(guest3.getRoom().getPrice() + "\n");

        System.out.println(guest4.getRoom().getPrice());
        guest4.useCoupon("alyapany", guest4.getRoom());
        System.out.println(guest4.getRoom().getPrice() + "\n");

    }

}

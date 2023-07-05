/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotel;

/**
 *
 * @author ahmed
 */
public class Employee extends Staff {

    public Employee(double baseSalary, double bonus, JobTitle jobTitle, String ID, String name,
            String address, String phone, String email, String age) {
        super(baseSalary, bonus, jobTitle, ID, name, address, phone, email, age);
    }

    public Employee(double baseSalary, double bonus, JobTitle jobTitle, String ID) {
        super(baseSalary, bonus, jobTitle, ID);
    }

    @Override
    public double calculateSalary() {
        // Calculate the salary for an employee (example logic)
        double totalSalary = getBaseSalary() + getBonus();

        switch (jobTitle) {
            case FRONT_DESK_CLERK:
                totalSalary += 200;
                break;
            case HOUSEKEEPER:
                totalSalary += 300;
                break;
            default:
                System.out.println("No additional bonus for this job title");
                break;
        }

        return totalSalary;
    }

    public void checkAvailability() {
        // check availability of rooms

    }

    public void performCleaning(Room room) {
        // perform cleaning of rooms
    }

    @Override
    public String toString() {
        return "Employee{" + "baseSalary : " + getBaseSalary() + ", bonus : " + getBonus() + ", jobTitle : "
                + getJobTitle()
                + ", ID : " + getID() + ", name : " + getName() + ", address : " + getAddress() + ", phone : "
                + getPhone()
                + ", email : " + getEmail() + ", age : " + getAge() + '}';
    }
}
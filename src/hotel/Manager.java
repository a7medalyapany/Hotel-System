/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotel;

import java.util.HashMap;

/**
 *
 * @author ahmed
 */
public class Manager extends Staff {
    private double allowance;
    Hotel hotel = Hotel.getInstance();

    public Manager(double allowance, double baseSalary, double bonus, JobTitle jobTitle, String ID,
            String name, String address, String phone, String email, String age) {
        super(baseSalary, bonus, jobTitle, ID, name, address, phone, email, age);
        this.allowance = allowance;
    }

    public Manager(double allowance, double baseSalary, double bonus, JobTitle jobTitle, String ID) {
        super(baseSalary, bonus, jobTitle, ID);
        this.allowance = allowance;
    }

    public double getAllowance() {
        return allowance;
    }

    public void setAllowance(double allowance) {
        if (allowance >= 0)
            this.allowance = allowance;
        else
            System.out.println("Invalid allowance");
    }

    @Override
    public double calculateSalary() {
        double totalSalary = getBaseSalary() + getBonus() + getAllowance();

        switch (jobTitle) {
            case GENERAL_MANAGER:
                totalSalary += 1000;
                break;
            case FRONT_DESK_MANAGER:
                totalSalary += 500;
                break;
            default:
                System.out.println("No additional bonus for this job title");
                break;
        }

        return totalSalary;
    }

    public void hireEmployee(Employee employee) {
        if (this.jobTitle != JobTitle.GENERAL_MANAGER) {
            throw new IllegalArgumentException("Only the General Manager can hire employees.");
        }
        hotel.getEmployees().add(employee);
        System.out.println("Employee hired successfully by : " + this.getID());
    }

    public void fireEmployee(Employee employee) {
        if (this.jobTitle != JobTitle.GENERAL_MANAGER) {
            throw new IllegalArgumentException("Only the General Manager can fire employees.");
        }
        hotel.getEmployees().remove(employee);
        System.out.println("Employee fired by : " + this.getID());
    }

    public void promoteEmployeeToManager(Employee employee, double allowance, JobTitle jobTitle) {
        if (this.jobTitle != JobTitle.CEO) {
            throw new IllegalArgumentException("Only the CEO can promote employees to managers.");
        }

        hotel.getEmployees().remove(employee);
        // Create a new Manager instance based on the employee's information
        Manager manager = new Manager(allowance, employee.getBaseSalary(), employee.getBonus(),
                jobTitle, employee.getID(), employee.getName(), employee.getAddress(),
                employee.getPhone(), employee.getEmail(), employee.getAge());

        hotel.getManagers().add(manager);
    }

    public void addOffer(String offerCode, float discount, int applicable) {
        if (this.jobTitle != JobTitle.GENERAL_MANAGER) {
            throw new IllegalArgumentException("Only the General Manager can add offer.");
        }
        HashMap<Float, Integer> offerDetails = hotel.getOffers().getOrDefault(offerCode, new HashMap<>());
        offerDetails.put(discount, applicable);
        hotel.getOffers().put(offerCode, offerDetails);
    }

    public void removeOffer(String offerCode) {
        if (this.jobTitle != JobTitle.GENERAL_MANAGER) {
            throw new IllegalArgumentException("Only the General Manager can remove offer.");
        }
        hotel.getOffers().remove(offerCode);
    }

    public void modifyOffer(String offerCode, float newDiscount, int n) {
        if (this.jobTitle != JobTitle.GENERAL_MANAGER) {
            throw new IllegalArgumentException("Only the General Manager can  modify offer.");
        }
        HashMap<Float, Integer> offerDetails = hotel.getOffers().get(offerCode);
        if (offerDetails != null) {
            offerDetails.put(newDiscount, n);
        }
    }

    @Override
    public String toString() {
        return "Manager [allowance : " + getAllowance() + ", baseSalary : " + getBaseSalary() + ", bonus : "
                + getBonus()
                + ", jobTitle : " + getJobTitle() + ", ID : " + getID() + ", name : " + getName() + ", address : "
                + getAddress()
                + ", phone : " + getPhone() + ", email : " + getEmail() + ", age : " + getAge() + "]";
    }

}

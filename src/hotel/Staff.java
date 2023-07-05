/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotel;

import java.text.DecimalFormat;

/**
 *
 * @author ahmed
 */

public abstract class Staff extends Person {
    protected double baseSalary;
    protected double bonus;
    protected JobTitle jobTitle;
    protected String ID;

    public enum JobTitle {
        CEO,
        GENERAL_MANAGER,
        FRONT_DESK_MANAGER,
        FRONT_DESK_CLERK,
        HOUSEKEEPER
        // Add more job titles here
    }

    public Staff(double baseSalary, double bonus, JobTitle jobTitle, String ID, String name,
            String address, String phone, String email, String age) {
        super(name, address, phone, email, age);
        this.baseSalary = baseSalary;
        this.bonus = bonus;
        this.jobTitle = jobTitle;
        this.ID = ID;
    }

    public Staff(double baseSalary, double bonus, JobTitle jobTitle, String ID) {
        this.baseSalary = baseSalary;
        this.bonus = bonus;
        this.jobTitle = jobTitle;
        this.ID = ID;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        if (baseSalary >= 0)
            this.baseSalary = baseSalary;
        else
            System.out.println("Invalid base salary");
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        if (bonus >= 0)
            this.bonus = bonus;
        else
            System.out.println("Invalid bonus");
    }

    public JobTitle getJobTitle() {
        return handleJobTitle();
    }

    public void setJobTitle(JobTitle jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getSalary() {
        return handleSalary();
    }

    public abstract double calculateSalary();

    public String getID() {
        return handleID();
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    private String handleSalary() {
        double salary = calculateSalary();
        DecimalFormat decimalFormat = new DecimalFormat("$#,###.00");
        return decimalFormat.format(salary);
    }

    private String handleID() {
        if (this instanceof Manager)
            return "M" + ID;
        else if (this instanceof Employee)
            return "E" + ID;

        return ID;
    }

    private JobTitle handleJobTitle() {
        if (this instanceof Manager) {
            switch (jobTitle) {
                case CEO:
                    return jobTitle;
                case GENERAL_MANAGER:
                    return jobTitle;
                case FRONT_DESK_MANAGER:
                    return jobTitle;
                // Add more specific job titles for managers here
                default:
                    System.out.println("Invalid job title");
                    break;
            }
        } else if (this instanceof Employee) {
            switch (jobTitle) {
                case FRONT_DESK_CLERK:
                    return jobTitle;
                case HOUSEKEEPER:
                    return jobTitle;
                // Add more specific job titles for employees here
                default:
                    System.out.println("Invalid job title");
                    break;
            }
        }

        return null;
    }
}

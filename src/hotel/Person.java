/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotel;

/**
 *
 * @author ahmed
 */
public abstract class Person {
    protected String name;
    protected String address;
    protected String phone;
    protected String email;
    protected String age;

    public Person(String name, String address, String phone, String email, String age) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.age = age;
    }

    public Person() {
        this.name = "";
        this.address = "";
        this.phone = "";
        this.email = "";
        this.age = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = handleName(name);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = handleAddress(address);
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = formatPhone(phone);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = formatEmail(email);
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = handleAge(age);
    }

    public String formatPhone(String phone) {
        String formattedPhone = "";
        if (phone.length() == 11) {
            formattedPhone = "(+2) " + phone.substring(0, 3) + "-" + phone.substring(3, 6) + "-"
                    + phone.substring(6, 10);
        } else if (phone.length() == 10) {
            formattedPhone = "(+2) " + phone.substring(0, 2) + "-" + phone.substring(2, 5) + "-"
                    + phone.substring(5, 9);
        } else {
            formattedPhone = phone;
        }
        return formattedPhone;
    }

    public String formatEmail(String email) {
        String formattedEmail = "";
        if (email.contains("@")) {
            formattedEmail = email;
        } else {
            formattedEmail = email + "@gmail.com";
        }
        return formattedEmail;
    }

    public String handleAge(String age) {
        String ageString = "";
        if (age.length() > 0) {
            ageString = age;
        } else {
            ageString = "No age";
        }
        return ageString;
    }

    public String handleName(String name) {
        String nameString = "";
        if (name.length() > 0) {
            nameString = name;
        } else {
            nameString = "No name";
        }
        return nameString;
    }

    public String handleAddress(String address) {
        String addressString = "";
        if (address.length() > 0) {
            addressString = address;
        } else {
            addressString = "No address";
        }
        return addressString;
    }

    @Override
    public String toString() {
        return "Name: " + getName() + "\nAddress: " + getAddress() + "\nPhone: " + getPhone() + "\nEmail: " + getEmail()
                + "\nAge: " + getAge();
    }

}
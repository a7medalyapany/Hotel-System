/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotel;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import hotel.Room.RoomType;

/**
 *
 * @author ahmed
 */
public class Guest extends Person {
    private LocalDate checkInDate;
    private String roomPreference;
    private int stayDuration;
    private boolean bookingFlag = false;
    private Room room;

    public Guest(String name, String address, String phone, String email, String age, String roomPreference) {
        super(name, address, phone, email, age);
        this.roomPreference = roomPreference;
    }

    public Guest(String name, String address, String phone, String email, String age, String roomPreference,
            int stayDuration, LocalDate checkInDate) {
        super(name, address, phone, email, age);
        this.checkInDate = checkInDate;
        this.roomPreference = roomPreference;
        this.stayDuration = stayDuration;
    }

    public Guest() {
        super();
        this.checkInDate = LocalDate.now();
        this.roomPreference = "";
    }

    // setter and getter for room
    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public int getStayDuration() {
        return stayDuration;
    }

    public void setStayDuration(int stayDuration) {
        this.stayDuration = stayDuration;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        if (bookingFlag)
            return getCheckInDate().plusDays(getStayDuration());
        else
            return LocalDate.now();
    }

    public String getRoomPreference() {
        return roomPreference;
    }

    public void setRoomPreference(String roomPreference) {
        this.roomPreference = roomPreference;
    }

    public synchronized boolean bookRoom(RoomType roomType, LocalDate checkInDate, int days) {
        Hotel hotel = Hotel.getInstance();

        stayDuration = days;
        if (stayDuration < 1) {
            System.out.println("Invalid booking duration. Minimum stay duration is 1 day.");
            return false;
        }

        if (hotel.isRoomAvailable(roomType)) {
            room = hotel.getAvailableRoomByType(roomType);
            room.setCheckOutDate(checkInDate.plusDays(stayDuration));
            hotel.addBookedRoom(room);
            hotel.removeAvailableRoom(room);
            setRoom(room);

            System.out.println("You did it, Now we are waiting you !!");
            bookingFlag = true;
            return bookingFlag;
        }

        System.out.println("Sorry " + this.getName() + " Room of type " + roomType + " isn't available right now :(");
        bookingFlag = false;
        return bookingFlag;
    }

    public synchronized void cancelBooking(Room room) {
        Hotel hotel = Hotel.getInstance();
        hotel.getBookedRooms().remove(room);
        hotel.addAvailableRoom(room);
    }

    public String getBookedRoomInfo() {
        String info = "";
        Hotel hotel = Hotel.getInstance();
        for (Room room : hotel.getBookedRooms()) {
            info += room.toString() + "\n";
        }
        return info;
    }

    public String getAvailableRoomInfo() {
        String info = "Available Rooms :\n";
        Hotel hotel = Hotel.getInstance();
        for (Room room : hotel.getAvailableRooms()) {
            info += room.toString() + "\n";
        }
        return info;
    }

    public void useCoupon(String couponCode, Room room) {
        Hotel hotel = Hotel.getInstance();
        HashMap<String, HashMap<Float, Integer>> offers = hotel.getOffers();

        if (offers.containsKey(couponCode)) {
            HashMap<Float, Integer> offerDetails = offers.get(couponCode);
            float discountPercentage = 0;
            int applicable = 0;

            for (Map.Entry<Float, Integer> entry : offerDetails.entrySet()) {
                discountPercentage = entry.getKey();
                applicable = entry.getValue();
                break;
            }

            if (applicable > 0) {

                switch (room.getRoomType()) {
                    case SINGLE:
                        room.setPrice(room.getPrice() - (room.getPrice() * (discountPercentage / 100)));
                        break;
                    case DOUBLE:
                        room.setPrice((room.getPrice() / 2) - ((room.getPrice() / 2) *
                                (discountPercentage / 100)));
                        break;
                    case TRIPLE:
                        room.setPrice((room.getPrice() / 3) - ((room.getPrice() / 3) *
                                (discountPercentage / 100)));
                        break;
                    case SUITE:
                        room.setPrice((room.getPrice() / 4) - ((room.getPrice() / 4) *
                                (discountPercentage / 100)));
                        break;

                    default:
                        break;
                }
                offerDetails.put(discountPercentage, applicable - 1);
                System.out.println("Coupon applied successfully!");

            } else {
                System.out.println("Coupon has reached its maximum limit.");
            }
        } else {
            System.out.println("Invalid coupon code.");
        }
    }

    @Override
    public String toString() {
        return "Guest{" + "checkOutDate = " + getCheckOutDate() + ", stay Duration : "
                + getStayDuration() + ", roomPreference : "
                + roomPreference + '}';
    }

}
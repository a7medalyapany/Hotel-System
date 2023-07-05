/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotel;

import java.time.LocalDate;
import java.util.concurrent.RejectedExecutionException;

/**
 *
 * @author ahmed
 */
public class Room {
    private String roomNumber;
    private RoomType roomType;
    private float price;
    private boolean isAvailable;
    private LocalDate checkOutDate;
    private boolean cleaningStatus;// true if the room needs to be cleaned // back to fix this

    public enum RoomType {
        SINGLE, DOUBLE, TRIPLE, SUITE
    }

    public Room(String roomNumber, RoomType roomType, float price) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.price = calculatePrice(price);
        isAvailable = true;
        this.cleaningStatus = false;
    }

    public Room() {
        this.roomNumber = "";
        this.roomType = RoomType.SINGLE;
        this.price = 0;
        isAvailable = true;
        this.cleaningStatus = false;
    }

    public boolean getCleaningStatus() {
        return cleaningStatus;
    }

    public void needToClean() {
        this.cleaningStatus = true;
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public synchronized void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = handleRoomNumber(roomNumber);
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        switch (roomType) {
            case "SINGLE":
                this.roomType = RoomType.SINGLE;
                break;
            case "DOUBLE":
                this.roomType = RoomType.DOUBLE;
                break;
            case "TRIPLE":
                this.roomType = RoomType.TRIPLE;
                break;
            case "SUITE":
                this.roomType = RoomType.SUITE;
                break;
            default:
                throw new RejectedExecutionException("Invalid room type");
        }
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = calculatePrice(price);
    }

    private float calculatePrice(float basePrice) {
        float finalPrice = basePrice;

        switch (roomType) {
            case SINGLE:
                finalPrice = basePrice;
                break;
            case DOUBLE:
                finalPrice = basePrice * 2;
                break;
            case TRIPLE:
                finalPrice = basePrice * 3;
                break;
            case SUITE:
                finalPrice = basePrice * 4;
                break;
            default:
                throw new RejectedExecutionException("Invalid room type");
        }
        return finalPrice;
    }

    private String handleRoomNumber(String roomNumber) {
        switch (roomType) {
            case SINGLE:
                return "S" + roomNumber;
            case DOUBLE:
                return "D" + roomNumber;
            case TRIPLE:
                return "T" + roomNumber;
            case SUITE:
                return "SU" + roomNumber;
            default:
                throw new RejectedExecutionException("Invalid room type");
        }
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber='" + roomNumber + '\'' +
                ", roomType=" + roomType +
                ", price='" + price + '\'' +
                ", isAvailable=" + isAvailable +
                '}';
    }

}
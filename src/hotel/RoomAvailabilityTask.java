/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotel;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author ahmed
 */
public class RoomAvailabilityTask implements Runnable {
    private List<Room> bookings;
    private Hotel hotel;

    public RoomAvailabilityTask(List<Room> bookings) {
        this.bookings = bookings;
    }

    @Override
    public void run() {
        while (true) {
            updateRoomAvailability();

            if (!bookings.isEmpty()) {
                Room firstBooking = bookings.get(0);
                LocalDate currentDate = LocalDate.now();
                LocalDate firstCheckoutDate = firstBooking.getCheckOutDate();

                if (firstCheckoutDate.isAfter(currentDate) || firstCheckoutDate.isEqual(currentDate)) {
                    sleepUntil(firstCheckoutDate);
                }
            } else {
                sleepForSomeTime();
            }
        }
    }

    private void updateRoomAvailability() {
        LocalDate currentDate = LocalDate.now();

        for (Room booking : bookings) {
            if (booking.getCheckOutDate().isBefore(currentDate)) {
                hotel.addAvailableRoom(booking);
                bookings.remove(booking);
            }
        }
    }

    private void sleepUntil(LocalDate date) {
        LocalDate currentDate = LocalDate.now();

        while (currentDate.isBefore(date) || currentDate.isEqual(date)) {
            try {
                Thread.sleep(1000); // Sleep for 1 second (adjust as needed)
                currentDate = LocalDate.now();
            } catch (InterruptedException e) {
                e.getMessage();
            }
        }
    }

    private void sleepForSomeTime() {
        try {
            Thread.sleep(5000); // Sleep for 5 seconds (adjust as needed)
        } catch (InterruptedException e) {
            e.getMessage();
        }
    }

}

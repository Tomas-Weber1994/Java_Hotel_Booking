package com.engeto;

import java.util.ArrayList;
import java.util.List;

public class BookingManager {
    private final List<Booking> bookings;

    public BookingManager(List<Booking> bookings) {
        this.bookings = bookings;
        checkIfBookingsNotEmpty();
    }

    private void checkIfBookingsNotEmpty() {
        if (bookings == null || bookings.isEmpty() || bookings.contains(null)) {
            throw new IllegalArgumentException(
                    "Nebyla vložena žádná rezervace nebo je minimálně jedna z rezervací prázdná.");
        }
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public Booking getBooking(int index) {
        return bookings.get(index);
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void clearBookings() {
        bookings.clear();
    }

    public int getNumberOfWorkingBookings() {
        int numberOfWorkingBookings = 0;
        for (Booking booking : bookings) {
            if (booking.isBusinessTrip()) {
                numberOfWorkingBookings += 1;
            }
        }
        return numberOfWorkingBookings;
    }

    public double getAverageGuests() {
        if (bookings.isEmpty()) {
            return 0.0;
        }
        int guestCount = 0;
        for (Booking booking : bookings) {
            guestCount += booking.getListOfGuests().size();
        }
        return (double) guestCount / bookings.size(); // Výsledek dělení bude typu double
    }

    public List<Booking> getTopNHolidayBookings(int limit) {
        List<Booking> holidayBookings = new ArrayList<>();
        for (Booking booking : bookings) {
            if (!booking.isBusinessTrip()) {
                holidayBookings.add(booking);
            }
            if (holidayBookings.size() >= limit) {
                break;
            }
        }
        return holidayBookings;
    }

    public void printGuestStatistics() {
        int singleGuestBookings = 0, twoGuestBookings = 0, multipleGuestBookings = 0;
        for (Booking booking : bookings) {
            int guestCount = booking.getListOfGuests().size();
            if (guestCount == 1) {
                singleGuestBookings++;
            } else if (guestCount == 2) {
                twoGuestBookings++;
            } else {
                multipleGuestBookings++;
            }
        }
        System.out.println("Počet rezervací s jedním hostem: " + singleGuestBookings);
        System.out.println("Počet rezervací se dvěma hosty: " + twoGuestBookings);
        System.out.println("Počet rezervací s více než dvěma hosty: " + multipleGuestBookings);
    }


}

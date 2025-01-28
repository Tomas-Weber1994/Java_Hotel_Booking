package com.engeto;

import java.time.LocalDate;
import java.util.List;

public class Booking {
    private List<Guest> listOfGuests;
    private Room room;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean businessTrip;

    public Booking(List<Guest> listOfGuests, Room room, LocalDate startDate, LocalDate endDate, boolean businessTrip) {
        this.listOfGuests = listOfGuests;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
        this.businessTrip = businessTrip;
        checkIfGuestsNotEmpty();
    }

    public Booking(List<Guest> listOfGuests, Room room, LocalDate startDate, boolean businessTrip) {
        this(listOfGuests, room, startDate, startDate.plusDays(6), businessTrip);
    }

    private void checkIfGuestsNotEmpty() {
        if (listOfGuests == null || listOfGuests.isEmpty() || listOfGuests.contains(null)) {
            throw new IllegalArgumentException(
                    "Neplatná rezervace: host není vyplněn! V případě více hostů je třeba vyplnit všechny.");
        }
    }

    public List<Guest> getListOfGuests() {
        return listOfGuests;
    }

    public void setListOfGuests(List<Guest> listOfGuests) {
        this.listOfGuests = listOfGuests;
        checkIfGuestsNotEmpty();
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public boolean isBusinessTrip() {
        return businessTrip;
    }

    public void setBusinessTrip(boolean businessTrip) {
        this.businessTrip = businessTrip;
    }
}

package com.engeto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
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

    private String formatDate(LocalDate date) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return date.format(dateFormatter);
    }

    private long getBookingLength() {
        return ChronoUnit.DAYS.between(startDate, endDate);
    }

    public BigDecimal getTotalPrice() {
        return getRoom().getPricePerNight().multiply(BigDecimal.valueOf(getBookingLength()));
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

    @Override
    public String toString() {
        return  "Příjezd: " + formatDate(startDate) +
                ", Odjezd: " + formatDate(endDate) +
                ", Na jméno: " + listOfGuests.get(0) +
                ", Datum narození hlavního hosta: " + formatDate(listOfGuests.get(0).getBirthDate()) +
                ", Počet hostů: " + listOfGuests.size() +
                ", Pokoj: " + room.getRoomNumber() +
                ", Balkón: " + (room.isHasBalcony() ? "Ano" : "Ne") +
                ", Pracovní pobyt: " + (businessTrip ? "Ano" : "Ne") +
                ". Celková cena: " + getTotalPrice() + "\n";
    }
}

import com.engeto.Booking;
import com.engeto.Guest;
import com.engeto.Room;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        Guest guest1 = new Guest(
                "Adéla",
                "Malíková",
                LocalDate.of(1993, 3, 13));
        Guest guest2 = new Guest(
                "Jan",
                "Dvořáček",
                LocalDate.of(1995, 5, 5));
        guest2.setBirthDate(LocalDate.of(1995, 4, 5));
        System.out.println("Údaje o hostu: \n" + guest2.getFirstName()
                + " " + guest2.getLastName() + " (" + guest2.getBirthDate().format(dateFormatter) + ")\n");

        Room room1 = new Room(
                1, 1,
                true, true,
                BigDecimal.valueOf(1000));
        Room room2 = new Room(
                2, 1,
                true, true,
                BigDecimal.valueOf(2400));
        Room room3 = new Room(
                3, 3,
                false, true,
                BigDecimal.valueOf(2400));

        List<Guest> guestsForReservation1 = new ArrayList<>();
        guestsForReservation1.add(guest1);
        Booking reservation1 = new Booking(
                guestsForReservation1, room1, LocalDate.of(2021, 7, 19),
                LocalDate.of(2021, 7, 26), false);

        List<Guest> guestsForReservation2 = new ArrayList<>();
        guestsForReservation2.add(guest1);
        guestsForReservation2.add(guest2);
        Booking reservation2 = new Booking(
                guestsForReservation2, room3, LocalDate.of(2021, 9, 1),
                false);

        List<Booking> listOfReservations = new ArrayList<>();
        listOfReservations.add(reservation1);
        listOfReservations.add(reservation2);
        System.out.println("V systému jsou evidovány následující rezervace: ");
        for (Booking booking : listOfReservations) {
            System.out.println(booking.getRoom());
            System.out.println("Hosté: " + booking.getListOfGuests());
            System.out.println("Příjezd: " + booking.getStartDate().format(dateFormatter));
            System.out.println("Odjezd: " + booking.getEndDate().format(dateFormatter) + "\n");
        }
    }
}

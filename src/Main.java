import com.engeto.Booking;
import com.engeto.BookingManager;
import com.engeto.Guest;
import com.engeto.Room;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        BookingManager bookings = fillBookings();
        System.out.println("V systému jsou evidovány následující rezervace: ");
        System.out.println(bookings.getBookings() + "\n");
        System.out.println("Statistiky: ");
        bookings.printGuestStatistics();
        System.out.println("Průměrný počet hostů na pobytu: " + bookings.getAverageGuests() + "\n");
        System.out.println("Celkový počet pracovních pobytů: " + bookings.getNumberOfWorkingBookings());
        System.out.println("Prvních 8 rekreačních rezervací: ");
        System.out.println(bookings.getTopNHolidayBookings(8) + "\n");
    }

    // region plnění rezervací
    public static BookingManager fillBookings() {
        Guest guest1 = new Guest(
                "Karel",
                "Dvořák",
                LocalDate.of(1990, 5, 15));
        Guest guest2 = new Guest(
                "Karel",
                "Dvořák",
                LocalDate.of(1979, 1, 3));
        Guest guest3 = new Guest(
                "Karolína",
                "Tmavá",
                LocalDate.of(1994, 11, 29));

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
                guestsForReservation1,
                room3,
                LocalDate.of(2023, 6, 1),
                true);

        List<Guest> guestsForReservation2 = new ArrayList<>();
        guestsForReservation2.add(guest2);
        Booking reservation2 = new Booking(
                guestsForReservation2,
                room2,
                LocalDate.of(2023, 7, 18),
                LocalDate.of(2023, 7, 21),
                false);

        List<Guest> guestsForReservation3 = new ArrayList<>(List.of(guest3, guest1));
        Booking reservation3 = new Booking(
                guestsForReservation3,
                room3,
                LocalDate.of(2023, 8, 1),
                LocalDate.of(2023, 8, 31),
                true);

        List<Booking> listOfReservations = new ArrayList<>(List.of(reservation1, reservation2, reservation3));

        // 10 repetitive reservation for guest3
        List<Guest> guestsForReservation4 = new ArrayList<>(List.of(guest3));
        for (int i = 0; i < 10; i ++) {
            LocalDate startDate = LocalDate.of(2023, 8, i * 2 + 1);
            LocalDate endDate = startDate.plusDays(1);

            Booking repetitive_reservation = new Booking(
                    guestsForReservation4, room2, startDate, endDate, false);
            listOfReservations.add(repetitive_reservation);
        }

        return new BookingManager(listOfReservations);
    }
    //endregion
}

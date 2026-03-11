package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class AppointmentTest {

    @Test
    public void isValidDateTime() {
        // null date
        assertThrows(NullPointerException.class, () -> Appointment.isValidDateTime(null));

        // invalid dates
        assertFalse(Appointment.isValidDateTime("")); // empty string
        assertFalse(Appointment.isValidDateTime("12/03/2026 1400")); // wrong separator
        assertFalse(Appointment.isValidDateTime("12-03-2026 2pm")); // wrong time format

        // valid dates
        assertTrue(Appointment.isValidDateTime("12-03-2026 1400"));
    }

    @Test
    public void getFormattedStrings() {
        String from = "12-03-2026 1400";
        String to = "12-03-2026 1500";
        Appointment appt = new Appointment(from, to);

        assertEquals(from, appt.getFormattedStart());
        assertEquals(to, appt.getFormattedEnd());
    }

    @Test
    public void setDateTime_validRange_success() {
        Appointment appt = new Appointment("01-01-2026 1000", "01-01-2026 1100");
        appt.setDateTime("12-03-2026 1400", "12-03-2026 1500");
        assertEquals(LocalDateTime.of(2026, 3, 12, 14, 0), appt.getFrom());
    }

    @Test
    public void setDateTime_invalidRange_throwsIllegalArgumentException() {
        Appointment appt = new Appointment("01-01-2026 1000", "01-01-2026 1100");
        assertThrows(IllegalArgumentException.class, () ->
                appt.setDateTime("12-03-2026 1500", "12-03-2026 1400"));
    }

    @Test
    public void isOverlapping() {
        Appointment appt = new Appointment("12-03-2026 1400", "12-03-2026 1500");

        // Overlaps
        assertTrue(appt.isOverlapping(new Appointment("12-03-2026 1430", "12-03-2026 1530"))); // partial overlap
        assertTrue(appt.isOverlapping(new Appointment("12-03-2026 1300", "12-03-2026 1600"))); // fully contains

        // Does not overlap
        assertFalse(appt.isOverlapping(new Appointment("12-03-2026 1500", "12-03-2026 1600"))); // back-to-back
        assertFalse(appt.isOverlapping(new Appointment("13-03-2026 1400", "13-03-2026 1500"))); // different day
    }

    @Test
    public void equals() {
        Appointment appt = new Appointment("12-03-2026 1400", "12-03-2026 1500");
        assertTrue(appt.equals(appt));
        assertFalse(appt.equals(5));
        assertFalse(appt.equals(null));
        assertFalse(appt.equals(new Appointment("12-03-2026 1400", "12-03-2026 1600")));
    }

    @Test
    public void toStringMethod() {
        Appointment appt = new Appointment("12-03-2026 1400", "12-03-2026 1500");
        String expected = "12-03-2026 1400 to 12-03-2026 1500";
        assertEquals(expected, appt.toString());
    }
}

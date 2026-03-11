package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AppointmentTest {
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
}

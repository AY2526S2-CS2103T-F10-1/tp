package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Person's appointment in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDateTime(String)}
 */
public class Appointment {

    public static final String MESSAGE_CONSTRAINTS =
            "Appointments should be in the format 'dd-MM-yyyy HHmm' and the 'from' date must be before 'to'.";

    // Example format: 12-03-2026 1400
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructs an {@code Appointment}.
     *
     * @param fromStr A valid start date-time string.
     * @param toStr A valid end date-time string.
     */
    public Appointment(String fromStr, String toStr) {
        requireNonNull(fromStr);
        requireNonNull(toStr);
        checkArgument(isValidDateTime(fromStr) && isValidDateTime(toStr), MESSAGE_CONSTRAINTS);

        LocalDateTime start = LocalDateTime.parse(fromStr, FORMATTER);
        LocalDateTime end = LocalDateTime.parse(toStr, FORMATTER);

        checkArgument(start.isBefore(end), "The start time must be before the end time.");

        this.from = start;
        this.to = end;
    }

    /**
     * Returns true if a given string is in the correct date-time format.
     */
    public static boolean isValidDateTime(String test) {
        try {
            LocalDateTime.parse(test, FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Updates the appointment timing.
     */
    public void setDateTime(String fromStr, String toStr) {
        requireNonNull(fromStr);
        requireNonNull(toStr);
        checkArgument(isValidDateTime(fromStr) && isValidDateTime(toStr), MESSAGE_CONSTRAINTS);

        LocalDateTime start = LocalDateTime.parse(fromStr, FORMATTER);
        LocalDateTime end = LocalDateTime.parse(toStr, FORMATTER);

        checkArgument(start.isBefore(end), "The start time must be before the end time.");

        this.from = start;
        this.to = end;
    }

    /**
     * Checks if this appointment overlaps with another appointment.
     * Two intervals $[S_1, E_1]$ and $[S_2, E_2]$ overlap if $S_1 < E_2$ and $S_2 < E_1$.
     */
    public boolean isOverlapping(Appointment other) {
        requireNonNull(other);
        return this.from.isBefore(other.to) && other.from.isBefore(this.to);
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    @Override
    public String toString() {
        return String.format("%s to %s", from.format(FORMATTER), to.format(FORMATTER));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Appointment)) {
            return false;
        }
        Appointment o = (Appointment) other;
        return from.equals(o.from) && to.equals(o.to);
    }

    @Override
    public int hashCode() {
        return from.hashCode() + to.hashCode();
    }
}

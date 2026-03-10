package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Patient's allergy in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAllergy(String)}
 */
public class Allergy {

    public static final String MESSAGE_CONSTRAINTS = "Allergies can take any values, and it should not be blank";

    /*
     * The first character of the allergy must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code Allergy}.
     *
     * @param allergy A valid allergy.
     */
    public Allergy(String allergy) {
        requireNonNull(allergy);
        checkArgument(isValidAllergy(allergy), MESSAGE_CONSTRAINTS);
        value = allergy;
    }

    /**
     * Returns true if a given string is a valid allergy.
     */
    public static boolean isValidAllergy(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Allergy)) {
            return false;
        }

        Allergy otherAllergy = (Allergy) other;
        return value.equals(otherAllergy.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

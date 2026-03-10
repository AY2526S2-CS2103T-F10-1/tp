package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Patient's medical condition in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidMedicalCondition(String)}
 */
public class MedicalCondition {

    public static final String MESSAGE_CONSTRAINTS = "Medical conditions can take any values, and it should not be blank";

    /*
     * The first character of the medical condition must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs a {@code MedicalCondition}.
     *
     * @param medicalCondition A valid medical condition.
     */
    public MedicalCondition(String medicalCondition) {
        requireNonNull(medicalCondition);
        checkArgument(isValidMedicalCondition(medicalCondition), MESSAGE_CONSTRAINTS);
        value = medicalCondition;
    }

    /**
     * Returns true if a given string is a valid medical condition.
     */
    public static boolean isValidMedicalCondition(String test) {
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
        if (!(other instanceof MedicalCondition)) {
            return false;
        }

        MedicalCondition otherMedicalCondition = (MedicalCondition) other;
        return value.equals(otherMedicalCondition.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

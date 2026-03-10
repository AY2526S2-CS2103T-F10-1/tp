package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.patient.MedicalCondition;

/**
 * Jackson-friendly version of {@link MedicalCondition}.
 */
class JsonAdaptedMedicalCondition {

    private final String conditionName;

    /**
     * Constructs a {@code JsonAdaptedMedicalCondition} with the given {@code conditionName}.
     */
    @JsonCreator
    public JsonAdaptedMedicalCondition(String conditionName) {
        this.conditionName = conditionName;
    }

    /**
     * Converts a given {@code MedicalCondition} into this class for Jackson use.
     */
    public JsonAdaptedMedicalCondition(MedicalCondition source) {
        conditionName = source.value;
    }

    @JsonValue
    public String getConditionName() {
        return conditionName;
    }

    /**
     * Converts this Jackson-friendly adapted medical condition object into the model's {@code MedicalCondition} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted medical condition.
     */
    public MedicalCondition toModelType() throws IllegalValueException {
        if (!MedicalCondition.isValidMedicalCondition(conditionName)) {
            throw new IllegalValueException(MedicalCondition.MESSAGE_CONSTRAINTS);
        }
        return new MedicalCondition(conditionName);
    }

}

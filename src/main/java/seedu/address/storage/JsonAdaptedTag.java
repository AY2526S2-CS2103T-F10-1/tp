package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.Allergy;
import seedu.address.model.tag.GeneralTag;
import seedu.address.model.tag.MedicalCondition;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Tag}.
 */
class JsonAdaptedTag {

    private final String tagName;
    private final String tagType;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedTag(String tagName) {
        this.tagName = tagName;
        this.tagType = "general";
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedTag(Tag source) {
        tagName = source.tagName;

        if (source instanceof Allergy) {
            tagType = "allergy";
        } else if (source instanceof MedicalCondition) {
            tagType = "condition";
        } else {
            tagType = "general";
        }
    }

    @JsonValue
    public String toJson() {
        if ("general".equals(tagType)) {
            return tagName;
        }
        return tagType + ":" + tagName;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Tag toModelType() throws IllegalValueException {

        String name = tagName;
        String type = tagType;

        // Detect stored prefix format
        if (tagName.contains(":")) {
            String[] parts = tagName.split(":", 2);
            type = parts[0];
            name = parts[1];
        }

        if (!Tag.isValidTagName(name)) {
            throw new IllegalValueException(Tag.MESSAGE_CONSTRAINTS);
        }

        switch (type) {
        case "allergy":
            return new Allergy(name);
        case "condition":
            return new MedicalCondition(name);
        default:
            return new GeneralTag(name);
        }
    }
}

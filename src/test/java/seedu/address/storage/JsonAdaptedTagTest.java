package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Allergy;
import seedu.address.model.tag.GeneralTag;
import seedu.address.model.tag.MedicalCondition;
import seedu.address.model.tag.Tag;

public class JsonAdaptedTagTest {

    @Test
    public void toModelType_validAllergyTag_returnsAllergy() throws Exception {
        JsonAdaptedTag adaptedTag = new JsonAdaptedTag("allergy:peanut");
        Tag modelTag = adaptedTag.toModelType();

        assertEquals(Allergy.class, modelTag.getClass());
    }

    @Test
    public void toModelType_validConditionTag_returnsMedicalCondition() throws Exception {
        JsonAdaptedTag adaptedTag = new JsonAdaptedTag("condition:asthma");
        Tag modelTag = adaptedTag.toModelType();

        assertEquals(MedicalCondition.class, modelTag.getClass());
    }

    @Test
    public void toModelType_validGeneralTag_returnsGeneralTag() throws Exception {
        JsonAdaptedTag adaptedTag = new JsonAdaptedTag("friends");
        Tag modelTag = adaptedTag.toModelType();

        assertEquals(GeneralTag.class, modelTag.getClass());
    }

    @Test
    public void toModelType_invalidTag_throwsException() {
        JsonAdaptedTag adaptedTag = new JsonAdaptedTag("#invalid");

        org.junit.jupiter.api.Assertions.assertThrows(
                seedu.address.commons.exceptions.IllegalValueException.class,
                adaptedTag::toModelType
        );
    }

    @Test
    public void constructor_fromModelTag_createsCorrectType() throws Exception {
        Tag allergy = new Allergy("peanut");
        JsonAdaptedTag adaptedTag = new JsonAdaptedTag(allergy);

        Tag result = adaptedTag.toModelType();

        assertEquals(Allergy.class, result.getClass());
    }

    @Test
    public void toJson_medicalConditionTag_returnsConditionPrefix() throws Exception {
        Tag condition = new MedicalCondition("asthma");
        JsonAdaptedTag adaptedTag = new JsonAdaptedTag(condition);
        assertEquals("condition:asthma", adaptedTag.toJson());
    }

    @Test
    public void toJson_allergyTag_returnsAllergyPrefix() throws Exception {
        Tag allergy = new Allergy("peanut");
        JsonAdaptedTag adaptedTag = new JsonAdaptedTag(allergy);
        assertEquals("allergy:peanut", adaptedTag.toJson());
    }

    @Test
    public void toJson_generalTag_returnsNameOnly() throws Exception {
        Tag general = new GeneralTag("friends");
        JsonAdaptedTag adaptedTag = new JsonAdaptedTag(general);
        assertEquals("friends", adaptedTag.toJson());
    }

}

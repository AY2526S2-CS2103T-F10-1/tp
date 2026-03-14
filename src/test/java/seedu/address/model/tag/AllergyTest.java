package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AllergyTest {

    @Test
    public void constructor_validName_success() {
        Allergy allergy = new Allergy("Peanut");
        assertEquals("[Peanut]", allergy.toString());
    }
}

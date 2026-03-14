package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MedicalConditionTest {

    @Test
    public void constructor_validName_success() {
        MedicalCondition condition = new MedicalCondition("Asthma");
        assertEquals("[Asthma]", condition.toString());
    }
}

package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LEAVE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LEAVE;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

public class LeaveTest {

    @Test
    public void validLeave() {
        assertEquals(new Leave().leave, Leave.NO_LEAVE);
        assertTrue(Leave.isValidLeave(ALICE.getLeave().leave));
        assertTrue(Leave.isValidLeave(BOB.getLeave().leave));
        assertTrue(Leave.isValidLeave(VALID_LEAVE));
        assertFalse(Leave.isValidLeave(INVALID_LEAVE));
        assertFalse(Leave.isValidLeave("111101010102"));
        assertEquals(new Leave().toString(), "-");
        assertEquals(ALICE.getLeave().leave, "111101010101");
        assertEquals(ALICE.getLeave().toString(), "Jan, Feb, Mar, Apr, Jun, Aug, Oct, Dec");
        assertEquals(new Leave().update("++++0+0+0+0+"), ALICE.getLeave());
        assertEquals(new Leave(), ALICE.getLeave().update("----0-0-0-0-"));
        assertEquals(ALICE.getLeave(), ALICE.getLeave());
        assertFalse(ALICE.getLeave().equals(null));
        assertFalse(ALICE.getLeave().equals(new Leave()));
        assertEquals(ALICE.getLeave().hashCode(), ALICE.getLeave().leave.hashCode());
    }
}

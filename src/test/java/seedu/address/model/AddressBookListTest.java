package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class AddressBookListTest {
    private final AddressBookList addressBookList = new AddressBookList();

    @Test
    public void undo_success() {
        addressBookList.add(new AddressBook());
        addressBookList.addCommandText("");
        addressBookList.add(new AddressBook());
        addressBookList.addCommandText("");
        assertEquals(addressBookList.undo(), new AddressBook());
        addressBookList.add(new AddressBook());
        addressBookList.addCommandText("");
        assertEquals(addressBookList.undo(), new AddressBook());
    }

    @Test
    public void undo_failure() {
        assertThrows(IllegalArgumentException.class, () -> addressBookList.undo());
    }

    @Test
    public void redo_success() {
        addressBookList.add(new AddressBook());
        addressBookList.add(new AddressBook());
        addressBookList.undo();
        assertEquals(addressBookList.redo(), new AddressBook());
    }

    @Test
    public void redo_failure() {
        assertThrows(IllegalArgumentException.class, () -> addressBookList.redo());
    }
}

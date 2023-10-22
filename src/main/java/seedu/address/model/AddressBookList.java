package seedu.address.model;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.ArrayList;

/**
 * Represents the history of AddressBook to allow undo and redo.
 */
public class AddressBookList extends ArrayList<AddressBook> {
    public static final String REDO_ERROR_MESSAGE = "There is no command to redo! "
            + "The command to be redone need to previously modified the employee list.";
    public static final String UNDO_ERROR_MESSAGE = "There is no command to undo! "
            + "The command to be undone need to previously modified the employee list.";

    private ArrayList<String> pastCommands = new ArrayList<String>();
    private int index;

    /**
     * Initializes the AddressBookList.
     */
    public AddressBookList() {
        super();
        index = -1;
    }

    @Override
    public boolean add(AddressBook addressBook) {
        index++;
        if (index < this.size()) {
            this.removeRange(index, this.size());
            while (this.pastCommands.size() >= index) {
                this.pastCommands.remove(pastCommands.size() - 1);
            }
        }
        return super.add(addressBook);
    }

    /**
     * Returns the AddressBook at given index.
     * @return
     */
    public AddressBook getAddressBook() {
        return super.get(index);
    }

    /**
     * Updates the current AddressBook to the previous version.
     * @return AddressBook
     */
    public AddressBook undo() {
        checkArgument(index > 0, UNDO_ERROR_MESSAGE);
        index--;
        return getAddressBook();
    }

    /**
     * Updates the current AddressBook to the latest undone version.
     * @return AddressBook
     */
    public AddressBook redo() {
        checkArgument(index < super.size() - 1, REDO_ERROR_MESSAGE);
        index++;
        return getAddressBook();
    }

    /**
     * Stores the history of command texts.
     * @param commandText
     */
    public void addCommandText(String commandText) {
        this.pastCommands.add(commandText);
    }

    /**
     * Gets the previous command text.
     * @return previous command text
     */
    public String undoPastCommand() {
        return this.pastCommands.get(index);
    }

    /**
     * Gets the latest undone command text.
     * @return latest undone command text
     */
    public String redoPastCommand() {
        return this.pastCommands.get(index - 1);
    }
}

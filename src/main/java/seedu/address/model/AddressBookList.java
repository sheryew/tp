package seedu.address.model;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.ArrayList;

public class AddressBookList extends ArrayList<AddressBook> {
    private static final String REDO_ERROR_MESSAGE = "There is no command to redo! "
            + "The command to be redone need to previously modified the employee list.";
    private static final String UNDO_ERROR_MESSAGE = "There is no command to undo! "
            + "The command to be undone need to previously modified the employee list.";

    private ArrayList<String> pastCommands = new ArrayList<String>();
    private int index;

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

    public AddressBook getAddressBook() {
        return super.get(index);
    }

    public AddressBook undo() {
        checkArgument(index > 0, UNDO_ERROR_MESSAGE);
        index--;
        return super.get(index);
    }

    public AddressBook redo() {
        checkArgument(index < super.size() - 1, REDO_ERROR_MESSAGE);
        index++;
        return super.get(index);
    }

    public void addCommandText(String commandText) {
        this.pastCommands.add(commandText);
    }

    public String undoPastCommand() {
        return this.pastCommands.get(index);
    }

    public String redoPastCommand() {
        return this.pastCommands.get(index - 1);
    }
}

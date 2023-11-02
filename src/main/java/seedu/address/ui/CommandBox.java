package seedu.address.ui;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "CommandBox.fxml";

    private final CommandExecutor commandExecutor;
    private final InputHistory inputs;

    @FXML
    private TextField commandTextField;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     */
    public CommandBox(CommandExecutor commandExecutor) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        this.inputs = new InputHistory();
        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        commandTextField.textProperty().addListener((unused1, unused2, unused3) -> setStyleToDefault());
    }

    /**
     * Handles the Enter button pressed event.
     */
    @FXML
    private void handleCommandEntered() {
        String commandText = commandTextField.getText();
        if (commandText.equals("")) {
            return;
        }

        try {
            inputs.add(commandText);
            commandExecutor.execute(commandText);
            commandTextField.setText("");
        } catch (CommandException | ParseException e) {
            setStyleToIndicateCommandFailure();
        }
    }

    /**
     * Handles UP and DOWN key pressed event.
     */
    @FXML
    private void handleClick(KeyEvent keyEvent) {
        KeyCode clickedKey = keyEvent.getCode();

        if (clickedKey.equals(KeyCode.UP)) {
            keyEvent.consume();
            getPreviousInput();
        } else if (clickedKey.equals(KeyCode.DOWN)) {
            keyEvent.consume();
            getNextInput();
        }
    }

    private void getPreviousInput() {
        if (inputs.hasPreviousInput()) {
            updateText(inputs.getPreviousInput());
        }
    }

    private void getNextInput() {
        if (inputs.hasNextInput()) {
            updateText(inputs.getNextInput());
        } else {
            updateText("");
            inputs.setLastIndex();
        }
    }

    /**
     * Change current text with the given input.
     */
    private void updateText(String input) {
        commandTextField.setText(input);
        commandTextField.positionCaret(input.length());
    }

    /**
     * Sets the command box style to use the default style.
     */
    private void setStyleToDefault() {
        commandTextField.getStyleClass().remove(ERROR_STYLE_CLASS);
    }

    /**
     * Sets the command box style to indicate a failed command.
     */
    private void setStyleToIndicateCommandFailure() {
        ObservableList<String> styleClass = commandTextField.getStyleClass();

        if (styleClass.contains(ERROR_STYLE_CLASS)) {
            return;
        }

        styleClass.add(ERROR_STYLE_CLASS);
    }

    /**
     * Represents a function that can execute commands.
     */
    @FunctionalInterface
    public interface CommandExecutor {
        /**
         * Executes the command and returns the result.
         *
         * @see seedu.address.logic.Logic#execute(String)
         */
        CommandResult execute(String commandText) throws CommandException, ParseException;
    }

    /**
     * Represents the history of inputs given by the user.
     */
    public class InputHistory extends ArrayList<String> {
        private int index;

        /**
         * Constructs the InputHistory.
         */
        public InputHistory() {
            super();
            super.add("");
            index = 1;
        }

        @Override
        public boolean add(String input) {
            if (!input.equals(super.get(super.size() - 1))) {
                super.add(input);
            }
            index = super.size();
            return true;
        }

        /**
         * Sets the index to be equal to the list size.
         */
        public void setLastIndex() {
            index = super.size();
        }

        /**
         * Checks whether the list has previous input.
         * @return boolean
         */
        public boolean hasPreviousInput() {
            return index > 0;
        }

        /**
         * Checks whether the list has next input.
         * @return boolean
         */
        public boolean hasNextInput() {
            return index < super.size() - 1;
        }

        /**
         * Gets the previous input.
         * @return the previous input
         */
        public String getPreviousInput() {
            assert index > 0;
            index--;
            return super.get(index);
        }

        /**
         * Gets the next input.
         * @return the next input
         */
        public String getNextInput() {
            assert index < super.size() - 1;
            index++;
            return super.get(index);
        }
    }
}

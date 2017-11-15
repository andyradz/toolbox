package com.codigo.aplios.toolbox.core.gui;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ComboBoxEditor;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;

public class AutoCompletion extends PlainDocument {

    JComboBox comboBox;

    ComboBoxModel model;

    JTextComponent editor;
    // flag to indicate if setSelectedItem has been called
    // subsequent calls to remove/insertString should be ignored

    boolean selecting = false;

    boolean hidePopupOnFocusLoss;

    boolean hitBackspace = false;

    boolean hitBackspaceOnSelection;

    KeyListener editorKeyListener;

    FocusListener editorFocusListener;

    public AutoCompletion(final JComboBox comboBox) {

        this.comboBox = comboBox;
        this.model = comboBox.getModel();
        comboBox.addActionListener(e -> {
            if (!AutoCompletion.this.selecting)
                AutoCompletion.this.highlightCompletedText(0);
        });
        comboBox.addPropertyChangeListener(e -> {
            if (e.getPropertyName()
                    .equals("editor"))
                AutoCompletion.this.configureEditor((ComboBoxEditor)e.getNewValue());
            if (e.getPropertyName()
                    .equals("model"))
                AutoCompletion.this.model = (ComboBoxModel)e.getNewValue();
        });
        this.editorKeyListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

                if (comboBox.isDisplayable())
                    comboBox.setPopupVisible(true);
                AutoCompletion.this.hitBackspace = false;
                switch (e.getKeyCode()) {
                    // determine if the pressed key is backspace (needed by the remove method)
                    case KeyEvent.VK_BACK_SPACE:
                        AutoCompletion.this.hitBackspace = true;
                        AutoCompletion.this.hitBackspaceOnSelection = AutoCompletion.this.editor
                                .getSelectionStart() != AutoCompletion.this.editor.getSelectionEnd();
                        break;
                    // ignore delete key
                    case KeyEvent.VK_DELETE:
                        e.consume();
                        comboBox.getToolkit()
                                .beep();
                        break;
                }
            }

        };
        // Bug 5100422 on Java 1.5: Editable JComboBox won't hide popup when tabbing out
        this.hidePopupOnFocusLoss = System.getProperty("java.version")
                .startsWith("1.5");
        // Highlight whole text when gaining focus
        this.editorFocusListener = new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {

                AutoCompletion.this.highlightCompletedText(0);
            }

            @Override
            public void focusLost(FocusEvent e) {

                // Workaround for Bug 5100422 - Hide Popup on focus loss
                if (AutoCompletion.this.hidePopupOnFocusLoss)
                    comboBox.setPopupVisible(false);
            }

        };
        this.configureEditor(comboBox.getEditor());
        // Handle initially selected object
        Object selected = comboBox.getSelectedItem();
        if (selected != null)
            this.setText(selected.toString());
        this.highlightCompletedText(0);
    }

    public static void enable(JComboBox comboBox) {

        // has to be editable
        comboBox.setEditable(true);
        // change the editor's document
        new AutoCompletion(comboBox);
    }

    void configureEditor(ComboBoxEditor newEditor) {

        if (this.editor != null) {
            this.editor.removeKeyListener(this.editorKeyListener);
            this.editor.removeFocusListener(this.editorFocusListener);
        }

        if (newEditor != null) {
            this.editor = (JTextComponent)newEditor.getEditorComponent();
            this.editor.addKeyListener(this.editorKeyListener);
            this.editor.addFocusListener(this.editorFocusListener);
            this.editor.setDocument(this);
        }
    }

    @Override
    public void remove(int offs, int len) throws BadLocationException {

        // return immediately when selecting an item
        if (this.selecting)
            return;
        if (this.hitBackspace) {
            // user hit backspace => move the selection backwards
            // old item keeps being selected
            if (offs > 0) {
                if (this.hitBackspaceOnSelection)
                    offs--;
            } else
                // User hit backspace with the cursor positioned on the start => beep
                this.comboBox.getToolkit()
                        .beep(); // when available use:
            // UIManager.getLookAndFeel().provideErrorFeedback(comboBox);
            this.highlightCompletedText(offs);
        } else
            super.remove(offs, len);
    }

    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {

        // return immediately when selecting an item
        if (this.selecting)
            return;
        // insert the string into the document
        super.insertString(offs, str, a);
        // lookup and select a matching item
        Object item = this.lookupItem(this.getText(0, this.getLength()));
        if (item != null)
            this.setSelectedItem(item);
        else {
            // keep old item selected if there is no match
            item = this.comboBox.getSelectedItem();
            // imitate no insert (later on offs will be incremented by str.length(): selection won't move forward)
            offs = offs - str.length();
            // provide feedback to the user that his input has been received but can not be accepted
            this.comboBox.getToolkit()
                    .beep(); // when available use:
            // UIManager.getLookAndFeel().provideErrorFeedback(comboBox);
        }
        this.setText(item.toString());
        // select the completed part
        this.highlightCompletedText(offs + str.length());
    }

    private void setText(String text) {

        try {
            // remove all text and insert the completed string
            super.remove(0, this.getLength());
            super.insertString(0, text, null);
        } catch (BadLocationException e) {
            throw new RuntimeException(e.toString());
        }
    }

    private void highlightCompletedText(int start) {

        this.editor.setCaretPosition(this.getLength());
        this.editor.moveCaretPosition(start);
    }

    private void setSelectedItem(Object item) {

        this.selecting = true;
        this.model.setSelectedItem(item);
        this.selecting = false;
    }

    private Object lookupItem(String pattern) {

        Object selectedItem = this.model.getSelectedItem();
        // only search for a different item if the currently selected does not match
        if ((selectedItem != null) && this.startsWithIgnoreCase(selectedItem.toString(), pattern))
            return selectedItem;
        else
            // iterate over all items
            for (int i = 0, n = this.model.getSize(); i < n; i++) {
                Object currentItem = this.model.getElementAt(i);
                // current item starts with the pattern?
                if ((currentItem != null) && this.startsWithIgnoreCase(currentItem.toString(), pattern))
                    return currentItem;
            }
        // no item starts with the pattern => return null
        return null;
    }

    // checks if str1 starts with str2 - ignores case
    private boolean startsWithIgnoreCase(String str1, String str2) {

        return str1.toUpperCase()
                .startsWith(str2.toUpperCase());
    }

    private static void createAndShowGUI() {

        // the combo box (add/modify items if you like to)
        final JComboBox comboBox = new JComboBox(new Object[]{"Ester", "Jordi", "Jordina", "Jorge", "Sergi"});
        AutoCompletion.enable(comboBox);

        // create and show a window containing the combo box
        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(3);
        frame.getContentPane()
                .add(comboBox);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(() -> AutoCompletion.createAndShowGUI());
    }

}

package com.codigo.aplios.toolbox.core.gui;

import java.awt.Component;
import java.awt.Window;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

public class Demonstration {

	public static String getLookAndFeelClassName(String nameSnippet) {

		LookAndFeelInfo[] plafs = UIManager.getInstalledLookAndFeels();
		for (LookAndFeelInfo info : plafs)
			if (info.getName()
				.contains(nameSnippet))
				return info.getClassName();
		return null;
	}

	public static void main(String[] args) throws Exception {

		String className = Demonstration.getLookAndFeelClassName("Nimbus");
		UIManager.setLookAndFeel(className);
		// UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		for (Window window : Window.getWindows())
			SwingUtilities.updateComponentTreeUI(window);

		SwingUtilities.invokeAndWait(() -> {
			List<String> myWords = new ArrayList<>();

			BufferedReader in = null;
			try {
				String str;
				in = new BufferedReader(new FileReader("D:/eclipse/workspace/explorer/WordList.txt"));
				while ((str = in.readLine()) != null)
					myWords.add(str);

			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			myWords.add("bike");
			myWords.add("car");
			myWords.add("cap");
			myWords.add("cape");
			myWords.add("canadian");
			myWords.add("caprecious");
			myWords.add("catepult");
			myWords.add("poland");
			myWords.add("norway");
			myWords.add("spain");

			myWords.forEach(e3 -> {
				System.out.println(e3.toString());
			});

			StringSearchable searchable = new StringSearchable(myWords);
			AutocompleteJComboBox combo = new AutocompleteJComboBox(searchable);
			JFrame frame = new JFrame();
			frame.add(combo);
			frame.pack();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});
	}
}

/**
 *
 * Interface to search an underlying inventory of items and return a collection of found items.
 *
 * @author G. Cope
 * @param <E> The type of items to be found.
 * @param <V> The type of items to be searched
 */
interface Searchable<E, V> {

	/**
	 * Searches an underlying inventory of items consisting of type E
	 *
	 * @param value A searchable value of type V
	 *
	 * @return A Collection of items of type E.
	 */

	public List<E> search(V value);

}

/**
 *
 * Implementation of the Searchable interface that searches a List of String objects.
 *
 * This implementation searches only the beginning of the words, and is not be optimized
 *
 * for very large Lists.
 *
 * @author G. Cope
 *
 *
 *
 */

class StringSearchable implements Searchable<String, String> {
	private List<String> terms;

	public StringSearchable(List<String> terms) {

		this.terms = terms;
	}

	@Override
	public List<String> search(String value) {

		return Stream.of(this.terms)
			.flatMap(r -> r.stream())
			.filter(e -> e.indexOf(value) == 0)
			.collect(Collectors.toList());
	}
}

class AutocompleteJComboBox extends JComboBox {
	static final long serialVersionUID = 4321421L;
	// private boolean isAutoCompleted = true;
	private final Searchable<String, String> searchable;

	public AutocompleteJComboBox(Searchable<String, String> s) {

		super();
		this.searchable = s;
		this.setEditable(true);

		Component c = this.getEditor()
			.getEditorComponent();
		if (c instanceof JTextComponent) {

			final JTextComponent tc = (JTextComponent) c;

			tc.getDocument()
				.addDocumentListener(new DocumentListener() {
					@Override
					public void changedUpdate(DocumentEvent arg0) {

					}

					@Override
					public void insertUpdate(DocumentEvent arg0) {

						this.update();
					}

					@Override
					public void removeUpdate(DocumentEvent arg0) {

						this.update();
					}

					public void update() {

						// perform separately, as listener conflicts between the editing component
						// and JComboBox will result in an IllegalStateException due to editing
						// the component when it is locked.
						SwingUtilities.invokeLater(() -> {
							List<String> founds = AutocompleteJComboBox.this.searchable.search(tc.getText());

							Collections.sort(founds);// sort alphabetically
							AutocompleteJComboBox.this.setEditable(false);
							AutocompleteJComboBox.this.removeAllItems();

							// if founds contains the search text, then only add once.
							if (!founds.contains(tc.getText()))
								AutocompleteJComboBox.this.addItem(tc.getText());

							founds.forEach(e -> AutocompleteJComboBox.this.addItem(e));
							AutocompleteJComboBox.this.setEditable(true);
							AutocompleteJComboBox.this.setPopupVisible(true);
						});
					}
				});

			// When the text component changes, focus is gained

			// and the menu disappears. To account for this, whenever the focus

			// is gained by the JTextComponent and it has searchable values, we show the popup.

			tc.addFocusListener(new FocusListener() {
				@Override
				public void focusGained(FocusEvent arg0) {

					if (tc.getText()
						.length() > 0)
						AutocompleteJComboBox.this.setPopupVisible(true);
				}

				@Override
				public void focusLost(FocusEvent arg0) {

				}
			});

		} else
			throw new IllegalStateException("Editing component is not a JTextComponent!");

	}
}

// UIManager.put("Button.font", /* font of your liking */);
// UIManager.put("ToggleButton.font", /* font of your liking */);
// UIManager.put("RadioButton.font", /* font of your liking */);
// UIManager.put("CheckBox.font", /* font of your liking */);
// UIManager.put("ColorChooser.font", /* font of your liking */);
// UIManager.put("ComboBox.font", /* font of your liking */);
// UIManager.put("Label.font", /* font of your liking */);
// UIManager.put("List.font", /* font of your liking */);
// UIManager.put("MenuBar.font", /* font of your liking */);
// UIManager.put("MenuItem.font", /* font of your liking */);
// UIManager.put("RadioButtonMenuItem.font", /* font of your liking */);
// UIManager.put("CheckBoxMenuItem.font", /* font of your liking */);
// UIManager.put("Menu.font", /* font of your liking */);
// UIManager.put("PopupMenu.font", /* font of your liking */);
// UIManager.put("OptionPane.font", /* font of your liking */);
// UIManager.put("Panel.font", /* font of your liking */);
// UIManager.put("ProgressBar.font", /* font of your liking */);
// UIManager.put("ScrollPane.font", /* font of your liking */);
// UIManager.put("Viewport.font", /* font of your liking */);
// UIManager.put("TabbedPane.font", /* font of your liking */);
// UIManager.put("Table.font", /* font of your liking */);
// UIManager.put("TableHeader.font", /* font of your liking */);
// UIManager.put("TextField.font", /* font of your liking */);
// UIManager.put("PasswordField.font", /* font of your liking */);
// UIManager.put("TextArea.font", /* font of your liking */);
// UIManager.put("TextPane.font", /* font of your liking */);
// UIManager.put("EditorPane.font", /* font of your liking */);
// UIManager.put("TitledBorder.font", /* font of your liking */);
// UIManager.put("ToolBar.font", /* font of your liking */);
// UIManager.put("ToolTip.font", /* font of your liking */);
// UIManager.put("Tree.font", /* font of your liking */);

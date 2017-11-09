
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// import org.pushingpixels.substance.api.renderers.SubstanceDefaultListCellRenderer;
// import org.pushingpixels.substance.api.skin.SubstanceGraphiteGlassLookAndFeel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;

/**
 * Simple example which shows the ListIndexBar component in use.
 */

public class ListIndexBarExample {

	public static void main(String[] args) {

		final Color MARKER_COLOUR = Color.GREEN;

		SwingUtilities.invokeLater(() -> {
			try {
				// UIManager.setLookAndFeel(new SubstanceGraphiteGlassLookAndFeel());
				// if you don't want to use the Substance L&F, you can use an alternative e.g.
				UIManager.setLookAndFeel(new WindowsLookAndFeel());
				JFrame.setDefaultLookAndFeelDecorated(true);
			} catch (UnsupportedLookAndFeelException e) {
				e.printStackTrace();
			}

			JPanel panel = new JPanel(new BorderLayout());

			// create a list of 100 items which the list will be populated with
			List<String> items = new ArrayList<>();
			for (int i = 0; i < 100; i++)
				items.add("Item " + (i + 1));

			// create the list and it's containing scrollpane
			final JList list = new JList(items.toArray(new String[items.size()]));
			JScrollPane scrollPane = new JScrollPane(list);
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPane.setPreferredSize(new Dimension(120, 300));

			// create the list index bar and configure it
			final ListIndexBar bar = new ListIndexBar(items.size());
			bar.setBackground(Color.WHITE);
			bar.setForeground(MARKER_COLOUR);
			bar.setOpaque(false);

			// add a set of example markers
			bar.addMarkers(Arrays.asList(4, 15, 32, 36, 58, 74, 92));

			// add a selection listener to select the corresponding item in the list when the marker is selected
			bar.addSelectionListener(e -> {
				int selectedIndex = e.getFirstIndex();
				System.out.println("index selected " + selectedIndex);
				list.setSelectedIndex(selectedIndex);
				list.ensureIndexIsVisible(selectedIndex);
			});

			// use a custom list cell renderer to highlight those items which have markers in the list index bar
			// list.setCellRenderer(new SubstanceDefaultListCellRenderer() {
			// @Override
			// public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
			// boolean cellHasFocus) {
			// JLabel comp = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			// if (bar.getMarkerSet().contains(index)) {
			// if (isSelected) {
			// comp.setBackground(MARKER_COLOUR);
			// } else {
			// comp.setForeground(MARKER_COLOUR);
			// }
			// }
			// return comp;
			// }
			// });

			// create the frame in which to show everything
			panel.add(scrollPane, BorderLayout.CENTER);
			panel.add(bar, BorderLayout.EAST);

			JFrame frame = new JFrame();
			frame.setContentPane(panel);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.pack();
			frame.setVisible(true);
		});

	}

}

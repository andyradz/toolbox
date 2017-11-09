
import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JComponent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Simple component for displaying a visual representation of the index of a list of items with markers for specific
 * indices in that list. Markers can be clicked on to fire a selection event to interested listeners.
 * <p/>
 * Author: Darren Scott, blog.darrenscott.com
 */
public class ListIndexBar extends JComponent {

	private int itemCount;
	private double scaleFactor;
	private int markerHeight;

	// set of list indices associated with items to be marked
	private Set<Integer> markerSet = new HashSet<>();

	// the index of the currently highlighted marker index
	// gets set when the pointer hovers over a marker and cleared when the mouse is moved off a marker
	// or the pointer leaves the component completely
	private int highlightedIndex = -1;

	// keep track of listeners interested in marker selection events
	private List<ListSelectionListener> listeners = new ArrayList<>();

	public ListIndexBar(int itemCount) {

		this.itemCount = itemCount;
		this.recalc();

		// add a mouse motion listener to track the current highlighted marker
		this.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {

				// calculate the list index which is under the mouse pointer
				int pos = (int) (ListIndexBar.this.itemCount * (e.getPoint()
					.getY() / ListIndexBar.this.getHeight()));
				if (ListIndexBar.this.markerSet.contains(pos)) {
					// we're over one of the markers so record the index and change the cursor
					ListIndexBar.this.highlightedIndex = pos;
					ListIndexBar.this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				} else {
					// we're not over any marker so clear the highlighted index and reset the cursor
					ListIndexBar.this.highlightedIndex = -1;
					ListIndexBar.this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
			}
		});

		// add a mouse listener to handle mouse clicks on markers
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				if (ListIndexBar.this.highlightedIndex != -1) {
					ListSelectionEvent event = new ListSelectionEvent(ListIndexBar.this,
							ListIndexBar.this.highlightedIndex,
							ListIndexBar.this.highlightedIndex,
							false);
					for (ListSelectionListener listener : ListIndexBar.this.listeners)
						listener.valueChanged(event);
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {

				// clear the highlighted index when we leave this component
				ListIndexBar.this.highlightedIndex = -1;
			}
		});

		// give the component a min and preferred size
		this.setMinimumSize(new Dimension(16, 60));
		this.setPreferredSize(new Dimension(16, 60));
	}

	public void addSelectionListener(ListSelectionListener listener) {

		this.listeners.add(listener);
	}

	public void removeSelectionListener(ListSelectionListener listener) {

		this.listeners.remove(listener);
	}

	public int getItemCount() {

		return this.itemCount;
	}

	public void setItemCount(int itemCount) {

		this.itemCount = itemCount;
		this.recalc();
		this.repaint();
	}

	private void recalc() {

		this.scaleFactor = this.getHeight() / (double) this.itemCount;
		this.markerHeight = Math.max(2, (int) this.scaleFactor);
	}

	@Override
	public void setBounds(int x, int y, int width, int height) {

		super.setBounds(x, y, width, height);
		this.recalc();
	}

	@Override
	public void setBounds(Rectangle r) {

		super.setBounds(r);
		this.recalc();
	}

	public Set<Integer> getMarkerSet() {

		return this.markerSet;
	}

	public void addMarkers(Collection<Integer> markers) {

		this.markerSet.addAll(markers);
		this.repaint();
	}

	public void removeMarkers(Collection<Integer> markers) {

		this.markerSet.removeAll(markers);
		this.repaint();
	}

	public void clearMarkers() {

		this.markerSet.clear();
		this.repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {

		// cast to a Graphics2D so we can do more with it
		Graphics2D g2 = (Graphics2D) g;

		// paint or clear the background depending on whether this component is opaque or not
		Composite composite = g2.getComposite();
		g2.setColor(this.getBackground());
		if (!this.isOpaque())
			// if not opaque, set the alpha composite to clear the background
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.DST));
		g2.fillRect(0, 0, this.getWidth(), this.getHeight());
		g2.setComposite(composite);

		// markers will be drawn with the foreground colour
		g2.setColor(this.getForeground());

		int pos;
		for (Integer marker : this.markerSet) {
			// for each marker, calculate the appropriate Y position and paint a marker of required size
			pos = (int) (marker * this.scaleFactor);
			g2.fillRect(0, pos, this.getWidth(), this.markerHeight);
		}
	}

}

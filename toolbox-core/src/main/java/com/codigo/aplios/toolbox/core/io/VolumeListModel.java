/*
 * To change this license header, choose License Headers in Project Properties. To change this template file, choose
 * Tools | Templates and open the template in the editor.
 */
package com.codigo.aplios.toolbox.core.io;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author andrzej.radziszewski
 */
public final class VolumeListModel extends AbstractListModel implements ComboBoxModel {

	private final List<String> volumeList = new ArrayList<>();

	public VolumeListModel() {

		super();
		File[] paths;
		FileSystemView fsv = FileSystemView.getFileSystemView();

		paths = File.listRoots();
		for (File path : paths) {
			this.volumeList.add(path.getPath());
			// prints file and directory paths
			System.out.println("Drive Name: " + path);
			System.out.println("Description: " + fsv.getSystemTypeDescription(path));
		}
	}

	String selection = null;

	@Override
	public Object getElementAt(int index) {

		return this.volumeList.get(index);
	}

	// ------------------------------------------------------------------------------------------------------------------
	@Override
	public int getSize() {

		return this.volumeList.size();
	}

	@Override
	public void setSelectedItem(Object anItem) {

		this.selection = (String) anItem; // to select and register an
	} // item from the pull-down list

	// Methods implemented from the interface ComboBoxModel
	@Override
	public Object getSelectedItem() {

		return this.selection; // to add the selection to the combo box
	}

}

/*
 * To change this license header, choose License Headers in Project Properties. To change this template file, choose
 * Tools | Templates and open the template in the editor.
 */
package com.codigo.aplios.toolbox.core.winapi;

/**
 *
 * @author andrzej.radziszewski
 */
import com.sun.jna.win32.StdCallLibrary;

public interface Kernel321 extends StdCallLibrary {

	public static void main(String[] args) {

	}
	//
	// final static Map<String, Object> WIN32API_OPTIONS = new HashMap<String, Object>() {
	//
	// private static final long serialVersionUID = 1L;
	//
	// {
	// put(Library.OPTION_FUNCTION_MAPPER, W32APIFunctionMapper.UNICODE);
	// put(Library.OPTION_TYPE_MAPPER, W32APITypeMapper.UNICODE);
	// }
	//
	// };
	//
	// public Kernel32 INSTANCE = (Kernel32) Native.loadLibrary("Kernel32", Kernel32.class, WIN32API_OPTIONS);
	//
	// /*
	// * BOOL WINAPI GetVolumeInformation( __in_opt LPCTSTR lpRootPathName, __out LPTSTR lpVolumeNameBuffer, __in DWORD
	// * nVolumeNameSize, __out_opt LPDWORD lpVolumeSerialNumber, __out_opt LPDWORD lpMaximumComponentLength, __out_opt
	// * LPDWORD lpFileSystemFlags, __out LPTSTR lpFileSystemNameBuffer, __in DWORD nFileSystemNameSize );
	// */
	// public boolean GetVolumeInformation(
	// String lpRootPathName,
	// char[] lpVolumeNameBuffer,
	// DWORD nVolumeNameSize,
	// IntByReference lpVolumeSerialNumber,
	// IntByReference lpMaximumComponentLength,
	// IntByReference lpFileSystemFlags,
	// char[] lpFileSystemNameBuffer,
	// DWORD nFileSystemNameSize
	// );

	// public DWORD GetFileSize(HANDLE hFile, LPDWORD lpFileSizeHigh);
	//
	// public boolean GetFileSizeEx(HANDLE hFile, PLARGE_INTEGER lpFileSize);
	public int GetLastError();

}

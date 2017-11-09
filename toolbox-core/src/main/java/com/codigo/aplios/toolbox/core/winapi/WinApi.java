package com.codigo.aplios.toolbox.core.winapi;

import java.io.File;
import java.io.IOException;

import com.sun.jna.Memory;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinBase;
import com.sun.jna.platform.win32.WinBase.FILE_ATTRIBUTE_TAG_INFO;
import com.sun.jna.platform.win32.WinBase.FILE_BASIC_INFO;
import com.sun.jna.platform.win32.WinBase.FILE_COMPRESSION_INFO;
import com.sun.jna.platform.win32.WinBase.FILE_ID_INFO;
import com.sun.jna.platform.win32.WinBase.FILE_STANDARD_INFO;
import com.sun.jna.platform.win32.WinBase.SYSTEMTIME;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.win32.StdCallLibrary;

public class WinApi {

	private final User32 user32;

	interface User32 extends StdCallLibrary {

		User32 INSTANCE = null;// (User32)Native.loadLibrary("user32", User32.class);

		int WM_GETTEXT = 0x000D;

		int WM_GETTEXTLENGTH = 0x000E;

		int WM_SYSCOMMAND = 0x0112;

		int SC_CLOSE = 0xF060;

		WinDef.HWND FindWindowA(String lpClassName, String lpWindowName);

		WinDef.HWND FindWindowExA(WinDef.HWND hwndParent, WinDef.HWND hwndChildAfter, String lpClassName,
				String lpWindowName);

		WinDef.LRESULT SendMessageA(WinDef.HWND editHwnd, int wmGettext, long l, byte[] lParamStr);

	}

	public WinApi() {

		this.user32 = User32.INSTANCE;
	}

	public HWND getNotepadEditHWND() {

		WinDef.HWND notepadHWND = this.user32.FindWindowA(null, "Kalkulator");
		// close the window using API
		// SendMessage(iHandle, WM_SYSCOMMAND, SC_CLOSE, 0);

		return this.user32.FindWindowExA(notepadHWND, null, "Kalkulator", null);
	}

	public int getTextLengthInChars(WinDef.HWND hwnd) {

		return this.user32.SendMessageA(hwnd, User32.WM_GETTEXTLENGTH, 0, null)
			.intValue();
	}

	public String getText(WinDef.HWND hwnd) {

		int textLength = this.getTextLengthInChars(hwnd);
		byte[] result = new byte[textLength];
		this.user32.SendMessageA(hwnd, User32.WM_GETTEXT, textLength + 1, result);
		// return Native.toString(result, "CP1250");
		return null;
	}

	public static void main(String[] args) throws IOException {

		Kernel32 klib = Kernel32.INSTANCE;
		WinNT.OSVERSIONINFOEX lpVersionInfo = new WinNT.OSVERSIONINFOEX();
		klib.GetVersionEx(lpVersionInfo);

		WinBase.SECURITY_ATTRIBUTES sa = new WinBase.SECURITY_ATTRIBUTES();
		boolean result = klib.CreateDirectory("D://winapi", sa);

		File tmp = File.createTempFile("testGetFileInformationByHandleEx", "jna");
		tmp.deleteOnExit();

		SYSTEMTIME st = new SYSTEMTIME();
		klib.GetSystemTime(st);

		WinNT.HANDLE hFile = Kernel32.INSTANCE.CreateFile(tmp.getAbsolutePath(),
				WinNT.GENERIC_WRITE,
				WinNT.FILE_SHARE_WRITE,
				new WinBase.SECURITY_ATTRIBUTES(),
				WinNT.OPEN_EXISTING,
				WinNT.FILE_ATTRIBUTE_NORMAL,
				null);

		Memory p = new Memory(FILE_BASIC_INFO.sizeOf());
		if (false == Kernel32.INSTANCE
			.GetFileInformationByHandleEx(hFile, WinBase.FileBasicInfo, p, new WinDef.DWORD(p.size()))) {

		}

		FILE_BASIC_INFO fbi = new FILE_BASIC_INFO(p);
		fbi.CreationTime.getValue();

		FILE_STANDARD_INFO fsi = new FILE_STANDARD_INFO(p);
		// New file has 1 link

		p = new Memory(FILE_COMPRESSION_INFO.sizeOf());
		if (false == Kernel32.INSTANCE
			.GetFileInformationByHandleEx(hFile, WinBase.FileCompressionInfo, p, new DWORD(p.size()))) {

		}
		FILE_COMPRESSION_INFO fci = new FILE_COMPRESSION_INFO(p);
		// Uncompressed file should be zero

		p = new Memory(FILE_ATTRIBUTE_TAG_INFO.sizeOf());
		if (false == Kernel32.INSTANCE
			.GetFileInformationByHandleEx(hFile, WinBase.FileAttributeTagInfo, p, new DWORD(p.size()))) {

		}
		FILE_ATTRIBUTE_TAG_INFO fati = new FILE_ATTRIBUTE_TAG_INFO(p);
		// New files have the archive bit

		p = new Memory(FILE_ID_INFO.sizeOf());
		if (false == Kernel32.INSTANCE
			.GetFileInformationByHandleEx(hFile, WinBase.FileIdInfo, p, new DWORD(p.size()))) {

		}
		FILE_ID_INFO fii = new FILE_ID_INFO(p);
		// Volume serial number should be non-zero

	}

}

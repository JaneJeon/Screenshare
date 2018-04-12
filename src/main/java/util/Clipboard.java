package util;

import java.awt.*;
import java.awt.datatransfer.StringSelection;

public class Clipboard {
	public static void copy(String string) {
		var selection = new StringSelection(string);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, selection);
	}
}
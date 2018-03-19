package util;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClipboardTest {
	private static final String text = "Hello, world!";
	
	@Test
	void copy() throws IOException, UnsupportedFlavorException {
		Clipboard.copy(text);
		assertEquals(text, Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor));
	}
}
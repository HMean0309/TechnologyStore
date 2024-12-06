package GUI.Component;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class NumericDocumentFilter extends DocumentFilter {
    private final String regex = "\\d*\\.?\\d*"; // Biểu thức chính quy cho số hợp lệ

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        if (string == null) return;

        String newText = fb.getDocument().getText(0, fb.getDocument().getLength());
        newText = newText.substring(0, offset) + string + newText.substring(offset);

        if (newText.matches(regex) || newText.isEmpty()) {
            super.insertString(fb, offset, string, attr);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if (text == null) return;

        String newText = fb.getDocument().getText(0, fb.getDocument().getLength());
        newText = newText.substring(0, offset) + text + newText.substring(offset + length);

        if (newText.matches(regex) || newText.isEmpty()) {
            super.replace(fb, offset, length, text, attrs);
        }
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
        String newText = fb.getDocument().getText(0, fb.getDocument().getLength());
        newText = newText.substring(0, offset) + newText.substring(offset + length);

        if (newText.matches(regex) || newText.isEmpty()) {
            super.remove(fb, offset, length);
        }
    }
}


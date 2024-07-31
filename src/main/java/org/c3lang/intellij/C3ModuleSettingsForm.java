package org.c3lang.intellij;

import com.intellij.ui.ComboboxWithBrowseButton;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.ui.components.JBLabel;

import javax.swing.*;

public class C3ModuleSettingsForm {
    private JPanel mainPanel;
    private JTextField textField1;
    private ComboboxWithBrowseButton comboboxWithBrowseButton1;
    private TextFieldWithBrowseButton textFieldWithBrowseButton1;

    public JComponent getComponent() {
        return mainPanel;
    }
}

package org.c3lang.intellij;

import com.intellij.openapi.ui.TextFieldWithBrowseButton;

import javax.swing.*;

public class C3ModuleSettingsForm {
    private JPanel mainPanel;
    private JTextField textField1;
    private TextFieldWithBrowseButton comboboxWithBrowseButton1;
    private TextFieldWithBrowseButton cUsersZeejfLibraryTextFieldWithBrowseButton;

    public JComponent getComponent() {
        return mainPanel;
    }
}

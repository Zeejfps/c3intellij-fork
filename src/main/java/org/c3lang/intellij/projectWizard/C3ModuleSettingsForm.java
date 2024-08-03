package org.c3lang.intellij.projectWizard;

import com.intellij.openapi.ui.TextFieldWithBrowseButton;

import javax.swing.*;

public class C3ModuleSettingsForm {
    private JPanel mainPanel;
    private JTextField projectNameField;
    private TextFieldWithBrowseButton compilerPathField;
    private TextFieldWithBrowseButton projectLocationPathField;

    public JComponent getComponent() {
        return mainPanel;
    }
}

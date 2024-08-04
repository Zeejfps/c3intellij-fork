package org.c3lang.intellij.projectWizard;

import com.intellij.ide.util.projectWizard.SettingsStep;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.openapi.ui.ValidationInfo;
import com.intellij.platform.ProjectGeneratorPeer;
import com.intellij.ui.DocumentAdapter;
import com.intellij.ui.EnumComboBoxModel;
import com.intellij.ui.components.JBTextField;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class C3ProjectGeneratorPeer implements ProjectGeneratorPeer<C3ProjectGeneratorSettings> {

    private final JBTextField nameField;
    private final ComboBox<C3ProjectKind> projectKindComboBox;
    private final List<SettingsListener> settingsListeners;
    private final TextFieldWithBrowseButton compilerField;
    private final C3ProjectGeneratorSettings settings;
    
    public C3ProjectGeneratorPeer() {
        settingsListeners = new ArrayList<>();
        settings = new C3ProjectGeneratorSettings();

        nameField = new JBTextField();
        nameField.getDocument().addDocumentListener(new DocumentAdapter() {
            @Override
            protected void textChanged(@NotNull DocumentEvent documentEvent) {
                NotifySettingsChanged();
                settings.setProjectName(nameField.getText());
            }
        });

        projectKindComboBox = new ComboBox<>(new EnumComboBoxModel<>(C3ProjectKind.class));
        projectKindComboBox.addActionListener(e -> {
            settings.setProjectKind(projectKindComboBox.getItem());
        });

        compilerField = new TextFieldWithBrowseButton();
    }

    @Override
    public @NotNull JComponent getComponent() {
        return null;
    }

    @Override
    public void buildUI(@NotNull SettingsStep settingsStep) {
        settingsStep.addSettingsField("Name", nameField);
        settingsStep.addSettingsField("Kind", projectKindComboBox);
        settingsStep.addSettingsField("Compiler", compilerField);
    }
    
    private void NotifySettingsChanged() {
        for (var settingsListener : settingsListeners) {
            settingsListener.stateChanged(true);
        }
    }

    @Override
    public @NotNull C3ProjectGeneratorSettings getSettings() {
        return settings;
    }

    @Override
    public @Nullable ValidationInfo validate() {
        String name = nameField.getText();
        if (name == null || name.isEmpty())
            return new ValidationInfo("Project name can not be empty", nameField);

        String regex = "^[a-zA-Z0-9_]+$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(name);

        if (!matcher.matches()) {
            return new ValidationInfo("Project name can only have letters, numbers, and _", nameField);
        } 
        
        return null;
    }

    @Override
    public void addSettingsListener(@NotNull final SettingsListener listener) {
        settingsListeners.add(listener);
    }

    @Override
    public boolean isBackgroundJobRunning() {
        return false;
    }
}

package org.c3lang.intellij.projectWizard;

import com.intellij.ide.util.projectWizard.ModuleNameLocationSettings;
import com.intellij.ide.util.projectWizard.SettingsStep;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.openapi.ui.ValidationInfo;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.platform.ProjectGeneratorPeer;
import com.intellij.ui.DocumentAdapter;
import com.intellij.ui.EnumComboBoxModel;
import org.c3lang.intellij.C3SettingsState;
import org.c3lang.intellij.sdk.C3SdkType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class C3ProjectGeneratorPeer implements ProjectGeneratorPeer<C3ProjectGeneratorSettings> {

    private static final Pattern moduleNamePattern = Pattern.compile("^[a-zA-Z0-9_]+$");

    private final ComboBox<C3ProjectKind> projectKindComboBox;
    private final List<SettingsListener> settingsListeners;
    private final TextFieldWithBrowseButton compilerField;
    private final C3ProjectGeneratorSettings settings;
    
    private SettingsStep settingsStep;
    
    public C3ProjectGeneratorPeer() {
        settingsListeners = new ArrayList<>();
        settings = new C3ProjectGeneratorSettings();
        
        projectKindComboBox = new ComboBox<>(new EnumComboBoxModel<>(C3ProjectKind.class));
        projectKindComboBox.addActionListener(e -> {
            settings.setProjectKind(projectKindComboBox.getItem());
        });

        compilerField = new TextFieldWithBrowseButton();
        compilerField.addBrowseFolderListener("Select C3 Compiler", "", null, new FileChooserDescriptor(true, false, false, false, false, false)
        {
            @Override
            public boolean isFileSelectable(@Nullable VirtualFile file)
            {
                if (file == null) return false;
                if (!file.exists()) return false;
                if (file.isDirectory()) return false;
                if (!Files.isExecutable(file.toNioPath())) return false;
                return C3SdkType.isSdkHome(file.getParent().getPath());
            }
        });
        compilerField.getTextField().getDocument().addDocumentListener(new DocumentAdapter() {

            @Override
            protected void textChanged(@NotNull DocumentEvent documentEvent) {
                NotifySettingsChanged();
                C3SettingsState.getInstance().sdk = compilerField.getText();
            }
        });
    }

    @Override
    public @NotNull JComponent getComponent() {
        return null;
    }

    @Override
    public void buildUI(@NotNull SettingsStep settingsStep) {
        this.settingsStep = settingsStep;
        settingsStep.addSettingsField("Kind", projectKindComboBox);
        settingsStep.addSettingsField("Compiler", compilerField);
        
        C3SettingsState settingsState = C3SettingsState.getInstance();
        compilerField.setText(settingsState.sdk);
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
        ModuleNameLocationSettings nameLocationSettings = settingsStep.getModuleNameLocationSettings();
        if (nameLocationSettings == null) {
            throw new RuntimeException("No name or location settings found");
        }
        
        String name = nameLocationSettings.getModuleName();
        if (name.isEmpty())
            return new ValidationInfo("Project name can not be empty");

        Matcher matcher = moduleNamePattern.matcher(name);
        if (!matcher.matches()) {
            return new ValidationInfo("Project name can only have letters, numbers, and _");
        } 
        
        Path pathToCompilerExe = Path.of(compilerField.getText());
        if (!C3SdkType.isSdkHome(pathToCompilerExe.getParent().toString())) {
            return new ValidationInfo("Project compiler location is not valid");
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

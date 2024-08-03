package org.c3lang.intellij.projectWizard;

import com.intellij.ide.util.projectWizard.SettingsStep;
import com.intellij.openapi.ui.ValidationInfo;
import com.intellij.platform.ProjectGeneratorPeer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class C3ProjectGeneratorPeer implements ProjectGeneratorPeer<C3ProjectGeneratorSettings> {

    @Override
    public @NotNull JComponent getComponent() {
        return null;
    }

    @Override
    public void buildUI(@NotNull SettingsStep settingsStep) {

    }

    @Override
    public @NotNull C3ProjectGeneratorSettings getSettings() {
        return null;
    }

    @Override
    public @Nullable ValidationInfo validate() {
        return null;
    }

    @Override
    public boolean isBackgroundJobRunning() {
        return false;
    }
}

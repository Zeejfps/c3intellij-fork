package org.c3lang.intellij.projectWizard;

import com.intellij.ide.util.projectWizard.SettingsStep;
import com.intellij.openapi.ui.DialogPanel;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.openapi.ui.ValidationInfo;
import com.intellij.platform.ProjectGeneratorPeer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class C3ProjectGeneratorPeer implements ProjectGeneratorPeer<C3ProjectGeneratorSettings> {

    @Override
    public @NotNull JComponent getComponent() {
        System.out.println("GET COMPONENT");
        DialogPanel panel = UitestKt.demoBasics();
        return panel;
//        return null;
    }

    @Override
    public void buildUI(@NotNull SettingsStep settingsStep) {
        System.out.println("BUILD UI");
        DialogPanel panel = UitestKt.demoBasics();
        JComponent test = (JComponent)panel.getComponent(1);
        settingsStep.addSettingsComponent(test);
        settingsStep.addSettingsField("Test", test);
    }

    @Override
    public @NotNull C3ProjectGeneratorSettings getSettings() {
        return new C3ProjectGeneratorSettings();
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

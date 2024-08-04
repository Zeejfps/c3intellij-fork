package org.c3lang.intellij;

import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.openapi.util.NlsContexts;
import com.intellij.openapi.vfs.VirtualFile;
import org.c3lang.intellij.sdk.C3SdkType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Files;

public class C3CompilerConfigurable implements SearchableConfigurable, Configurable.NoScroll
{
    private JPanel panel;
    private TextFieldWithBrowseButton pathField;

    public C3CompilerConfigurable()
    {
    }

    @Override public @NotNull @NonNls String getId()
    {
        return "org.c3lang.compiler";
    }

    @Override public @NlsContexts.ConfigurableName String getDisplayName()
    {
        return "C3 Compiler";
    }

    @Override public @Nullable JComponent createComponent()
    {
        panel = new JPanel(new BorderLayout(10, 5));
        final JPanel contentPanel = new JPanel(new BorderLayout(4, 0));
        panel.add(contentPanel, BorderLayout.NORTH);
        contentPanel.add(new JLabel("C3 Compiler"), BorderLayout.WEST);
        pathField = new TextFieldWithBrowseButton();
        contentPanel.add(pathField);
        pathField.addBrowseFolderListener("Select C3 Compiler", "", null, new FileChooserDescriptor(true, false, false, false, false, false)
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
        return panel;
    }

    @Override public boolean isModified()
    {
        String sdk = C3SettingsState.getInstance().sdk;
        return !pathField.getText().equals(sdk);
    }


    @Override public void apply()
    {
        C3SettingsState.getInstance().sdk = pathField.getText();
    }

    @Override public void reset()
    {
        pathField.setText(C3SettingsState.getInstance().sdk);
    }
}

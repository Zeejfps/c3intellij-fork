package org.c3lang.intellij.projectWizard;

import com.intellij.ide.util.projectWizard.SettingsStep;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.openapi.ui.DialogPanel;
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
import javax.swing.event.DocumentListener;
import javax.swing.event.EventListenerList;
import javax.swing.text.AbstractDocument;
import javax.swing.text.Document;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class C3ProjectGeneratorPeer implements ProjectGeneratorPeer<C3ProjectGeneratorSettings> {

    private JBTextField nameField;
    private List<SettingsListener> settingsListeners;
    
    public C3ProjectGeneratorPeer() {
        nameField = new JBTextField();
        settingsListeners = new ArrayList<>();

    }
    
    @Override
    public @NotNull JComponent getComponent() {
        System.out.println("GET COMPONENT");
        DialogPanel panel = UitestKt.demoBasics();
        return panel;
//        return null;
    }

    @Override
    public @NotNull JComponent getComponent(@NotNull TextFieldWithBrowseButton myLocationField, @NotNull Runnable checkValid) {
        System.out.println("GetCompoent " + myLocationField);
        return ProjectGeneratorPeer.super.getComponent(myLocationField, checkValid);
    }

    @Override
    public void buildUI(@NotNull SettingsStep settingsStep) {
        System.out.println("BUILD UI");
        nameField.getDocument().addDocumentListener(new DocumentAdapter() {
            @Override
            protected void textChanged(@NotNull DocumentEvent documentEvent) {
                for (var settingsListener : settingsListeners) {
                    settingsListener.stateChanged(true);
                }
//                JComponent parent = nameField.getRootPane();
//                System.out.println(parent.getClass());
//                //DialogPanel panel = (DialogPanel) parent;
//                
//                Component[] components = parent.getComponents();
//                printComponents(components, 0);
            }
        });
        settingsStep.addSettingsField("Name", nameField);
        ComboBox<C3ProjectKind> comboBox = new ComboBox<>(new EnumComboBoxModel<>(C3ProjectKind.class));
        settingsStep.addSettingsField("Kind", comboBox);
    }
    
    private void printComponents(Component[] components, int indent) {
        for (int i = 0; i < components.length; i++) {
            Component cmp = components[i];
            if (cmp instanceof JComponent) {
                JComponent component = (JComponent) cmp;
                for (int j = 0; j < indent; j++) {
                    System.out.print("-");
                }
                
                System.out.println(component.getClass());
                if (cmp instanceof JBTextField) {
                    System.out.println("Text: " + ((JBTextField) cmp).getText());
                    try {
                        printDocumentListeners(((JTextField) cmp).getDocument());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                Component[] children = component.getComponents();
                printComponents(children, indent + 1);
            }
        }
    }
    public static void printDocumentListeners(Document doc) throws Exception {
        if (doc instanceof AbstractDocument) {
            DocumentListener[] listeners = ((AbstractDocument) doc).getDocumentListeners();
            // find listener in array and remove
            System.out.println("Listeners attached to Document:");
            for (int i = 0; i < listeners.length; i += 2) {
                DocumentListener listener = (DocumentListener) listeners[i + 1];
                System.out.println("Listener: " + listener.toString());
            }
        }
        // Print the listeners
        
    }
    
    
    public static void printListeners(JTextField textField) throws Exception {
        // Get the class of the JTextField
        Class<?> clazz = textField.getClass();

        // Get the private 'listenerList' field
        Field listenerListField = JComponent.class.getDeclaredField("listenerList");
        listenerListField.setAccessible(true);

        // Get the listener list from the JTextField instance
        EventListenerList listenerList = (EventListenerList) listenerListField.get(textField);

        // Get the array of listener pairs from the listener list
        Object[] listeners = listenerList.getListenerList();

        // Print the listeners
        System.out.println("Listeners attached to JTextField: " + listeners.length);
        for (int i = 0; i < listeners.length; i += 2) {
            Class<?> listenerType = (Class<?>) listeners[i];
            EventListener listener = (EventListener) listeners[i + 1];
            System.out.println("Listener type: " + listenerType.getName());
            System.out.println("Listener: " + listener.toString());
        }
    }
    

    @Override
    public @NotNull C3ProjectGeneratorSettings getSettings() {
        return new C3ProjectGeneratorSettings();
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

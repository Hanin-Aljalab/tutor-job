package view.swing.components;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import view.swing.components.*;
import model.Student;
import model.*;
import controller.*;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

//TODO anpassen an neuste Version

public class PreferencePopupTest {

    private PreferencePopup preferencePopup;
    private Student student;

    @BeforeEach
    public void setUp() {
        student = new Student("Hanin", "Aljalab", "password", "123456", "MA1");
        preferencePopup = new PreferencePopup(student);
    }

}
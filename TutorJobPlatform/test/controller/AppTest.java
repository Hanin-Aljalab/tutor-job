package controller;

import model.AppData;
import org.junit.jupiter.api.*;
import java.io.*;

import static controller.App.*;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    private String someField;
    private static final String TEST_FILE_PATH = "testfile.ser";
    private static AppData data;

    @BeforeAll
    public static void setUp() {
        // Initialize the data object with test data
        data = new AppData();
        data.setSomeFieldForTest("Test");
    }

    @AfterAll
    public static void tearDown() {
        // Delete the test file after tests are done
        File file = new File(TEST_FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testSerializeObjects() {
        // Call the method to serialize the object
        serializeObjects();

        // Now deserialize the object to check if it was serialized correctly
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(TEST_FILE_PATH))) {
            AppData deserializedData = (AppData) in.readObject();
            assertNotNull(deserializedData, "Deserialized data should not be null");
            assertEquals(data.getSomeFieldForTest(), deserializedData.getSomeFieldForTest(), "Fields should match after serialization and deserialization");
        } catch (IOException | ClassNotFoundException e) {
            fail("Exception should not have been thrown: " + e.getMessage());
        }
    }
}

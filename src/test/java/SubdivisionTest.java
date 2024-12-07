import org.example.Subdivision;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubdivisionTest {

    @Test
    void testSubdivisionCreation() {
        Subdivision subdivision = new Subdivision("001", "Finance");

        assertEquals("001", subdivision.getId());
        assertEquals("Finance", subdivision.getName());
    }

    @Test
    void testToString() {
        Subdivision subdivision = new Subdivision("002", "Marketing");

        String expected = "Subdivision{id='002', name='Marketing'}";
        assertEquals(expected, subdivision.toString());
    }
}

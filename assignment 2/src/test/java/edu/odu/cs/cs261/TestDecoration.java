package edu.odu.cs.cs261;

//import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


//import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestDecoration {
    @Test
    public void testDecorationConstructor() {
        Decoration d1 = new Decoration(2, 1.0);
        assertThat(d1.getWeight(), is(1.0));
        assertThat(d1.getWidth(), is(0.0));
    }
}

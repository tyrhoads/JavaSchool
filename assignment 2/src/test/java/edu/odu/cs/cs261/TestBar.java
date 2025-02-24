package edu.odu.cs.cs261;

//import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


//import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestBar {
    
    @Test
    public void testBarConstructor() {
        Decoration d1 = new Decoration(2, 1.0);
        Decoration d2 = new Decoration(3, 4.0);
        Bar bar = new Bar(1, 10.0, d1, d2);
        assertThat(bar.getWeight(), is(d1.getWeight() + d2.getWeight()));
        assertThat(bar.getWidth(), is(10.0));
    }
}

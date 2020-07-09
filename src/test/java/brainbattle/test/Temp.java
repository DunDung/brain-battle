package brainbattle.test;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Temp {
    @BeforeEach
    void setUp() {
        System.out.println(1);
    }

    @Test
    void name() {
        System.out.println(2);
        assertThat(1).isEqualTo(1);
    }
}

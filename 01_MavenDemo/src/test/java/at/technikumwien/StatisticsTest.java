package at.technikumwien;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StatisticsTest {
    private Statistics statistics;

    @BeforeEach
    public void setUp() {
        statistics = new Statistics(1, 2, 3);
    }

    @Test
    public void testCnt() {
        assertEquals(3, statistics.cnt());
    }

    @Test
    public void testSum() {
        assertEquals(6, statistics.sum());
    }

    @Test
    public void testAvg() {
        assertEquals(2, statistics.avg());
    }
}

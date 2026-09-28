package wildlifeproject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
public class WildlifeRescueSystemTest {
    private ArrayList<RescueCase> testList;

    @BeforeEach
    public void setUp() {
        testList = new ArrayList<>();
    }

    @Test
    public void testRescueCostCalculations() {
        InjuredAnimalRescue case1 = new InjuredAnimalRescue("WR101", "N/A", "Lion", "Loc", "Ranger", 10, 100.0, "Rescued", "Cut", 1000.0, true);
        assertEquals(7000.0, case1.calculateTotalCost(), 0.01);
    }

    @Test
    public void testRescuePriorityCalculations() {
        InjuredAnimalRescue case1 = new InjuredAnimalRescue("WR101", "N/A", "Lion", "Loc", "Ranger", 10, 100.0, "Rescued", "Cut", 1000.0, true);
        assertEquals("Critical", case1.determinePriority());
    }

    @Test
    public void testRescueStatusUpdates() {
        InjuredAnimalRescue case1 = new InjuredAnimalRescue("WR101", "N/A", "Lion", "Loc", "Ranger", 10, 100.0, "Rescued", "Cut", 1000.0, false);
        case1.setCurrentRescueStatus("Completed");
        assertEquals("Completed", case1.getCurrentRescueStatus());
    }

    @Test
    public void testSearchingForExistingRescueCase() {
        InjuredAnimalRescue case1 = new InjuredAnimalRescue("WR101", "Leo", "Lion", "Loc", "Ranger", 10, 100.0, "Rescued", "Cut", 1000.0, false);
        testList.add(case1);
        
        RescueCase result = null;
        for (RescueCase rc : testList) {
            if (rc.getRescueCaseId().equalsIgnoreCase("WR101")) {
                result = rc;
                break;
            }
        }
        assertNotNull(result);
        assertEquals("Leo", result.getAnimalName());
    }

    @Test
    public void testPreventingDuplicateRescueCaseIds() {
        testList.add(new InjuredAnimalRescue("WR101", "N/A", "Lion", "Loc", "Ranger", 10, 100.0, "Rescued", "Cut", 1000.0, false));
        
        String testId = "WR101";
        boolean unique = true;
        for (RescueCase rc : testList) {
            if (rc.getRescueCaseId().equalsIgnoreCase(testId)) {
                unique = false;
                break;
            }
        }
        assertFalse(unique);
    }

    private void assertEquals(double d, double calculateTotalCost, double d0) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}


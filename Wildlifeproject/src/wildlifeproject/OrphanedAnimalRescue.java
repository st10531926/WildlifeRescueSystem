package wildlifeproject;
public class OrphanedAnimalRescue extends RescueCase {
    private int estimatedAgeMonths;
    private double feedingCost;
    private boolean fosterCareRequired;

    public OrphanedAnimalRescue(String rescueCaseId, String animalName, String species, 
                                String rescueLocation, String assignedRanger, int numberOfRescueDays, 
                                double dailyCareCost, String currentRescueStatus, int estimatedAgeMonths, 
                                double feedingCost, boolean fosterCareRequired) {
        super(rescueCaseId, animalName, species, rescueLocation, assignedRanger, numberOfRescueDays, dailyCareCost, currentRescueStatus);
        this.estimatedAgeMonths = estimatedAgeMonths;
        this.feedingCost = feedingCost;
        this.fosterCareRequired = fosterCareRequired;
    }

    @Override
    public String getRescueType() { 
        return "Orphaned Animal Rescue"; 
    }

    @Override
    public double calculateTotalCost() {
        double baseCost = getNumberOfRescueDays() * getDailyCareCost();
        double total = baseCost + feedingCost;
        if (fosterCareRequired) {
            total += 2500.0;
        }
        return total;
    }

    @Override
    public String determinePriority() {
        return (estimatedAgeMonths <= 3 || fosterCareRequired) ? "High" : "Medium";
    }
}


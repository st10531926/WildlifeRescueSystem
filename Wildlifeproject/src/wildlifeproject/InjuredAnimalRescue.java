package wildlifeproject;
public class InjuredAnimalRescue extends RescueCase {
    private String injuryDescription;
    private double veterinaryTreatmentCost;
    private boolean surgeryRequired;

    public InjuredAnimalRescue(String rescueCaseId, String animalName, String species, 
                               String rescueLocation, String assignedRanger, int numberOfRescueDays, 
                               double dailyCareCost, String currentRescueStatus, String injuryDescription, 
                               double veterinaryTreatmentCost, boolean surgeryRequired) {
        super(rescueCaseId, animalName, species, rescueLocation, assignedRanger, numberOfRescueDays, dailyCareCost, currentRescueStatus);
        this.injuryDescription = injuryDescription;
        this.veterinaryTreatmentCost = veterinaryTreatmentCost;
        this.surgeryRequired = surgeryRequired;
    }

    @Override
    public String getRescueType() { 
        return "Injured Animal Rescue"; 
    }

    @Override
    public double calculateTotalCost() {
        double baseCost = getNumberOfRescueDays() * getDailyCareCost();
        double total = baseCost + veterinaryTreatmentCost;
        if (surgeryRequired) {
            total += 5000.0;
        }
        return total;
    }

    @Override
    public String determinePriority() {
        return surgeryRequired ? "Critical" : "Medium";
    }
}


package wildlifeproject;
public class EndangeredSpeciesRescue extends RescueCase {
    private String conservationClassification;
    private double securityCost;
    private boolean specialistTeamRequired;

    public EndangeredSpeciesRescue(String rescueCaseId, String animalName, String species, 
                                   String rescueLocation, String assignedRanger, int numberOfRescueDays, 
                                   double dailyCareCost, String currentRescueStatus, String conservationClassification, 
                                   double securityCost, boolean specialistTeamRequired) {
        super(rescueCaseId, animalName, species, rescueLocation, assignedRanger, numberOfRescueDays, dailyCareCost, currentRescueStatus);
        this.conservationClassification = conservationClassification;
        this.securityCost = securityCost;
        this.specialistTeamRequired = specialistTeamRequired;
    }

    @Override
    public String getRescueType() { 
        return "Endangered Species Rescue"; 
    }

    @Override
    public double calculateTotalCost() {
        double baseCost = getNumberOfRescueDays() * getDailyCareCost();
        double total = baseCost + securityCost;
        if (specialistTeamRequired) {
            total += 8000.0;
        }
        return total;
    }

    @Override
    public String determinePriority() {
        if (conservationClassification.equalsIgnoreCase("Critical") || specialistTeamRequired) {
            return "Critical";
        }
        return "High";
    }
}


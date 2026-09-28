package wildlifeproject;
public abstract class RescueCase implements RescueOperations {
    private String rescueCaseId;
    private String animalName;
    private String species;
    private String rescueLocation;
    private String assignedRanger;
    private int numberOfRescueDays;
    private double dailyCareCost;
    private String currentRescueStatus;

    public RescueCase(String rescueCaseId, String animalName, String species, 
                      String rescueLocation, String assignedRanger, 
                      int numberOfRescueDays, double dailyCareCost, String currentRescueStatus) {
        this.rescueCaseId = rescueCaseId;
        this.animalName = animalName;
        this.species = species;
        this.rescueLocation = rescueLocation;
        this.assignedRanger = assignedRanger;
        this.numberOfRescueDays = numberOfRescueDays;
        this.dailyCareCost = dailyCareCost;
        this.currentRescueStatus = currentRescueStatus;
    }

    // Getters and Setters
    public String getRescueCaseId() { return rescueCaseId; }
    public String getAnimalName() { return animalName; }
    public String getSpecies() { return species; }
    public String getRescueLocation() { return rescueLocation; }
    public String getAssignedRanger() { return assignedRanger; }
    public int getNumberOfRescueDays() { return numberOfRescueDays; }
    public double getDailyCareCost() { return dailyCareCost; }
    public String getCurrentRescueStatus() { return currentRescueStatus; }
    
    public void setCurrentRescueStatus(String currentRescueStatus) { 
        this.currentRescueStatus = currentRescueStatus; 
    }

    // Default interface operations implementation
    @Override
    public void startRescueOperation() {
        this.currentRescueStatus = "Rescue in Progress";
    }

    @Override
    public void completeRescueOperation() {
        this.currentRescueStatus = "Completed";
    }

    @Override
    public String generateRescueSummary() {
        return String.format(
            "Case ID: %s | Type: %s | Species: %s | Status: %s | Cost: R%,.2f",
            rescueCaseId, getRescueType(), species, currentRescueStatus, calculateTotalCost()
        );
    }

    // Abstract methods to implement polymorphism
    public abstract String getRescueType();
    public abstract double calculateTotalCost();
    public abstract String determinePriority();
}

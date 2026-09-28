package wildlifeproject;
import java.util.ArrayList;
import java.util.Scanner;

public class WildlifeRescueSystem {
    private static ArrayList<RescueCase> rescueCases = new ArrayList<>();
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        // Pre-populating matching data for testing output formats
        rescueCases.add(new InjuredAnimalRescue("WR101", "N/A", "African Elephant", "Sector 4", "Ranger John", 10, 1050.0, "Rescue in Progress", "Fractured leg", 5000.0, true));
        rescueCases.add(new OrphanedAnimalRescue("WR102", "N/A", "White Rhino", "Sector 2", "Ranger Smith", 15, 400.0, "Under Observation", 2, 3000.0, true));

        int choice = 0;
        while (choice != 6) {
            System.out.println("========================================");
            System.out.println("WILDLIFE RESCUE OPERATIONS SYSTEM");
            System.out.println("========================================");
            System.out.println("1. Create Rescue Case");
            System.out.println("2. Search Rescue Case");
            System.out.println("3. Update Rescue Status");
            System.out.println("4. Display All Rescue Cases");
            System.out.println("5. Rescue Report");
            System.out.println("6. Exit");
            System.out.print("\nEnter your choice: ");
            
            try {
                choice = Integer.parseInt(input.nextLine());
                switch (choice) {
                    case 1: createRescueCase(); break;
                    case 2: searchRescueCase(); break;
                    case 3: updateRescueStatus(); break;
                    case 4: displayAllCases(); break;
                    case 5: generateRescueReport(); break;
                    case 6: System.out.println("Exiting System... Goodbye!"); break;
                    default: System.out.println("Invalid choice. Select options 1-6.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Validation Error: Please enter a valid choice integer number.");
            }
            System.out.println();
        }
    }

    private static void createRescueCase() {
        System.out.println("\n--- Select Rescue Type ---");
        System.out.println("1. Injured Animal Rescue");
        System.out.println("2. Orphaned Animal Rescue");
        System.out.println("3. Endangered Species Rescue");
        int type = getValidMenuChoice(1, 3);

        String id;
        while (true) {
            id = getNonBlankInput("Enter Case ID: ");
            if (isIdUnique(id)) break;
            System.out.println("Validation Error: This ID already exists. Try a unique ID.");
        }

        String name = getNonBlankInput("Enter Animal Name: ");
        String species = getNonBlankInput("Enter Species: ");
        String loc = getNonBlankInput("Enter Rescue Location: ");
        String ranger = getNonBlankInput("Enter Assigned Ranger: ");
        int days = getPositiveIntInput("Enter Number of Rescue Days: ");
        double dailyCost = getPositiveDoubleInput("Enter Daily Care Cost (R): ");
        String status = getNonBlankInput("Enter Current Status: ");

        if (type == 1) {
            String desc = getNonBlankInput("Injury Description: ");
            double vetCost = getPositiveDoubleInput("Veterinary Cost (R): ");
            System.out.print("Surgery Required? (true/false): "); 
            boolean surg = Boolean.parseBoolean(input.nextLine());
            rescueCases.add(new InjuredAnimalRescue(id, name, species, loc, ranger, days, dailyCost, status, desc, vetCost, surg));
        } else if (type == 2) {
            int age = getPositiveIntInput("Estimated Age (Months): ");
            double feed = getPositiveDoubleInput("Feeding Cost (R): ");
            System.out.print("Foster Care Required? (true/false): "); 
            boolean foster = Boolean.parseBoolean(input.nextLine());
            rescueCases.add(new OrphanedAnimalRescue(id, name, species, loc, ranger, days, dailyCost, status, age, feed, foster));
        } else if (type == 3) {
            String classif = getNonBlankInput("Conservation Classification: ");
            double secCost = getPositiveDoubleInput("Security Cost (R): ");
            System.out.print("Specialist Team Required? (true/false): "); 
            boolean spec = Boolean.parseBoolean(input.nextLine());
            rescueCases.add(new EndangeredSpeciesRescue(id, name, species, loc, ranger, days, dailyCost, status, classif, secCost, spec));
        }
        System.out.println("Case added successfully!");
    }

    private static void searchRescueCase() {
        System.out.print("\nEnter Rescue Case ID to search: ");
        String id = input.nextLine();
        for (RescueCase rc : rescueCases) {
            if (rc.getRescueCaseId().equalsIgnoreCase(id)) {
                System.out.println("\n" + rc.generateRescueSummary());
                return;
            }
        }
        System.out.println("Case ID not found.");
    }

    private static void updateRescueStatus() {
        System.out.print("\nEnter Rescue Case ID to update: ");
        String id = input.nextLine();
        for (RescueCase rc : rescueCases) {
            if (rc.getRescueCaseId().equalsIgnoreCase(id)) {
                String newStatus = getNonBlankInput("Enter new Rescue Status: ");
                rc.setCurrentRescueStatus(newStatus);
                System.out.println("Status updated successfully.");
                return;
            }
        }
        System.out.println("Case ID not found.");
    }

    private static void displayAllCases() {
        if (rescueCases.isEmpty()) {
            System.out.println("\nNo rescue cases currently registered.");
            return;
        }
        System.out.println("\n--- LISTING ALL REGISTERED CASES ---");
        for (RescueCase rc : rescueCases) {
            System.out.println(rc.generateRescueSummary());
        }
    }

    private static void generateRescueReport() {
        System.out.println("\n========================================");
        System.out.println("WILDLIFE RESCUE REPORT");
        System.out.println("========================================");

        double totalEstimatedCost = 0;

        for (int i = 0; i < rescueCases.size(); i++) {
            RescueCase rc = rescueCases.get(i);
            double caseCost = rc.calculateTotalCost();
            totalEstimatedCost += caseCost;

            System.out.printf("%-15s: %s\n", "Case ID", rc.getRescueCaseId());
            System.out.printf("%-15s: %s\n", "Type", rc.getRescueType());
            System.out.printf("%-15s: %s\n", "Species", rc.getSpecies());
            System.out.printf("%-15s: %s\n", "Priority", rc.determinePriority());
            System.out.printf("%-15s: %s\n", "Status", rc.getCurrentRescueStatus());
            System.out.printf("%-15s: R%,.2f\n", "Total Cost", caseCost);

            if (i < rescueCases.size() - 1) {
                System.out.println("----------------------------------------");
            }
        }

        System.out.println("========================================");
        System.out.println("Total Rescue Cases   : " + rescueCases.size());
        System.out.printf("Total Estimated Cost : R%,.2f\n", totalEstimatedCost);
        System.out.println("========================================");
    }

    // Validation Methods
    private static String getNonBlankInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String raw = input.nextLine().trim();
            if (!raw.isEmpty()) return raw;
            System.out.println("Validation Error: This field cannot be left blank.");
        }
    }

    private static int getPositiveIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int val = Integer.parseInt(input.nextLine());
                if (val > 0) return val;
                System.out.println("Validation Error: Value must be greater than zero.");
            } catch (NumberFormatException e) {
                System.out.println("Validation Error: Please enter a valid integer.");
            }
        }
    }

    private static double getPositiveDoubleInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double val = Double.parseDouble(input.nextLine());
                if (val > 0) return val;
                System.out.println("Validation Error: Value must be greater than zero.");
            } catch (NumberFormatException e) {
                System.out.println("Validation Error: Please enter a valid decimal value.");
            }
        }
    }

    private static int getValidMenuChoice(int min, int max) {
        while (true) {
            System.out.print("Enter choice (" + min + "-" + max + "): ");
            try {
                int val = Integer.parseInt(input.nextLine());
                if (val >= min && val <= max) return val;
                System.out.println("Validation Error: Selection out of range.");
            } catch (NumberFormatException e) {
                System.out.println("Validation Error: Please enter a number.");
            }
        }
    }

    private static boolean isIdUnique(String id) {
        for (RescueCase rc : rescueCases) {
            if (rc.getRescueCaseId().equalsIgnoreCase(id)) return false;
        }
        return true;
    }
}

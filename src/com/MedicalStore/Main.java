package com.MedicalStore;

import java.util.Scanner;

import com.MedicalStore.Medicine.MedicineType;

public class Main {
    Scanner scanner = new Scanner(System.in);

    public void handleUserSelection(int choice) {
        MedicineStore medicineStore = MedicineStore.getInstance();
        UserInterface userInterface = UserInterface.getInstance();

        switch (choice) {
            case 1:
                addMedicine();
                break;
            case 2:
                String userSelection = userInterface.selectMedicine();
                Medicine medicine = medicineStore.getMedicine(userSelection);
                medicineStore.remove(medicine);
                break;
            case 3:
                userInterface.print(medicineStore.getMedicineList());
                break;
            case 4:
                String medicineName = userInterface.selectMedicine();
                Medicine medicineForUpdate = medicineStore.getMedicine(medicineName);

                int updateChoice = userInterface.showUpdateMenu();
                updateMedicine(medicineForUpdate, updateChoice);
                break;
            case 5:
                System.exit(0);
                break;
            default:

        }
    }

    public void updateMedicine(Medicine medicineForUpdate, int updateChoice) {
        UserInterface userInterface = UserInterface.getInstance();

        switch (updateChoice) {
            case 1:
                System.out.println("Current Name is : " + medicineForUpdate.name + ". Enter your new name :");
                String newName = scanner.next();
                if (medicineForUpdate.name == newName) {
                    System.out.println("Already Having This Name");
                    break;
                }
                medicineForUpdate.name = newName;
                break;
            case 2:
                System.out.println("Current Brand Name is : " + medicineForUpdate.brandName + ". Enter your new Brandname :");
                medicineForUpdate.brandName = scanner.next();
                break;
            case 3:
                System.out.println("Current Type is : " + medicineForUpdate.type + ". Enter your new Type :");
                int selectedType = userInterface.showTypeOptions();
                selectTypeForMedicine(selectedType, medicineForUpdate);
                break;
            case 4:
                System.out.println("Current price is : " + medicineForUpdate.price + ". Enter your new price :");
                medicineForUpdate.price = scanner.nextInt();
                break;
            case 5:
                System.exit(0);
                break;
        }
    }

    public void selectTypeForMedicine(int selectedType, Medicine medicineForUpdate) {
        if (selectedType == 1) {
            medicineForUpdate.type = MedicineType.AYURVEDIC;
        } else if (selectedType == 2) {
            medicineForUpdate.type = MedicineType.ALLOPATHY;

        } else if (selectedType == 3) {
            medicineForUpdate.type = MedicineType.HOMEOPATHY;
        } else {
            System.out.println("Select Correct option i.e 1, 2 or 3");
        }
    }

    public void addMedicine() {
        MedicineStore medicineStore = MedicineStore.getInstance();
        UserInterface userInterface = UserInterface.getInstance();

        Medicine medicine = new Medicine();

        System.out.println("Enter the name of the medicine :");
        medicine.name = scanner.next();

        System.out.println("Enter the brand name of the medicine :");
        medicine.brandName = scanner.next();

        System.out.println("Select the medicine type :");
        int selectedType = userInterface.showTypeOptions();
        selectTypeForMedicine(selectedType, medicine);

        System.out.println("Enter the Price :");
        medicine.price = scanner.nextDouble();

        medicineStore.add(medicine);
    }

    public static void main(String[] args) {
        UserInterface userInterface = UserInterface.getInstance();

        Main main = new Main();

        int choice = 0;
        while (choice != 5) {
            choice = userInterface.showMainMenu();
            main.handleUserSelection(choice);
        }
    }
}
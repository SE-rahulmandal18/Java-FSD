package rentmycam.io;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Camera {
    private int id;
    private String brand;
    private String model;
    private double price;
    private boolean available;

    public Camera(int id, String brand, String model, double price, boolean available) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.available = available;
    }

    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

public class CameraRental {
    private static double walletBalance = 17000.0;

    public static void main(String[] args) {
    	System.out.println("\t WELCOME TO CAMERA RENTAL APP\n");
    	System.out.println("Please Login to Continue-");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (username.equals("admin") && password.equals("password")) {
            System.out.println("Login Successful.......\n");
            List<Camera> cameraList = createCameraList();

            int choice;
            do {
                printMainMenu();
                System.out.print("Select your option:\n ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1:
                        manageMyCamera(cameraList, scanner);
                        break;
                    case 2:
                        rentCamera(cameraList, scanner);
                        break;
                    case 3:
                        viewAllCameras(cameraList);
                        break;
                    case 4:
                        viewWalletBalance(scanner);
                        break;
                    case 5:
                    	 System.exit(0);                  
                    	 
                    default:
                        System.out.println("Invalid option. Please try again.");
                        //System.exit(0);
                        break;
                }

                System.out.print("If you want to continue (1.Yes / 2.No): ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                System.out.println();
            } while (choice == 1);
        } else {
            System.out.println("Authentication Failed");
           
        }

        System.out.println("Thank you for visiting camera rental. Please try again !");
       
    }

    private static void printMainMenu() {
        System.out.println("1. MY CAMERA");
        System.out.println("2. RENT A CAMERA");
        System.out.println("3. VIEW ALL CAMERAS");
        System.out.println("4. MY WALLET");
        System.out.println("5. EXIT");
    }

    private static List<Camera> createCameraList() {
        List<Camera> cameraList = new ArrayList<>();
        cameraList.add(new Camera(1, "Canon", "DSLR", 6000, true));
        cameraList.add(new Camera(2, "Sony", "Ds123", 1330, false));
        cameraList.add(new Camera(3, "LG", "5050",   12000, true));
        cameraList.add(new Camera(4, "Lenovo", "XPL", 1000, true));
        cameraList.add(new Camera(5, "Nikon", "SRL", 8000, true));
        cameraList.add(new Camera(6, "Sony", "2130", 16000, false));
        cameraList.add(new Camera(7, "Samsung", "DL", 4550, true));
        cameraList.add(new Camera(8, "LG", "Digital", 9500, true));
        cameraList.add(new Camera(9, "Fujitsu", "J5", 7500, true));
        
        return cameraList;
    }

    private static void manageMyCamera(List<Camera> cameraList, Scanner scanner) {
        int choice;
        do {
            printMyCameraMenu();
            System.out.print("Enter your choice:\n ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addCamera(cameraList, scanner);
                    break;
                case 2:
                    removeCamera(cameraList, scanner);
                    break;
                case 3:
                    viewMyCamera(cameraList);
                    break;
                case 4:
                    return; // Go back to previous menu
                case 5: 
                	 System.exit(0);
                	 break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }

            System.out.print("If you want to add or remove a camera (1.Yes / 2.No): ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
            System.out.println();
        } while (choice == 1);
    }

    private static void printMyCameraMenu() {
        System.out.println("1. ADD CAMERA");
        System.out.println("2. REMOVE CAMERA");
        System.out.println("3. VIEW MY CAMERA");
        System.out.println("4. GO TO PREVIOUS MENU");
    }

    private static void addCamera(List<Camera> cameraList, Scanner scanner) {
        System.out.print("Enter Camera ID: ");
        int cameraId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter Camera Brand: ");
        String brand = scanner.nextLine();
        System.out.print("Enter Camera Model: ");
        String model = scanner.nextLine();
        System.out.print("Enter Camera Price per day(INR): ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character
        boolean available = true;
        cameraList.add(new Camera(cameraId, brand, model, price, available));
        System.out.println("Your Camera has been Added Successfully to the List\n");

        System.out.print("Do you want to view the camera list? (1.Yes / 0.No): ");
        int viewListChoice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        if (viewListChoice == 1) {
            viewAllCameras(cameraList);
        }
    }

    private static void removeCamera(List<Camera> cameraList, Scanner scanner) {
        System.out.print("Enter the Camera ID to remove: ");
        int cameraId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        boolean found = false;
        for (Camera camera : cameraList) {
            if (camera.getId() == cameraId) {
                cameraList.remove(camera);
                found = true;
                break;
            }
        }

        if (found) {
            System.out.println("Camera with ID " + cameraId + " has been removed Successfully from the List.");
        } else {
            System.out.println("Camera with ID " + cameraId + " is not found in the list.");
        }
    }

    private static void viewMyCamera(List<Camera> cameraList) {
        System.out.println("cameraID\tBrand\tModel\tPrice\tStatus");
        for (Camera camera : cameraList) {
            String status = camera.isAvailable() ? "Available" : "Rented";
            System.out.println(camera.getId() + "\t\t" + camera.getBrand() + "\t" + camera.getModel()
                    + "\t" + camera.getPrice() + "\t" + status);
        }
    }

    private static void rentCamera(List<Camera> cameraList, Scanner scanner) {
    	System.out.println("FOLLOWING ARE THE LIST OF AVALIABLE CAMERA(S)");
        System.out.println("cameraID\tBrand\tModel\tPrice\tStatus");
        for (Camera camera : cameraList) {
            if (camera.isAvailable()) {
                String status = camera.isAvailable() ? "Available" : "Rented";
                System.out.println(camera.getId() + "\t\t" + camera.getBrand() + "\t" + camera.getModel()
                        + "\t" + camera.getPrice() + "\t" + status);
            }
        }
		int index = -1;
		System.out.println("Entre camera Id  you want to rent: ");
		Scanner sc = new Scanner(System.in);
		int cameraId = sc.nextInt();
		for (int i = 0; i < cameraList.size(); i++) {
			Camera camera = cameraList.get(i);
			if (camera.getId() == cameraId) {
				index = i;
				break; // Found the camera, exit the loop
			}
		}
		if (index != -1) {
			Camera camera = cameraList.get(index);
			if (camera.getPrice() <= walletBalance) {
				System.out.println("YOUR TRANSACTION FOR CAMERA- "+ camera.getBrand() +" with rent INR "+ camera.getPrice()+" has Successfully rented");
				camera.setAvailable(false);
				walletBalance = walletBalance - camera.getPrice();
				System.out.println("Current wallet balance - " + walletBalance);
			} else {
				System.out.println("TRANSACTION FAILED DUE TO INSUFFICIENT WALLET BALANCE. PLEASE DEPOSIT AMOUNT TO YOUR WALLET");
			}
		} else {
			System.out.println("Camera with ID " + cameraId + " is not found in the list.");
		}

		
    }
    private static void viewAllCameras(List<Camera> cameraList) {
        System.out.println("cameraID\tBrand\tModel\tPrice\tStatus");
        for (Camera camera : cameraList) {
            String status = camera.isAvailable() ? "Available" : "Rented";
            System.out.println(camera.getId() + "\t\t" + camera.getBrand() + "\t" + camera.getModel()
                    + "\t" + camera.getPrice() + "\t" + status);
        }
    }

    private static void viewWalletBalance(Scanner scanner) {
        System.out.println("Current Wallet Balance: INR" + walletBalance);
        System.out.print("Do you want to add more amount to your wallet? (1.Yes / 0.No): ");
        int addMoneyChoice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        if (addMoneyChoice == 1) {
            System.out.print("Enter the amount(INR) to add: ");
            double amount = scanner.nextDouble();
            scanner.nextLine(); // Consume the newline character
            walletBalance += amount;
            System.out.println("YOUR WALLET BALAANCE UPDATED SUCCESSFULLY.  CURRENT WALLET BALANCE- INR " + walletBalance);
            
        }
    }
}
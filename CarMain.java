package com.jsp.CarDekhoJdbc.main;

import java.util.List;
import java.util.Scanner;

public class CarMain {

		public static void main(String[] args) {

			Carjdbc1 jdbc = new Carjdbc1();
			Scanner scanner = new Scanner(System.in);
			boolean flag = true;

			while (flag) {
				System.out.println(
						"Enter 1 to add Car.\nEnter 2 to get all Cars.\nEnter 3 to get Car by id.\nEnter 4 to delete Car.\nEnter 5 to update Car.\nEnter 6 to Exit.");
				System.out.println("Enter your choice.");
				int choice = scanner.nextInt();
				switch (choice) {
				case 1:
					Car Car = new Car();
					System.out.println("Enter Car id.");
					Car.setCar_id(scanner.nextInt());
					scanner.nextLine();
					System.out.println("Enter Car name.");
					Car.setName(scanner.nextLine());
					System.out.println("Enter Car Brand.");
					Car.setBrand(scanner.nextLine());
					System.out.println("Enter Car Fuel Type.");
					Car.setFuel_type(scanner.nextLine());
					System.out.println("Enter Car price.");
					Car.setPrice(scanner.nextDouble());
					jdbc.addCar(Car);
					break;
				case 2:
					List<Car> Cars = jdbc.getAllCars();
					for (Car s : Cars) {
						System.out.println(s);
					}
					break;
				case 3:
					System.out.println("Enter Car id.");
					Car s = jdbc.getCarById(scanner.nextInt());
					System.out.println(s);
					break;
				case 4:
					System.out.println("Enter Car id.");
					jdbc.deleteCar(scanner.nextInt());
					break;
				case 5:
					System.out.println("Enter Car id.");
					jdbc.updateCar(scanner.nextInt(), scanner);
					break;
				case 6:
					System.out.println("Thank you!");
					flag = false;
					break;
				default:
					System.out.println("Invalid choice.");
				}
			}
			scanner.close();
		}

	}

package com.jsp.CarDekhoJdbc.main;


	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.ArrayList;
	import java.util.List;
	import java.util.Scanner;

	public class Carjdbc1 {
		private Connection connection;
		private PreparedStatement preparedStatement;
		private ResultSet resultSet;
		private String query;
		
		public void addCar(Car car) {
			try {
				connection = openConnection();
				query = "INSERT INTO Car VALUES(?,?,?,?,?)";
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, car.getCar_id());
				preparedStatement.setString(2, car.getName());
				preparedStatement.setString(3, car.getBrand());
				preparedStatement.setString(4, car.getFuel_type());
				preparedStatement.setDouble(5, car.getPrice());
				int row = preparedStatement.executeUpdate();
				System.out.println(row + " row(s) affected.");
			} catch (Exception e) {
//				e.printStackTrace();
			} finally {
				try {
					closeConnection();
				} catch (SQLException e) {
//					e.printStackTrace();
				}
			}
		}
		
		
		public List<Car> getAllCars() {

			ArrayList<Car> list = new ArrayList<>();

			try {
				connection = openConnection();
				query = "SELECT * FROM  car";
				preparedStatement = connection.prepareStatement(query);
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					Car car = new Car();
					car.setCar_id(resultSet.getInt(1));
					car.setName(resultSet.getString(2));
					car.setBrand(resultSet.getString(3));
					car.setFuel_type(resultSet.getString(4));
					car.setPrice(resultSet.getDouble(5));
					list.add(car);
				}
			} catch (Exception e) {
//				e.printStackTrace();
			} finally {
				try {
					closeConnection();
				} catch (SQLException e) {
//					e.printStackTrace();
				}
			}
			return list;

		}

		
		public Car getCarById(int car_id) {

			Car car = new Car();

			try {
				connection = openConnection();
				query = "SELECT * FROM car WHERE car_id = ?";
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, car_id);
				resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					car.setCar_id(resultSet.getInt(1));
					car.setName(resultSet.getString(2));
					car.setBrand(resultSet.getString(3));
					car.setFuel_type(resultSet.getString(4));
					car.setPrice(resultSet.getDouble(5));
				}
			} catch (Exception e) {
//				e.printStackTrace();
			} finally {
				try {
					closeConnection();
				} catch (SQLException e) {
//					e.printStackTrace();
				}
			}

			return car;

		}
		
		
		public void deleteCar(int car_id) {

			try {
				connection = openConnection();
				query = "DELETE FROM car WHERE car_id = ?";
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, car_id);
				int row = preparedStatement.executeUpdate();
				System.out.println(row + " row(s) affected.");
			} catch (Exception e) {
//				e.printStackTrace();
			} finally {
				try {
					closeConnection();
				} catch (SQLException e) {
//					e.printStackTrace();
				}
			}

		}
		
		
		public void updateCar(int car_id, Scanner scanner) {

			try {
				connection = openConnection();
				query = "UPDATE car SET name = ?, brand = ?, fuel_type = ?, price = ? WHERE car_id = ?";
				preparedStatement = connection.prepareStatement(query);
				scanner.nextLine();
				System.out.println("Enter Car name.");
				preparedStatement.setString(1, scanner.nextLine());
				System.out.println("Enter Car Brand.");
				preparedStatement.setString(2, scanner.nextLine());
				System.out.println("Enter Car Fuel type.");
				preparedStatement.setString(3, scanner.nextLine());
				System.out.println("Enter Car price.");
				preparedStatement.setDouble(4, scanner.nextDouble());
				preparedStatement.setInt(5, car_id);
				int row = preparedStatement.executeUpdate();
				System.out.println(row + " row(s) affected.");
			} catch (ClassNotFoundException | SQLException e) {
//				e.printStackTrace();
			} finally {
				try {
					closeConnection();
				} catch (SQLException e) {
//					e.printStackTrace();
				}
			}

		}


		private Connection openConnection() throws ClassNotFoundException, SQLException {

			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/weja3", "root", "root");

		}

		private void closeConnection() throws SQLException {

			if (resultSet != null) {
				resultSet.close();
			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (connection != null) {
				connection.close();
			}

		}
		

	}

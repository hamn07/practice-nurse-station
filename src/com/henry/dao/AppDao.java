package com.henry.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.henry.model.Assignment;
import com.henry.model.Nurse;
import com.henry.model.Station;
import com.henry.util.DbUtil;

public class AppDao {
	private Connection connection;

	public AppDao() {
		connection = DbUtil.getConnection();
	}
	
	/*****************
	 * 
	 * 
	 * 
	 * 護理人員
	 * 
	 * 
	 * 
	 *****************/
	public void addNurse(Nurse nurse) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into nurse(name) values (?)");

			preparedStatement.setString(1, nurse.getName());
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteNurse(int id) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("delete from nurse where id=?");
			// Parameters start with 1
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateNurse(Nurse nurse) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("update nurse set name=? where id=?");

			preparedStatement.setString(1, nurse.getName());
			preparedStatement.setInt(2, nurse.getId());
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Nurse> getAllNurse() {
		List<Nurse> nurses = new ArrayList<Nurse>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select id,name from nurse");
			while (rs.next()) {
				Nurse nurse = new Nurse();
				nurse.setId(rs.getInt("id"));
				nurse.setName(rs.getString("name"));
				nurses.add(nurse);
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return nurses;
	}
	
	public List<Nurse> getAllNurseNotInByStation(int station_id) {
		List<Nurse> nurses = new ArrayList<Nurse>();
		try {
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT id,"
							       + "       name"
							       + "  FROM nurse"
							       + " WHERE id NOT IN (SELECT nurse_id"
							       + "                    FROM assignment"
							       + "                   WHERE station_id = ?)");
			preparedStatement.setInt(1, station_id);
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				Nurse nurse = new Nurse();
				nurse.setId(rs.getInt("id"));
				nurse.setName(rs.getString("name"));
				nurses.add(nurse);
			}
			rs.close();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return nurses;
	}

	
	
	
	/*********
	 * 
	 * 護理站
	 * 
	 * 
	 *********/
	
	
	public void addStation(Station station) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into station(name) values (?)");

			preparedStatement.setString(1, station.getName());
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteStation(int id) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("delete from station where id=?");
			// Parameters start with 1
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateStation(Station station) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("update station set name=? where id=?");

			preparedStatement.setString(1, station.getName());
			preparedStatement.setInt(2, station.getId());
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Station> getAllStation() {
		List<Station> stations = new ArrayList<Station>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select id,name from station");
			while (rs.next()) {
				Station station = new Station();
				station.setId(rs.getInt("id"));
				station.setName(rs.getString("name"));
				stations.add(station);
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return stations;
	}
	
	
	
	/***************
	 * 
	 * 
	 * 護理站負責人員
	 * 
	 * 
	 * 
	 ***************/
	
	public void addAssignment(Assignment assignment) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into assignment(nurse_id,station_id) values (?,?)");

			preparedStatement.setInt(1, assignment.getNurse_id());
			preparedStatement.setInt(2, assignment.getStation_id());
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteAssignment(Assignment assignment) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("delete from assignment where nurse_id=? and station_id=?");
			// Parameters start with 1
			preparedStatement.setInt(1, assignment.getNurse_id());
			preparedStatement.setInt(2, assignment.getStation_id());
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Assignment> getAllAssignmentByNurse(Station station) {
		List<Assignment> assignments = new ArrayList<Assignment>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					  "select nurse.id     nurse_id,"
                    + "       nurse.name   nurse_name,"
                    + "       station.id   station_id,"
                    + "       station.name station_name"
                    + "  from assignment,nurse,station"
                    + " where nurse_id = nurse.id"
                    + "   and station_id = station.id"
                    + "   and station_id = ?");
			preparedStatement.setInt(1, station.getId());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Assignment assignment = new Assignment();
				assignment.setNurse_id(rs.getInt("nurse_id"));
				assignment.setNurse_name(rs.getString("nurse_name"));
				assignment.setStation_id(rs.getInt("station_id"));
				assignment.setStation_name(rs.getString("station_name"));
				assignments.add(assignment);
			}
			rs.close();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return assignments;
	}
	
}

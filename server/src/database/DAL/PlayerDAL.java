package database.DAL;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import database.Connector;
import database.DTO.PlayerDTO;
import models.Player;
import utils.ObjectMapping;

public class PlayerDAL {

	private static PlayerDAL instance = null;

	private PlayerDAL() {

	}

	public static PlayerDAL getInstance() {
		if (instance == null) {
			instance = new PlayerDAL();
		}
		return instance;
	}

	public PlayerDTO getPlayerByLogin(String email, String password) {
		try {
			Connector connector = new Connector();
			ResultSet resultSet = connector
					.query("SELECT * FROM PLAYERS WHERE EMAIL='" + email + "' AND PASSWORD='" + password + "'");
			return ObjectMapping.mapFirstPlayer(resultSet);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public PlayerDTO updateCurrentPlayerDTOByUserUID(String user_uid) {
		try {
			Connector connector = new Connector();
			ResultSet resultSet = connector.query("SELECT * FROM PLAYERS WHERE USER_UID = '" + user_uid + "'");
			return ObjectMapping.mapFirstPlayer(resultSet);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public List<PlayerDTO> getAllPlayers() {
		try {
			Connector connector = new Connector();
			ResultSet resultSet = connector.query("SELECT * FROM PLAYERS");
			return ObjectMapping.mapPlayers(resultSet);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public boolean createPlayer(PlayerDTO player) {
		try {
			Connector connector = new Connector();
			String sql = "INSERT INTO players (user_uid, full_name, gender, email, password, dob, total_matches, win_streak_counts, win_matches, lost_matches, elo_rating_points, player_img_file_path, biography, joined_date, rank_id) "
					+ "VALUES (?, ?, ?, ?, ?, ?, 0, 0, 0, 0, 500, '/HinhAnh.png', ' ', ?, 1)";

			PreparedStatement ps = connector.getConnection().prepareStatement(sql);
			ps.setString(1, player.getUser_uid());
			ps.setString(2, player.getFull_name());
			ps.setString(3, player.getGender());
			ps.setString(4, player.getEmail());
			ps.setString(5, player.getPassword());

			LocalDate joinedDate = LocalDate.now();
			System.out.println("Joined date: " + joinedDate);

			LocalDate dob = player.getDob();
			ps.setDate(6, Date.valueOf(dob));
			ps.setDate(7, Date.valueOf(joinedDate));

			int rowsInserted = ps.executeUpdate();
			return rowsInserted > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public void updateElo(PlayerDTO player, int newElo) {
		try {
			Connector connector = new Connector();
			String sql = "UPDATE players SET elo_rating_points = elo_rating_points + " + newElo + " WHERE USER_UID = '"
					+ player.getUser_uid() + "'";
			connector.executeNonQuery(sql);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void deletePlayerById(Long id) {

	}

	public void updateProfilePlayer(PlayerDTO player) {
		try {
			Connector connector = new Connector();
			LocalDate dob = player.getDob();
			String sql = "UPDATE players" + " SET gender = " + player.getGender() + "," + " dob =" + Date.valueOf(dob)
					+ ", full_name=" + player.getFull_name() + ", player_img_file_path =" + player.getPlayer_img_path()
					+ "," + " biography = " + player.getBiography() + ", password = " + player.getPassword()
					+ " WHERE user_uid = " + player.getUser_uid();
			connector.executeNonQuery(sql);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}

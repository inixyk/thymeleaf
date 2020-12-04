package com.inixindojogja;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.LinkedList;

public class DataAkses {
	public LinkedList<HashMap<String,Object>> retrieve() {
		String sql = "SELECT * FROM aktivitas";
		LinkedList<HashMap<String,Object>> tabel = new LinkedList<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost/latihan","root","root");
			PreparedStatement ps = koneksi.prepareStatement(sql);
			ResultSet hasil = ps.executeQuery();
			while(hasil.next()) {
				HashMap<String,Object> baris = new HashMap<>();
				baris.put("no",hasil.getString("no"));
				baris.put("nama",hasil.getString("nama"));
				baris.put("tgl",hasil.getString("tgl"));
				baris.put("keterangan",hasil.getString("keterangan"));
				tabel.add(baris);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return tabel;
	}
	
	public void insertupdate(int no, String nama, String tgl, String keterangan) {
		String sqlinsert = "INSERT INTO aktivitas VALUES(null,?,?,?)";
		String sqlupdate = "UPDATE aktivitas SET nama=?,tgl=?,keterangan=? WHERE no=?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost/latihan","root","root");

			PreparedStatement ps = koneksi.prepareStatement(sqlinsert);
			if(no != 0) { 
				ps = koneksi.prepareStatement(sqlupdate);
				ps.setInt(4, no);
			}
			ps.setString(1, nama);
			ps.setString(2, tgl);
			ps.setString(3, keterangan);
			ps.executeUpdate();
					
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delete(int no) {
		String sqldelete = "DELETE FROM aktivitas WHERE no=?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost/latihan","root","root");

			PreparedStatement ps = koneksi.prepareStatement(sqldelete);
			ps.setInt(1, no);
			ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public HashMap<String,Object> retrieve(int no){
		String sql = "SELECT * FROM aktivitas WHERE no=?";
		HashMap<String,Object> baris = new HashMap<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost/latihan","root","root");
			PreparedStatement ps = koneksi.prepareStatement(sql);
			ps.setInt(1, no);
			ResultSet hasil = ps.executeQuery();
			if(hasil.next()) {
				baris.put("no",hasil.getString("no"));
				baris.put("nama",hasil.getString("nama"));
				baris.put("tgl",hasil.getString("tgl"));
				baris.put("keterangan",hasil.getString("keterangan"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return baris;
	}
}

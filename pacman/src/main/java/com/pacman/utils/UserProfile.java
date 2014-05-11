package com.pacman.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserProfile {
	/**
	 * @author: Siyuan Liu
	 * Purpose: The user profile class, can set, get, save and load user
	 * profiles.
	 */
	private String Nickname;
	private String Gender;
	private String Discription;

	public String getNickname() {
		return Nickname;
	}

	public void setNickname(String nickname) {
		Nickname = nickname;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public String getDiscription() {
		return Discription;
	}

	public void setDiscription(String discription) {
		Discription = discription;
	}

	public boolean saveProfile() {
		String outfileName = "Me.conf";
		try {
			if (Nickname != null && Nickname != "") {
				FileWriter fw = new FileWriter(outfileName);
				fw.write(Nickname + "\n");
				fw.write(Gender + "\n");
				fw.write(Discription + "\n");
				fw.close();
			}
			else return false;
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	public boolean loadProfile() {
		String infileName = "Me.conf";
		try {
			FileInputStream fis = new FileInputStream(infileName);
			InputStreamReader ins = new InputStreamReader(fis);
			BufferedReader bfr = new BufferedReader(ins);

			String nickname = bfr.readLine();
			String gender = bfr.readLine();
			String discription = bfr.readLine();
			setNickname(nickname);
			setGender(gender);
			setDiscription(discription);
			
			bfr.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}

}

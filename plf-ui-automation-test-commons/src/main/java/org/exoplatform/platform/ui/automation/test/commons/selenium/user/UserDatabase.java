package org.exoplatform.platform.ui.automation.test.commons.selenium.user;

import java.util.ArrayList;

import org.exoplatform.selenium.platform.objectdatabase.common.DatabaseResource;

public class UserDatabase {
	public ArrayList<Integer> type;
	public ArrayList<String> userName;
	public ArrayList<String> password;
	public ArrayList<String> fullName;

	
	/**
	 * UserDatabase
	 * @param type
	 * @param userName
	 * @param password
	 * @param fullName
	 */
	public UserDatabase(ArrayList<Integer> type, ArrayList<String> userName, ArrayList<String> password, ArrayList<String> fullName){
		this.type = type;
		this.userName = userName;
		this.password = password;
		this.fullName = fullName;
	}

	/**
	 * UserDatabase
	 */
	public UserDatabase(){
		type  = new ArrayList<Integer>();
		userName  = new ArrayList<String>();
		password  = new ArrayList<String>();
		fullName  = new ArrayList<String>();
	}

	
	/**
	 * setUserData
	 * @param userDataFile
	 * @param userSheet
	 * @param opParams
	 * @throws Exception
	 */
	public void setUserData(String userDataFile, String userSheet, Object... opParams) throws Exception{
		String[][] testData = DatabaseResource.getDataFromSource(userDataFile,userSheet,opParams);
		for(int i = 0; i<testData.length; i++)
		{	
			type.add(Integer.valueOf(testData[i][0]));
			userName.add(testData[i][1]);
			password.add(testData[i][2]);
			fullName.add(testData[i][3]);
		}
	}
	
	/**
	 * Get Full name By Index
	 * @param index
	 * @return fullName.get(index)
	 */
	public String getFullNameByIndex(int index){
		return fullName.get(index);
	}

	/**
	 * Get User name By Index
	 * @param index
	 * @return userName.get(index);
	 */
	public String getUserNameByIndex(int index){
		return userName.get(index);
	}
}

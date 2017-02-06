package org.exoplatform.platform.ui.automation.test.commons.selenium.user;

import java.util.ArrayList;


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
		// Mock (to update)
		String[][] testData = new String[][] {{ "X0", "Y0"},
						{ "X1", "Y1"},
						{ "X2", "Y2"},
						{ "X3", "Y3"},
						{ "X4", "Y4"}};
		//DatabaseResource.getDataFromSource(userDataFile,userSheet,opParams);
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

package org.exoplatform.platform.ui.automation.test.commons.selenium;

import static org.exoplatform.platform.ui.automation.test.config.Logger.info;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class Dialog {

	public Dialog(WebDriver dr) {
		driver = dr;
	}

	public ManageAlert alt = new ManageAlert(driver);

	//Dialog warning
	public final String ELEMENT_POPUP_WARNING = "//*[@class='UIPopupWindow UIDragObject uiPopup']//*[@class='warningIcon' and contains(text(),'${message}')]";

	//Close Message
	public final String ELEMENT_MESSAGE_TEXT = "//li[@class='MessageContainer']/span[contains(@class, 'PopupIcon')]";
	public final String ELEMENT_MESSAGE_DIALOG_CLOSE_ICON_IE = ELEMENT_MESSAGE_TEXT + "/../../../../../..//a";
	public final String ELEMENT_MESSAGE_DIALOG_CLOSE_ICON = "//div[contains(@class, 'UIPopupWindow') and contains(@style, 'visibility: visible')]//a[contains(@class, 'uiIconClose')]";

	//ECMS > Admin > Repository > Manage Lock Tabs
	public final String MESSAGE_UNLOCK_WITHOUT_PERMISSION = "You do not have permission to unlock this node. Please contact the administrator to get the correct right.";
	public final String MESSAGE_CANNOT_DELETE_ADMIN_GROUP = "Cannot delete the group *:/platform/administrators.";

	//ECMS > Delete a document
	public final By ELEMENT_DELETE_IN_DIALOG = By.xpath("//*[contains(@class, 'uiAction')]//*[text()='Delete']");
	public final By ELEMENT_CANCEL_IN_DIALOG = By.xpath("//*[contains(@class, 'uiAction')]//*[text()='Cancel']");

	/**
	 * Close Message Dialog
	 */
	public void closeMessageDialog() {
		info("--Closing message dialog--");
		if (ieFlag) {
			click(ELEMENT_MESSAGE_DIALOG_CLOSE_ICON_IE);
		} else {
			click(ELEMENT_MESSAGE_DIALOG_CLOSE_ICON);
		}
		Utils.pause(1000);
	}

	/**
	 * Click button Delete in Dialog
	 */
	public void deleteInDialog(){
		waitForAndGetElement(ELEMENT_DELETE_IN_DIALOG);
		click(ELEMENT_DELETE_IN_DIALOG);
		Utils.pause(3000);
	}
}

package com.gmailtesting.tests;


import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.gmailtesting.businesslogic.Login;
import com.gmailtesting.businesslogic.MainPageBL;
import com.gmailtesting.common.TestBase;
import com.gmailtesting.data.Message;
import com.gmailtesting.data.MessageRepository;
import com.gmailtesting.data.User;
import com.gmailtesting.data.UserRepository;

public class TestGmail  extends TestBase{
		
//	@DataProvider(parallel = true)
//	public Object[][] draftsMessage() {
//		return new Object[][] { { UserRepository.getUser1(), MessageRepository.getMessage() },{ UserRepository.getUser7(), MessageRepository.getMessage() }};
//	}
	
	@DataProvider
	public Object[][] draftsMessage() {
		return new Object[][] { { UserRepository.getUser2(), MessageRepository.getMessage() }};
	}

	@Test(dataProvider = "draftsMessage")
	public void testDraftsMessage(User user, Message message) {
			
		Login login = new Login();
		login.loginInGmail(user);

		MainPageBL mainPage = new MainPageBL();
		mainPage.createDraftMessage(message);
		mainPage.openLastDraftMessage();

		Message messageDraft = mainPage.getMessage();
		Assert.assertEquals(message.getMessageToData(), messageDraft.getMessageToData());
		Assert.assertEquals(message.getMessageCcData(), messageDraft.getMessageCcData());
		Assert.assertEquals(message.getMessageBccData(), messageDraft.getMessageBccData());
		Assert.assertEquals(message.getSubjectData(), messageDraft.getSubjectData());
		Assert.assertEquals(message.getTextMessageData(), messageDraft.getTextMessageData());

//		mainPage.sendMessage();
//		
//		mainPage.goToInboxMessages();

	}
	

	
	

}
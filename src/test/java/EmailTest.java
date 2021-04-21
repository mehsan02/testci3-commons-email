package org.apache.commons.mail;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EmailTest {
	
	private static final String[] TEST_EMAILS = { "ab@bc.com", "a.b@c.org",
			"acbdefghijklmnopqrst@abcdefghijklmnopqrst.com.bd"};
	
	private EmailConcrete email;
	
	@Before
	public void setUpEmailTest() throws Exception{
		email = new EmailConcrete();
	}
	
	@Test
	public void testgetHostName() {
		email.setHostName("192.345.3.2");
		assertEquals("192.345.3.2", email.getHostName());
	}
	
	@Test
	public void testGetSetHostNameWithNull() {
		email.setHostName(null);
		assertEquals(null, email.getHostName());
	}
	
	@Test
	public void testGetHostNameWithSession() {

		Properties properties = new Properties();
		Session session = Session.getDefaultInstance(properties, null);
		properties.put(EmailConstants.MAIL_HOST, "me@gmail.com");
		email.setMailSession(session);
		assertEquals("me@gmail.com", email.getHostName());
	}
	
	

	@After
	public void tearDownEmailTest() throws Exception{
		
	}
	

	
	@Test
	public void testAddBcc() throws Exception{
		email.addBcc(TEST_EMAILS);
		int num = 3; 
		assertEquals(num, email.getBccAddresses().size());
	}
	
	@Test
	public void testAddCc() throws EmailException {
		
		email.addCc(TEST_EMAILS);	
		int num = 3;
		assertEquals(num, email.getCcAddresses().size());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAddHeaderWithNameNull() {
		String strValue = "0";
		email.addHeader(null, strValue);
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAddHeaderWithValueNull(){
		String strName = "Hi";
		email.addHeader(strName, null);
				
	}
	

	@Test
	public void testAddReplyTo() throws EmailException {
		String emailName = "me@gmail.com";
		String personName = "ME";
		email.addReplyTo(emailName, personName);
	}
	
	@Test
	public void testCreateMimeMessage() {
		email.createMimeMessage(null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testSendMimeMessage() throws EmailException {
		email.sendMimeMessage();
	}
	
	
	
	@Test (expected = EmailException.class)
	public void testGetMailSession() throws EmailException {	
		email.setBounceAddress("me2@gmail.com");
		email.getMailSession();
	}
	
	@Test 
	public void testbuildMimeMessage() throws EmailException {
				
		email.setHostName("localHost"); 
		email.setSmtpPort(4532);
		email.setFrom("me@gmail.com");
		email.addTo("me2@gmail.com");
		email.setSubject("Hi There");
		
		email.setCharset("ISO-8859-1");
		email.setContent("test content", "text/plain");
		email.addCc("test@abc.com");
		email.addBcc("test2@abc.com");
		email.addHeader("test", "abc");
		email.updateContentType(null);
						
		email.buildMimeMessage();
	}
	
	
	
	@Test
	public void testGetSentDate() {
		Date date = new Date(2021-03-21);
		email.setSentDate(date);	
		assertEquals(date, email.getSentDate());
	}
	
	@Test
	public void testGetSocketConnectionTimeout() {
		email.setSocketConnectionTimeout(1);
		assertEquals(1, email.getSocketConnectionTimeout());
	}
	
	@Test
	public void testSetFrom() throws EmailException {
		email.setFrom("me@gmail.com");
	}
	
}
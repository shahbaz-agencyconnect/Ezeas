package Ezeas.AbstractComponents;

import jakarta.mail.*;
import jakarta.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailLinkExtractor {

	public static String extractVerificationLink(String username, String password, String host, String port,String subject,String regexPattern) {
		String verificationLink = null;
		Properties props = new Properties();
		props.put("mail.store.protocol", "pop3s"); // Or "pop3"
		props.put("mail.pop3s.host", host); // e.g., "imap.gmail.com"
		props.put("mail.pop3s.port", port); // e.g., "993" for IMAP SSL
		props.put("mail.pop3s.ssl.enable", "true");

//		props.put("mail.store.protocol", "pop3s");
//		props.put("mail.pop3s.host", host);
//		props.put("mail.pop3s.port", "995");
//		props.put("mail.pop3s.ssl.enable", "true");

		Session session = Session.getInstance(props, null);
		Store store = null;
		Folder inbox = null;

		try {
			store = session.getStore();
			store.connect(username, password);
			inbox = store.getFolder("INBOX");
			inbox.open(Folder.READ_ONLY);

			Message[] messages = inbox.getMessages();
			for (Message message : messages) {
				// Check if the email is a verification email (you might need to check subject
				// or sender)
				if (message.getSubject().contains(subject)) { // Example subject
					System.out.println(subject);
					String content = message.getContent().toString();
					if (message.getContent() instanceof MimeMultipart) {
						MimeMultipart multipart = (MimeMultipart) message.getContent();
						content = getTextFromMimeMultipart(multipart);
					}

					// Regex to find a URL (adjust as needed for your specific link format)
//					Pattern pattern = Pattern.compile(
//							"https://stg\\.ezeas\\.com/account/confirm-registration/[A-Za-z0-9-_]+\\.[A-Za-z0-9-_]+\\.[A-Za-z0-9-_]+");			
					Pattern pattern = Pattern.compile(regexPattern);
					
					
					Matcher matcher = pattern.matcher(content);
					if (matcher.find()) {
						verificationLink = matcher.group();
						break; // Stop after finding the first link
					}
				}
			}
		} catch (MessagingException | IOException e) {
			e.printStackTrace();
		} finally {
			// Close resources
			try {
				if (inbox != null && inbox.isOpen())
					inbox.close(false);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			try {
				if (store != null && store.isConnected())
					store.close();
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
		return verificationLink;
	}

	private static String getTextFromMimeMultipart(MimeMultipart multipart) throws MessagingException, IOException {
		StringBuilder result = new StringBuilder();
		int count = multipart.getCount();
		for (int i = 0; i < count; i++) {
			BodyPart bodyPart = multipart.getBodyPart(i);
			if (bodyPart.isMimeType("text/plain")) {
				result.append(bodyPart.getContent());
			} else if (bodyPart.isMimeType("text/html")) {
				String html = (String) bodyPart.getContent();
				// You might want to use a HTML parser here for more robust HTML parsing
				result.append(html);
			}
		}
		return result.toString();
	}

	public String getAttachedLink(String username, String password, String subject,String regexPattern) {
//		String username = "anand.mahindra4578@gmail.com";
//		String password = "aayu nhuf xvxs chdk";
		String host = "pop.gmail.com";
		String port = "995";

		String link = extractVerificationLink(username, password, host, port, subject, regexPattern);
		if (link != null) {
			System.out.println("Verification Link: " + link);
		} else {
			System.out.println("No verification link found in the emails.");
		}
		return link;
	}
}

package com.quick.questions.ws.shared;

import org.springframework.stereotype.Service;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.amazonaws.services.simpleemail.model.SendEmailResult;
import com.quick.questions.ws.shared.dto.UserDto;
@Service
public class AmazonSES {

	final String FROM = "hts.rajeshb@gmail.com";
	
	
	final String SUBJECT = "One last step to complete your registration with Quickquestions App";
	
	final String PASSWORD_RESET_SUBJECT = "Password reset request";
	
	final String HTMLBODY = "<h1>Please verify your email address</h1>"
			+"<p>Thank you for registering with our Quickquestions app.To complete registration process and be able to log in </p>"
			+ "<p> click on the following link: </p>"
			+"<a href='http://localhost:8080/verification-service/email-verification.html?token=$tokenValue'>"
			+"Final step to complete your registration"
			+"</a><br /><br />"
			+"Thank you! And we are waiting for you inside!";
	final String TEXTBODY =	"Please verify your email address."
			+"Thank you for registering with our Quickquestions app.To complete registration process and be able to log in</p>"
			+ "open the following URL in the browser window : "
			+"http://localhost:8080/verification-service/email-verification.html?token=$tokenValue"
			+"Thank you! And we are waiting for you inside!";
	
	final String PASSWORD_RESET_HTMLBODY = "<h1>A request to reset your password</h1>"
		      + "<p>Hi, $firstName!</p> "
		      + "<p>Someone has requested to reset your password with our project. If it were not you, please ignore it."
		      + " otherwise please click on the link below to set a new password: " 
		      + "<a href='http://localhost:8080/verification-service/password-reset.html?token=$tokenValue'>"
		      + " Click this link to Reset Password"
		      + "</a><br/><br/>"
		      + "Thank you!";

		  // The email body for recipients with non-HTML email clients.
		  final String PASSWORD_RESET_TEXTBODY = "A request to reset your password "
		      + "Hi, $firstName! "
		      + "Someone has requested to reset your password with our project. If it were not you, please ignore it."
		      + " otherwise please open the link below in your browser window to set a new password:" 
		      + " http://localhost:8080/verification-service/password-reset.html?token=$tokenValue"
		      + " Thank you!";
	
	public void verifyEmail(UserDto userDto) {
		
		System.setProperty("aws.accessKeyId", "AKIASCWPUIKGJLUD5B65");
		System.setProperty("aws.secretKey", "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");
		
		/*
		 * AWSCredentialsProvider provider = new AWSCredentialsProvider() {
		 * 
		 * @Override public void refresh() { // TODO Auto-generated method stub
		 * 
		 * }
		 * 
		 * @Override public AWSCredentials getCredentials() { // TODO Auto-generated
		 * method stub AWSCredentials credentials = new
		 * BasicAWSCredentials("AKIASCWPUIKGJLUD5B65",
		 * "kVfQogPRxl7P2DpzJOtJsNaojBz2bZ/fOT1At3FZ"); return credentials; } };
		 */
		AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder.standard()
				.withRegion(Regions.US_EAST_1).build();
		System.out.println("Client details ==  "+client);
		String htmlBodyWithToken = HTMLBODY.replace("$tokenValue", userDto.getEmailVerificationToken());
		String textBodyWithToken = TEXTBODY.replace("$tokenValue", userDto.getEmailVerificationToken());
		
		SendEmailRequest request = new SendEmailRequest()
				.withDestination(new Destination().withToAddresses(userDto.getEmail()))
				.withMessage(new Message().withBody(new Body().withHtml(new Content()
						.withCharset("UTF-8").withData(htmlBodyWithToken))
						.withText(new Content().withCharset("UTF-8")
								.withData(textBodyWithToken)))
						.withSubject(new Content().withCharset("UTF-8").withData(SUBJECT)))
				.withSource(FROM);
		System.out.println("Client details ==  "+client.toString());
		client.sendEmail(request);
		System.out.println("Email sent");
	}

	public boolean sendPasswordResetRequest(String firstName, String email, String passwordResetToken) {
		// TODO Auto-generated method stub
		boolean returnValue = false;
		
	
		AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder.standard()
				.withRegion(Regions.US_EAST_1).build();
		System.out.println("Client details ==  "+client);
		
		String htmlBodyWithToken = PASSWORD_RESET_HTMLBODY.replace("$tokenValue", passwordResetToken);
		htmlBodyWithToken = htmlBodyWithToken.replace("$firstName", firstName);
		String textBodyWithToken = PASSWORD_RESET_TEXTBODY.replace("$tokenValue", passwordResetToken);
		textBodyWithToken = textBodyWithToken.replace("$firstName", firstName);
		
		SendEmailRequest request = new SendEmailRequest()
				.withDestination(new Destination().withToAddresses(email))
				.withMessage(new Message().withBody(new Body()
						.withHtml(new Content().withCharset("UTF-8")
								.withData(htmlBodyWithToken))
						.withText(new Content().withCharset("UTF-8")
								.withData(textBodyWithToken)))
						.withSubject(new Content().withCharset("UTF-8")
								.withData(PASSWORD_RESET_SUBJECT)))
				.withSource(FROM);
		SendEmailResult  sendEmailResult= client.sendEmail(request);
		if(sendEmailResult != null && (sendEmailResult.getMessageId() != null 
				&& !sendEmailResult.getMessageId().isEmpty())) {
			returnValue = true;
		}
		
		return returnValue;
	}
}

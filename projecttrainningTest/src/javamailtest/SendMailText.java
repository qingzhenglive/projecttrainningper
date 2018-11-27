package javamailtest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/*
 * ���
 * 
 */
public class SendMailText {
	// �����˵�ַ
	public static String senderAddress = "test2014123@126.com";
	// �ռ��˵�ַ
	public static String recipientAddress = "2939479023@qq.com";
	// �������˻���
	public static String senderAccount = "test2014123";
	// �������˻�����
	public static String senderPassword = "123456abcd";

	public static void main(String[] args) throws Exception {
		// 1�������ʼ��������Ĳ�������
		Properties props = new Properties();
		// �����û�����֤��ʽ
		props.setProperty("mail.smtp.auth", "true");
		// ���ô���Э��
		props.setProperty("mail.transport.protocol", "smtp");
		// ���÷����˵�SMTP��������ַ
		props.setProperty("mail.smtp.host", "smtp.126.com");
		// 2��������������Ӧ�ó�������Ļ�����Ϣ�� Session ����
		Session session = Session.getInstance(props);
		// ���õ�����Ϣ�ڿ���̨��ӡ����
		session.setDebug(true);
		// 3�������ʼ���ʵ������
		Message msg = getMimeMessage(session);
		// 4������session�����ȡ�ʼ��������Transport
		Transport transport = session.getTransport();
		// ���÷����˵��˻���������
		transport.connect(senderAccount, senderPassword);
		// �����ʼ��������͵������ռ��˵�ַ��message.getAllRecipients() ��ȡ�������ڴ����ʼ�����ʱ��ӵ������ռ���, ������, ������
		transport.sendMessage(msg, msg.getAllRecipients());

		// ���ֻ�뷢�͸�ָ�����ˣ���������д��
		// transport.sendMessage(msg, new Address[]{new InternetAddress("xxx@qq.com")});
		System.out.println("�ɹ������ʼ�......");
		// 5���ر��ʼ�����
		transport.close();
	}

	/**
	 * ��ô���һ���ʼ���ʵ������
	 * 
	 * @param session
	 * @return
	 * @throws MessagingException
	 * @throws AddressException
	 */
	public static MimeMessage getMimeMessage(Session session) throws Exception {
		// ����һ���ʼ���ʵ������
		MimeMessage msg = new MimeMessage(session);
		// ���÷����˵�ַ
		msg.setFrom(new InternetAddress(senderAddress));
		/**
		 * �����ռ��˵�ַ���������Ӷ���ռ��ˡ����͡����ͣ�����������һ�д�����д���� MimeMessage.RecipientType.TO:����
		 * MimeMessage.RecipientType.CC������ MimeMessage.RecipientType.BCC������
		 */
		msg.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(recipientAddress));
		// �����ʼ�����
		msg.setSubject("�ʼ�����", "UTF-8");
		// �����ʼ�����
		// msg.setContent("�򵥵Ĵ��ı��ʼ���HelloWorld", "text/html;charset=UTF-8");
		String readHTML = readHTML();
		msg.setContent(readHTML, "text/html; charset=utf-8");
		// �����ʼ��ķ���ʱ��,Ĭ����������
		msg.setSentDate(new Date());
		return msg;
		
	}
	// ��ȡҳ��
	public static String readHTML() throws IOException {
		String spath = "E:\\eclipse space\\seleniumTestNG\\test-output\\emailable-report.html";
		InputStreamReader isReader = null;
		BufferedReader bufReader = null;
		StringBuffer buf = new StringBuffer();
		try {
			File file = new File(spath);
			isReader = new InputStreamReader(new FileInputStream(file), "utf-8");
			bufReader = new BufferedReader(isReader, 1);
			String data;
			while ((data = bufReader.readLine()) != null) {

				buf.append(data);
			}

		} catch (Exception e) {
			// TODO �����쳣
		} finally {
			// TODO �ر���
			isReader.close();

			bufReader.close();

		}
		// System.out.print(buf.toString());
		return buf.toString();
	}

}

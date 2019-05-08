package com.example.sftpclient;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class App {
	public static void main(String[] args) {
		App app = new App();
		try {
			app.connectSFTP();
		} catch (JSchException e) {
			e.printStackTrace();
		} catch (SftpException e) {
			e.printStackTrace();
		}
	}

	public void connectSFTP() throws JSchException, SftpException {
		JSch jsch = new JSch();
		Session session;
		session = jsch.getSession("sftpuser", "localhost", 22);
		session.setPassword("secret");
		session.setConfig("StrictHostKeyChecking", "no");
		session.connect();

		System.out.println("Session connted: " + session.isConnected());

		ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");
		channelSftp.connect();
		System.out.println("Channel connted: " + channelSftp.isConnected());
		List<String> sqllist = new ArrayList<String>();
		sqllist.add("BLABLABLA1");
		sqllist.add("BLABLABLA2");
		sqllist.add("BLABLABLA4");
		sqllist.add("BLABLABLA5");
		System.out.println(sqllist.toString());
		StringJoiner sentences = new StringJoiner("\n");
		for (String s : sqllist) {
			sentences.add(s);
		}

		System.out.println(sentences.toString());
		InputStream src = new ByteArrayInputStream(sentences.toString().getBytes());
		channelSftp.put(src, "IMFILENEM.sql");
	}

}

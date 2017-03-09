package edu.tamuc.util;

import org.apache.commons.io.IOUtils; 
import org.apache.commons.net.ftp.FTPClient; 

import java.io.File; 
import java.io.FileInputStream; 
import java.io.FileNotFoundException;
import java.io.IOException; 
import java.io.FileOutputStream; 
import java.util.Properties;
 
public class FtpUtil { 
   
	private static String ip;
	private static String user;
	private static String pw;
	
	public FtpUtil(){
		config();
	}

    public static void fileUpload(String fileDir, String serverFile) { 
        FTPClient ftpClient = new FTPClient(); 
        FileInputStream fis = null; 

        try { 
            ftpClient.connect(ip); 
            ftpClient.login(user, pw); 

            File srcFile = new File(fileDir); 
            fis = new FileInputStream(srcFile); 
            ftpClient.changeWorkingDirectory("/"); 
            ftpClient.setBufferSize(1024); 
            ftpClient.setControlEncoding("utf-8"); 
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE); 
            ftpClient.storeFile(serverFile, fis); 
        } catch (IOException e) { 
            e.printStackTrace(); 
            throw new RuntimeException("FTP error", e); 
        } finally { 
            IOUtils.closeQuietly(fis); 
            try { 
                ftpClient.disconnect(); 
            } catch (IOException e) { 
                e.printStackTrace(); 
                throw new RuntimeException("FTP close error", e); 
            } 
        } 
    } 

   
    public static void fileDownload(String serverFile, String clientFile) { 
        FTPClient ftpClient = new FTPClient(); 
        FileOutputStream fos = null; 

        try { 
        	ftpClient.connect(ip); 
            ftpClient.login(user, pw); 

            String remoteFileName = serverFile; 
            fos = new FileOutputStream(clientFile); 

            ftpClient.setBufferSize(1024); 
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE); 
            ftpClient.retrieveFile(remoteFileName, fos); 
        } catch (IOException e) { 
            e.printStackTrace(); 
            throw new RuntimeException("FTP error", e); 
        } finally { 
            IOUtils.closeQuietly(fos); 
            try { 
                ftpClient.disconnect(); 
            } catch (IOException e) { 
                e.printStackTrace(); 
                throw new RuntimeException("FTP close error", e); 
            } 
        } 
    }
    
    public void config(){
		Properties p = new Properties();
		FileInputStream in = null;
		try {
			in = new FileInputStream("ftp.properties");
			p.load(in);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		ip = p.getProperty("ip");
		user = p.getProperty("user");
		pw = p.getProperty("pw");
	}
} 

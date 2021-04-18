
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPConnectionClosedException;
import org.apache.commons.net.ftp.FTPFile;

import org.apache.commons.net.ftp.FTPReply;

import org.apache.commons.net.io.CopyStreamEvent;
import org.apache.commons.net.io.CopyStreamListener;


import java.io.*;

public class Client {
    boolean error=false;
    //String  username,pas;
    //  String  server;
    //   int port;
    FTPClient ftp;
    Client(){
        ftp =new FTPClient();
    }
    void connect(String server, int port){
        try
        {
            ftp.setControlEncoding("UTF-8");
            final int reply;
            if (port > 0) {
                ftp.connect(server, port);
            } else {
                ftp.connect(server);
            }
            System.out.println("Connected to " + server + " on " + (port>0 ? port : ftp.getDefaultPort()));

            // After connection attempt, you should check the reply code to verify
            // success.
            reply = ftp.getReplyCode();

            if (!FTPReply.isPositiveCompletion(reply))
            {
                ftp.disconnect();
                System.err.println("FTP server refused connection.");
                System.exit(1);
            }
        }
        catch (final IOException e)
        {
            if (ftp.isConnected())
            {
                try
                {
                    ftp.disconnect();
                }
                catch (final IOException f)
                {
                    // do nothing
                }
            }
            System.err.println("Could not connect to server.");
            e.printStackTrace();
            System.exit(1);
        }
    }
    void login(String username,String pas){
        try {
            if (!ftp.login(username, pas))
            {
                ftp.logout();
                error = true;
            }
//            System.out.println("Remote system is " + ftp.getSystemType());
//            ftp.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void server_content() throws IOException {
        //   ftp.setListHiddenFiles(false);


        for (final FTPFile f : ftp.listFiles("")) {
            System.out.println(f.getRawListing());
//            System.out.println(f.toFormattedString());
        }
    }
    boolean download(String filename,String filepath) throws IOException {
        OutputStream input = new FileOutputStream(filepath);
        return  ftp.retrieveFile(filename,input);

    }
    boolean upload(String filename, String filepath) throws IOException {
        final InputStream input = new FileInputStream("F:\\home\\host1\\error.txt");
        return  ftp.storeFile(filename,input);
    }
    boolean delete(String filename) throws IOException {
        return ftp.deleteFile(filename);
    }
    boolean rename(String oldname,String newname) throws IOException {
        return ftp.rename(oldname,newname);

    }
}

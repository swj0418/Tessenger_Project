package jeong.pro.messenger.server.authentication;

import java.io.*;
import java.net.InetAddress;
import java.util.HashMap;

public class Authenticator implements Runnable {
    public static HashMap<String, Boolean> authorizedUsers = new HashMap<>();

    private File User_Info_File = new File("/Users/top1/Workspace/Programming/Java/Tessenger_Project/src/" +
            "jeong/pro/messenger/server/data/userinfodata/User_Info");
    private BufferedReader inputReader;
    String[] toValidate = null;

    public Authenticator(String[] userInfo) {
        System.out.println("Authentication request from : " + userInfo[0]);
        this.toValidate = userInfo;
    }

    private void validateUser() {
        try {
            String line = "";
            while ((line = inputReader.readLine()) != null) {
                String[] splitID_Password = line.split(",");

                // If there is an id match, perform validation
                if(toValidate[0].equals(splitID_Password[0])) {
                    if (toValidate[1].equals(splitID_Password[1])) {
                        authorizedUsers.put(splitID_Password[0], true);
                        System.out.println("User : " + toValidate[1] + " ::: Authorized");
                        break;
                    } else {
                        authorizedUsers.put(splitID_Password[0], false);
                        System.out.println("User : " + toValidate[0] + " ::: Unauthorized");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            inputReader = new BufferedReader(new InputStreamReader(new FileInputStream(User_Info_File)));
            validateUser();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}

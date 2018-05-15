package jeong.pro.messenger.server.authentication;

import java.io.*;
import java.net.InetAddress;

public class Authenticator implements Runnable {
    private File User_Info_File = new File("F:\\PUBLIC\\Project\\Messenger\\src\\jeong\\pro\\messenger\\server\\data\\userinfodata\\User_Info");
    private BufferedReader inputReader;
    private boolean validUser = false;
    String[] toValidate = null;

    public Authenticator(String[] userInfo) {
        System.out.println("Authentication request from : " + userInfo[0]);
        this.toValidate = userInfo;
    }

    public boolean isValidUser() {
        return validUser;
    }

    private void validateUser() {
        try {
            String line = "";
            while ((line = inputReader.readLine()) != null) {
                String[] splitID_Password = line.split(",");

                // If there is an id match, perform validation
                if(toValidate[0].equals(splitID_Password[0])) {
                    if (toValidate[1].equals(splitID_Password[1])) {
                        validUser = true;
                        AuthenticationSessionContainer.getAuthenticationSessionContainerInstance().appendSession(toValidate[0], toValidate[1]);
                        AuthenticationSessionContainer.getAuthenticationSessionContainerInstance().setAuthorized(toValidate[0]);
                        System.out.println("User : " + toValidate[1] + " ::: Authorized");
                        break;
                    } else {
                        validUser = false;
                        AuthenticationSessionContainer.getAuthenticationSessionContainerInstance().appendSession(toValidate[0], toValidate[1]);
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

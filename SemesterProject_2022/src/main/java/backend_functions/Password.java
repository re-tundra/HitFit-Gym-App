package backend_functions;

import com.hitfit.database.DatabaseFunctions;
import org.apache.commons.codec.digest.DigestUtils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Password {


    public static String isCustomerOrEmployee;

    private static void checkCustomerEmployee(){

    }

    public static String[] makeFinalPassword(String password) {

        String changedPassword = DigestUtils.sha3_256Hex(password);
        SecureRandom secureRandom = null;
        String[] passSalt = new String[2];

        try {
            secureRandom = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error! " + e);
        }

        assert secureRandom != null;
        int tempRandom1 = secureRandom.nextInt(100000, 999999);
        String random1 = Integer.toString(tempRandom1);
        changedPassword = changedPassword + random1;

        passSalt[0] = random1;
        passSalt[1] = changedPassword;

        return passSalt;
    }

    public static boolean verifyPassword(String customerUsernameEmail, String enteredPassword) {
        try {
            String passwordToMatch = null;
            int i = 0;

            if (isCustomerOrEmployee.equals("customer")){
                passwordToMatch = DatabaseFunctions.getAccountPassword(customerUsernameEmail, "customers");
            } else if (isCustomerOrEmployee.equals("employee")) {
                passwordToMatch = DatabaseFunctions.getAccountPassword(customerUsernameEmail, "employees");
            }

//            String changedPassword = DigestUtils.sha3_256Hex(enteredPassword);

//            changedPassword = changedPassword + userSaltPassword[0];

            if (enteredPassword.equals(passwordToMatch)) {
                System.out.println("Access granted.");
                return true;
            } else {
                System.out.println("wrong password");
                return false;
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}

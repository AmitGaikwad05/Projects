
import java.util.*;

// =========================== User Info class ===============================
class Info {
  protected int acc_no;
  protected String name;
  protected String acc_type;
  protected int pin;
  protected long contact_no;
  protected double balance = 0.0;

}

// ============================ User class ==================================
class User extends Info {
  // Info obj1Info = new Info();
  Scanner sc = new Scanner(System.in);

  // --------------- parameterized constructor (passing account number as argument) -----------------
  User(int account_no) {
    this.acc_no = account_no;
  }

  void begin() {
    System.out.println("\n============================ ATM system =================================\n");
    System.out.println(
        " To create new account - 1 \n To deposit cash - 2 \n To withdraw cash - 3 \n To check balance - 4 \n To reset your account pin - 5 \n To exit - 6");
    int ch = sc.nextByte();
    sc.nextLine(); // important : consumes the newline character left in the input buffer due to previous nextInt() method.
    switch (ch) {

      case 1:
        createAccount();
        break;

      case 2:
        cashDeposit();
        break;

      case 3:
        withdraw();
        break;

      case 4:
        checkBalance();
        break;

      case 5:
        resetPin();
        break;

      case 6:
        System.out.println("Sure you want to exit ? ");
        break;

      default:
        System.out.println("Invalid choice");
        break;
    }

    System.out.println("\nTo continue with transaction - 1 \nTo exit - 0");
    int ex = sc.nextInt();
    if (ex == 0) {
      System.out
          .println("\n ---------------------------- Thankyou !... Visit again --------------------------------\n");
    } else {
      begin();
    }

  }

  // ====================================== 1. Create account  ==============================================
  void createAccount() {
    System.out.println("\n---------------- Creating account -----------------\n");
    System.out.print("Enter your name : ");
    this.name = sc.nextLine();
    accType();
    contact();
    pin();
    System.out.println("\n------------------------ Account Created successfully --------------------------\n");
    System.out.println("\n------------- Account details ---------------\n");
    System.out.println("Account number : " + this.acc_no);
    System.out.println("Account holder name : " + this.name);
    System.out.println("Account type : " + this.acc_type);
    System.out.println("Contact no : " + this.contact_no);
    System.out.println("Balance : " + this.balance);

  }

  // ----------------- Account type validation -------------------
  void accType() {
    System.out.print("Select A/C type (Saving/Current) : ");
    String tempAccType = sc.nextLine().toLowerCase();

    if (tempAccType.equals("saving") || tempAccType.equals("current")) {
      this.acc_type = tempAccType;
    } else {
      System.out.println("---------------------------------------------");
      System.out.println("Incorrect A/C type, Enter the correct choice");
      accType();
    }

  }

  // ----------------- Contact validation -------------------

  void contact() {

    System.out.print("Enter the contact number : ");
    long contact = sc.nextLong();
    String numberAsString = String.valueOf(contact);
    int digit = numberAsString.length();
    if (digit != 10) {
      System.out.println("---------------------------------------------");
      System.out.println("Invalid contact number. Enter a 10 digit contact number");
      contact();
    } else {
      this.contact_no = contact;
    }

  }

  void pin() {
    System.out.print("Create a pin (4 digits) : ");
    int tempPin = sc.nextInt();
    System.out.print("Re-enter the pin : ");
    int tempPinVerify = sc.nextInt();
    if (tempPin == tempPinVerify) {
      this.pin = tempPin;
    } else {
      System.out.println("---------------------------------------------");
      System.out.println("The pin do not match. Create the pin again");
      pin();
    }
    
  }
  
  // ====================================== 2. Deposit cash ==============================================
  void cashDeposit() {
    System.out.println("\n---------------- Cash deposit -----------------\n");
    accVerify();
    pinVerify();
    System.out.print("Enter the amount you want to deposit : ");
    double depAmount = sc.nextDouble();
    this.balance += depAmount;
    System.out.println("Amount of Rs. " + depAmount + " is credited in your " + this.acc_type + " account.");
    System.out.println("Total balance : Rs. " + this.balance);
  }
  
  
  
  // ====================================== 3. Withdraw cash ==============================================
  
  void withdraw() {
    System.out.println("\n---------------- Cash Withdraw -----------------\n");
    accVerify();
    pinVerify();
    System.out.print("Enter the amount you want to withdraw : ");
    double wdAmount = sc.nextDouble();
    this.balance -= wdAmount;
    System.out.println("Amount of Rs. " + wdAmount + " is debited from your " + this.acc_type + " account.");
    System.out.println("Remaining balance : Rs. " + this.balance);
  }
  
  
  // ====================================== 4. Check balance ==============================================
  
  void checkBalance(){
    System.out.println("\n---------------- Cash Withdraw -----------------\n");
    accVerify();
    pinVerify();
    System.out.println("Available Balance : Rs. " + this.balance);
  }
  
  
  // ====================================== 5. Reset pin ==============================================
  void resetPin(){
    System.out.println("\n---------------- Reset Account Pin -----------------\n");

    System.out.print("Enter the old pin : ");
    int oldPin = sc.nextInt();
    if (oldPin!= this.pin) {
      System.out.println("The pin is incorrect");
      System.out.print("Forgot pin ? (Yes/No) : ");
      String choice = sc.next().toLowerCase();

      if(choice.equals("yes")){
        System.out.print("\nEnter your registered contact number : ");
        long tempContact = sc.nextLong();
            if (tempContact != this.contact_no) {
              System.out.println("---------------------------------------------");
              System.out.println("The number is not registerd with this bank account. Enter the registered or a valid contact number and try again");
            }
            else{
              newPin();
            }

      }

      else{
        resetPin();
      }
    }
    else{
      newPin();
    }
    
  }

  //   ----------- New pin -------------
void newPin(){
  System.out.print("Enter a new pin (4 digits) : ");
              int newPin = sc.nextInt();
              System.out.print("Confirm pin : ");
              int cnfNewPin = sc.nextInt();
              if (newPin == cnfNewPin) {
                this.pin = newPin;
                System.out.println("\n ------------ Your pin is changed successfully --------------\n");
              }
              else{
                System.out.println("The pin does not match");
              }
}


  // ----------------- Account number verification (Global) -------------------
  void accVerify() {
    System.out.print("Enter the Account number : ");
    int tempAccNo = sc.nextInt();
    if (tempAccNo != this.acc_no) {
      System.out.println("---------------------------------------------");
      System.out.println(
          "The account number is Incorrect or this account does not exist. Try again with the correct account number");
      accVerify();
    }
  }

  // ----------------- Pin verification (Global) -------------------
  void pinVerify() {
    System.out.print("Enter the pin : ");
    int tempPin = sc.nextInt();
    if (tempPin != this.pin) {
      System.out.println("---------------------------------------------");
      System.out.println("Incorrect pin ! Enter the correct pin");
      pinVerify();
    }
  }

} // ---- user class ends here

// =========================== Main class ===============================
public class AtmSystem {
  public static void main(String[] args) {
    User obj1 = new User(1001);
    obj1.begin();

  }
}

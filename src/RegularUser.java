public class RegularUser extends User{
    private String privatekey;
    private float walletBalance;

    RegularUser(String walletName, String publickey, String privatekey){
        this.walletName = walletName;
        this.userAddress = publickey;
        this.privatekey = privatekey;
        this.walletBalance = 0;
    }

    public float getWalletBalance(){
        return walletBalance;
    }
    public void Send(float amount, String pk){
        if(pk.equals(this.privatekey)){
            walletBalance -= amount;
        }
    }
    public void Receive(float amount){
        walletBalance += amount;
    }
}

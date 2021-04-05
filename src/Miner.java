public class Miner extends User{
    private String privatekey;
    private float walletBalance;
    private float minerBalance;

    Miner(String walletName, String publickey, String privatekey){
        this.walletName = walletName;
        this.userAddress = publickey;
        this.privatekey = privatekey;
        this.minerBalance = 0;
        this.walletBalance = 0;
    }

    public float getWalletBalance(){
        return walletBalance;
    }
    public float getMinerBalance(){
        return minerBalance;
    }
    public void Convert(float amount, String pk){
        if(minerBalance >= amount && pk.equals(this.privatekey)){
            walletBalance = walletBalance + amount;
            minerBalance -= amount;
        }else
            System.out.println("Insufficient Funds to convert!");
    }
    public void Send(String t ,float amount, String pk){
        if(pk.equals(this.privatekey)){
            Transaction newSend = new Transaction(this.userAddress, t, amount);
        }
    }
    public void Receive(float amount){
        walletBalance += amount;
    }
    public void minerReceive(float amount){
        minerBalance += amount;
    }

}

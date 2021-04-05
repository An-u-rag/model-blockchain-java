import java.lang.*;

// This class implements from Data interface as it contains hash values directly.
public class Transaction implements Data{
    /*
        Transaction
            1. Sender/Source address of the transaction - sourceAddr
            2. Receiver/Destination address of the transaction - destAddr
            3. Amount transferred between them - amount
            - OVERLOADED CONSTRUCTORS for differentiating between miner transaction and Regular transaction.
                - A miner transaction does not have a source address other than the system itself.
            - Necessary getters and setters for the class.
            - A function for Calculating the hash of the transaction.
     */
    private String sourceAddr;
    private String destAddr;
    private float amount;
    private String _sHash;

    Transaction(String sourceAddr, String destAddr, float amount){
        this.sourceAddr = sourceAddr;
        this.destAddr = destAddr;
        this.amount = amount;
        this._sHash = calculateHash();
    }

    Transaction(String destAddr, boolean _isFee, float amount){
        if(_isFee){
            this.sourceAddr = "0000";
            this.destAddr = destAddr;
            this.amount = amount;
            this._sHash = calculateHash();
        }
    }

    public String getSourceAddr() {
        return sourceAddr;
    }

    public void setSourceAddr(String sourceAddr) {
        this.sourceAddr = sourceAddr;
    }

    public String getDestAddr() {
        return destAddr;
    }

    public void setDestAddr(String destAddr) {
        this.destAddr = destAddr;
    }

    public float getAmount() {
        return amount;
    }

    public String getHash(){
        return _sHash;
    }

    public String calculateHash(){
        String combinationString = this.sourceAddr + this.destAddr + String.valueOf(this.amount);
        int hashint = combinationString.hashCode();
        _sHash = Integer.toString(Math.abs(hashint));
        return _sHash;
    }

}

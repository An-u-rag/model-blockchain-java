import java.util.*;
import java.sql.Timestamp;

// This class implements from Data interface as it contains hash values directly.
public class Block implements Data{
    /*
        -> Block
            1. Hash of previous block
            2. Index Number
            3. Data
            4. Time in
            5. Block Hash
            -Constructor to construct with required inputs
            -A function to calculate current hash
            -A function to return current hash
            -A function to validate or to "mine" the block
    */
    // Required Fields for a block in blockchain
    public String sPrevHash;

    public int _index;
    private int _nNonce;
    private List<Transaction> _sData;
    private Timestamp _inTime;
    private String _sHash;

    // Here we have CONSTRUCTOR OVERLOADING and this reference.
    // This is needed to differentiate between Genesis and Regular Transaction Blocks and for future flexibility.
    Block(){
        this._nNonce = -1;
        this._index = -1;
        this._sData = null;
        this._inTime = null;
    }

    Block(int _index){
        this();
        if(_index==0){
            this._index = 0;
            this._nNonce = 0;
            Date date = new Date();
            long time = date.getTime();
            this._inTime = new Timestamp(time);
            this._sHash = "GenesisHash";
        }
    }
    Block(int _index, List<Transaction> _sData) {
        this();
        if(_index > 0){
            this._index = _index;
            this._nNonce = -1;
            this._sData = _sData;
            Date date = new Date();
            long time = date.getTime();
            this._inTime = new Timestamp(time);
            this._sHash = calculateHash();
        }
    }

    //Required Methods in a Block including getters
    public int get_nNonce() {
        return _nNonce;
    }

    public List<Transaction> get_sData() {
        return _sData;
    }

    // A function to calculate the hash of the block after all details have been fed into the block.
    public String calculateHash() {
        String combinationString = this._nNonce + this.sPrevHash + this._index + String.valueOf(this._inTime) + this._sData.hashCode();
        int hashint = combinationString.hashCode();
        _sHash = Integer.toString(Math.abs(hashint));
        return _sHash;
    }

    public String getHash() {
        return _sHash;
    }

    // This function helps Validate and Verify a block before inserted onto the Blockchain.
    // This method is also called Proof of Work.
    // This also generates the mining reward for the miner after successful validation.
    // Here I have also used METHOD OVERLOADING
    // to differentiate between calling Blocks which have Miner Transaction VS Regular Transactions.
    public void MineBlock(){
        int nDifficulty = 1;
        char[] cstr = new char[nDifficulty];
        for(int i=0;i<nDifficulty;i++){
            cstr[i] = '1';
        }
        String diffString = new String(cstr);


        this._nNonce = 0;
        do{
            this._nNonce++;
            this._sHash = calculateHash();
            System.out.println("Hash with nonce " + this._nNonce + " and difficulty: "+nDifficulty+": "+ this._sHash);
        }while(!_sHash.substring(0, nDifficulty).equals(diffString));

        System.out.println("Hashes mined: "+ _nNonce);
        System.out.println("Hash of block: "+_sHash);
    }

    public void MineBlock(int nDifficulty) {
        char[] cstr = new char[nDifficulty];
        for(int i=0;i<nDifficulty;i++){
            cstr[i] = '1';
        }
        String diffString = new String(cstr);


        this._nNonce = 0;
        do{
            this._nNonce++;
            this._sHash = calculateHash();
            System.out.println("Hash with nonce " + this._nNonce + " and difficulty: "+nDifficulty+": "+ this._sHash);
        }while(!_sHash.substring(0, nDifficulty).equals(diffString));

        System.out.println("Hashes mined: "+ _nNonce);
        System.out.println("Hash of block: "+_sHash);
    }

}
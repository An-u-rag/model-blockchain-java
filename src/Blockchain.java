import java.util.Vector;

public class Blockchain {

    /*
        Blockchain
            1. Difficulty level of blockchain - nDifficulty
            2. The reward for mining a block based on number of transaction in the block - minerReward
            3. The main Vector of Block Objects which is the foundation of the Blockchain - _vChain
            - Constructor to instantiate a blockchain and genesis block with difficulty.
            - A function to return the Blockchain Vector
            - A function to add a new block onto the blockchain
            - A function to reference the latest element in the block chain i.e. the latest Block.
     */
    private int nDifficulty;
    private float minerReward;
    private Vector<Block> _vChain = new Vector<Block>();


    Blockchain(){
        this.nDifficulty = 2;
        _vChain.add(new Block(0));
    }

    Vector<Block> get_vChain(){
        return _vChain;
    }

    Transaction AddBlock(Block bNew, String minerHash){
        if(bNew.get_sData().size() == 1){
            minerReward = 5;
        }else{
            minerReward = 10;
        }
        bNew.sPrevHash = _GetLastBlock().getHash();
        bNew.MineBlock(this.nDifficulty);
        _vChain.add(bNew);
        Transaction miningReward = new Transaction(minerHash, true, minerReward);
        System.out.println("The Miner Hash is: "+ minerHash);
        System.out.println("The Miner has been rewarded of fee: " + miningReward.getAmount() + "$. The transaction has been processed and is waiting to be added to a block!");
        return miningReward;
    }


    public Block _GetLastBlock(){
        return _vChain.lastElement();
    }

}

import java.util.*;

public class BlockchainExample {

    //This is the main function.
    public static void main(String[] args) {
        System.out.println("-----------------JAVA LAB 2 ASSESSMENT - ANURAG BLOCKCHAIN IMPLEMENTATION-------------------");
        System.out.println("Anurag------18BCE2335-----26/March/2021");

        //Instantiation of the main Blockchain Object
        Blockchain bChain = new Blockchain();

        // Instantiation of a few Users of the cryptocurrency.
        Miner anurag = new Miner("Anurag", "18BCE2335", "abc123");
        Miner xyz = new Miner("xyz", "xyz123", "abc123");
        RegularUser john = new RegularUser("John", "John","abc");
        RegularUser ram = new RegularUser("Ram", "Ram","123");



        //Creating two transactions for the first block.
        Transaction firsttran = new Transaction("John", "Ram", 100);
        Transaction secondtran = new Transaction("Ram", "John", 50);

        // Instantiation of Block using a parameterized constructor.
        // Here we are sending the two transaction objects as a list of objects into the Block.
        Block B1 = new Block(1, Arrays.asList(firsttran,secondtran));


        // The previously created transactions have been assigned a null value for garbage collection.
        firsttran = null;
        secondtran = null;
        System.gc();// Garbage Collection

        // This Transaction object is created to add the mining reward transaction into the Blockchain.
        // This Instantiation uses another overloaded Constructor method than previous 2, as the second parameter is a boolean.
        // Here we call a function "AddBlock" Which takes in the Block object and the miner hash.
        // This function adds the block onto the Blockchain after validating and verifying it by Mining. (Proof-of-Work).
        // The Transaction object of the mining reward is then returned to the "mineTrans" object for processing into next block.
        Transaction mineTrans = bChain.AddBlock(B1, anurag.getUserAddress());
        // Transaction execute once validated and block has been added to the blockchain
        john.Send(100, "abc");
        ram.Receive(100);
        ram.Send(50, "123");
        john.Receive(50);


        // Block one is ready for garbage collection as it has already been added to the Blockchain Vector.
        B1 = null;
        System.gc();// Garbage Collection

        // Instantiation of another Block Object to take in the transaction of the mining reward done on the previous block.
        // "mineTrans" is sent as a parameter.
        Block B2 = new Block(2, Arrays.asList(mineTrans));

        // "mineTrans" is now ready for garbage collection as it has already been added to the Block's data.
        mineTrans = null;
        System.gc(); // Garbage Collection

        // The Block gets added onto the blockchain similarly and this mining reward is now assigned to "mineTrans" after
        // garbage collection. Now the cycle repeats.
        mineTrans = bChain.AddBlock(B2, xyz.getUserAddress());
        anurag.minerReceive(10);
        anurag.Convert(10, "abc123");

        // Statements to display the Blockchain and their content.
        for(int i=0;i<bChain.get_vChain().size();i++){
            System.out.println("Block "+i+" :");
            System.out.println("Hash: "+bChain.get_vChain().get(i).getHash());
            System.out.println("Previous Hash: "+bChain.get_vChain().get(i).sPrevHash);
            System.out.println("\t\tThe transactions of this block are: ");
            System.out.println("\t\t"+"Sender ---> Amount ---> Receiver");
            if(bChain.get_vChain().get(i).get_sData()!=null)
                bChain.get_vChain().get(i).get_sData().forEach(transaction -> {
                    System.out.print("\t\t"+transaction.getSourceAddr()+"--->");
                    System.out.print(" "+transaction.getAmount()+"--->");
                    System.out.println(" "+transaction.getDestAddr());
                });
            else
                System.out.println("\t\t"+"genesis block has no transactions");
            System.out.println();
        }
        System.out.println("THE BALANCES OF EACH USER ARE: ");
        System.out.println("Anurag : " + anurag.getWalletBalance() + " Miner Balance: "+ anurag.getMinerBalance());
        System.out.println("XYZ : " + xyz.getWalletBalance()+ " Miner Balance: "+ xyz.getMinerBalance());
        System.out.println("John : " + john.getWalletBalance());
        System.out.println("Ram : " + ram.getWalletBalance());
    }
}
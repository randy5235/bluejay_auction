import java.util.ArrayList;

/**
 * A simple model of an auction.
 * The auction maintains a list of lots of arbitrary length.
 *
 * @author David J. Barnes and Michael Kölling.
 * @version 2011.07.31
 * 
 * Modified by: Randy Wressell
 * Date:    8/7/2014
 * 
 * Modifications:
 * 4.48: add close method to the Auction class.
 * 4.49 add getUnsold method to Auction class.
 * 4.51 Modified getLot method to iterate through the lot collection to match lotNumber
 *      to lot.number ( in object)
 *      
 * 4.52 add a removeLot method to Auction class
 */
public class Auction
{
    // The list of Lots in this auction.
    private ArrayList<Lot> lots;
    // The number that will be given to the next lot entered
    // into this auction.
    private int nextLotNumber;

    /**
     * Create a new auction.
     */
    public Auction()
    {
        lots = new ArrayList<Lot>();
        nextLotNumber = 1;
    }

    /**
     * Enter a new lot into the auction.
     * @param description A description of the lot.
     */
    public void enterLot(String description)
    {
        lots.add(new Lot(nextLotNumber, description));
        nextLotNumber++;
    }

    /**
     * Show the full list of lots in this auction.
     */
    public void showLots()
    {
        for(Lot lot : lots) {
            System.out.println(lot.toString());
        }
    }
    
    /**
     * Make a bid for a lot.
     * A message is printed indicating whether the bid is
     * successful or not.
     * 
     * @param lotNumber The lot being bid for.
     * @param bidder The person bidding for the lot.
     * @param value  The value of the bid.
     */
    public void makeABid(int lotNumber, Person bidder, long value)
    {
        Lot selectedLot = getLot(lotNumber);
        if(selectedLot != null) {
            Bid bid = new Bid(bidder, value);
            boolean successful = selectedLot.bidFor(bid);
            if(successful) {
                System.out.println("The bid for lot number " +
                                   lotNumber + " was successful.");
            }
            else {
                // Report which bid is higher.
                Bid highestBid = selectedLot.getHighestBid();
                System.out.println("Lot number: " + lotNumber +
                                   " already has a bid of: " +
                                   highestBid.getValue());
            }
        }
    }

    /**
     * Return the lot with the given number. Return null
     * if a lot with this number does not exist.
     * @param lotNumber The number of the lot to return.
     * 
     * Ex   4.51
     * 
     * Modified By: Randy Wressell
     * Date 8/7/2014
     * 
     * Updated the getLot method so that it checks the lot.number value 
     * instead of the index value
     */
    public Lot getLot(int lotNumber)
    {   Lot returnLot = null; // create a null Lot
       for (Lot lot : lots) //for each through the collection
       {
           if (lotNumber == lot.getNumber()) // check if lotNumber is equal to the lot.number
           {
            returnLot = lot; //set returnLot to lot object and break
            
           break;
           }
        }
         
       if (returnLot == null) // if no matching lot was found return null and print error.
       {
           System.out.println("Lot number: " + lotNumber +
                               " does not exist.");
            
       }
        return returnLot;
        
        
//         if((lotNumber >= 1) && (lotNumber < nextLotNumber)) {
//             // The number seems to be reasonable.
//             Lot selectedLot = lots.get(lotNumber - 1);
//             // Include a confidence check to be sure we have the
//             // right lot.
//             if(selectedLot.getNumber() != lotNumber) {
//                 System.out.println("Internal error: Lot number " +
//                                    selectedLot.getNumber() +
//                                    " was returned instead of " +
//                                    lotNumber);
//                 // Don't return an invalid lot.
//                 selectedLot = null;
//             }
//             return selectedLot;
//         }
//         else {
//             System.out.println("Lot number: " + lotNumber +
//                                " does not exist.");
//             return null;
//         }
    }
    
    /**
     * Ex 4.48
     * 
     * Name: close
     * 
     * Description: print out details of the lots/
     * Description: Print out all lot items with highest bidder name and bid value
     *              or return that item was not sold.
     * @author  Randy Wressell
     * @version  8/7/2014
     */
    public void close() 
    {
        for ( Lot lot : lots) // loop through lots collection for each item
        {
            Bid highestBid = lot.getHighestBid(); // get the bid to check if there is a bid
            
            if (highestBid == null) // if there is no bid on a lot item then tell us.
            {
                System.out.println("the " + lot.getDescription() + " has no bids");
            }
            else 
            {
                long highestBidValue = lot.getHighestBid().getValue(); // get bid value
                
                //return the lot name and bidder name and value of highest bid
                System.out.println("the " + lot.getDescription() + "'s winner is " + 
                                    lot.getHighestBid().getBidder().getName() +" with a bid of " +
                                    highestBidValue);
            }
        }
    }
    
    /**
     * ex 4.49
     * 
     * Name: getUnsold
     * Description: Return a list of all items that are not currently sold
     * 
     * @author Randy Wressell
     * @version 8/7/2014
     */
    
    public ArrayList<Lot> getUnsold()
    {
        ArrayList<Lot> unSoldLots = new ArrayList<Lot>(); // create an empty collection of lots
        for (Lot lot : lots)
        {
            
            Bid isSold = lot.getHighestBid(); // get the highest bid if it exists
            if (isSold == null) // if null add lot to new collection
            {
                unSoldLots.add(lot);  
            }
            
        }
        return unSoldLots;
    }
    
    /**
     * Remove the lot with given lot number
     * @param number the number ofthe lot to be removed
     * @return theLot with the given number, or null if
     * there is no such lot.
     * 
     * ex 5.52
     * Name: removeLot
     * Description: remove a lot from the collection
     * 
     */
    public Lot removeLot(int number)
    {
        Lot removeLot = null;  //set return to null
        for (Lot lot:lots) //iterate throw collection
        {
            if (number == lot.getNumber()) //check lot for value
            {
                lots.remove(lot); // remove from collection
                removeLot = lot; //set return value
                break;
            }
            
        }
        return removeLot; // return lot value
    }
}

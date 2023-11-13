/**
 *
 *
 *
 */

/**
 * RandomTransactionGenerator:
 * • created with a Bank instance
 * • randomly generates deposit and withdrawal transactions of up to EUR 10,000 for random
 * accounts in the bank and submits them to the bank queue for processing
 * • sleeps for a random amount of time (between 0 and 1 seconds) between the generation of
 * transactions.
 * • one it has been terminated, it inserts an end-of-stream (or “Poison pill”) object to the bank
 * queue to indicate that it is closed.
 */


public class RandomTransactionGenerator {
}

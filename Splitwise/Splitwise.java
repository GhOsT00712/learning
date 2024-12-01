package Splitwise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/*
 * Given a list of payments, split the payments such that each person pays the same amount.
 * If a person has paid more than the share, they should receive the difference from the others.
 * If a person has paid less than the share, they should pay the difference to the others.
 * The result should be a list of strings, where each string represents a payment from one person to another.
 */
public class Splitwise {
    /**
     * Time complexity: O(nlogn)
     * Space complexity: O(n)
     * where n is the number of payments.
     * 
     * @param payments a list of payments, where each payment is represented by a
     *                 name and an amount.
     * @return a list of strings, where each string represents a payment from one
     *         person to another.
     */
    public List<String> split(List<Payment> payments) {
        List<String> result = new ArrayList<>();
        int totalAmount = getTotalAmount(payments);
        int eachShare = totalAmount / payments.size();
        List<Payment> positivePayments = getPositivePayments(payments, eachShare);
        List<Payment> negativePayments = getNegativePayments(payments, eachShare);
        Collections.sort(positivePayments, (a, b) -> Integer.compare(b.amount(), a.amount()));
        Collections.sort(negativePayments, (a, b) -> Integer.compare(b.amount(), a.amount()));
        int pIdx = 0;
        int nIdx = 0;

        while (pIdx < positivePayments.size() && nIdx < negativePayments.size()) {
            Payment p = positivePayments.get(pIdx);
            Payment n = negativePayments.get(nIdx);
            int amount = Math.min(p.amount(), n.amount());
            result.add(buildString(n, p, amount));
            if (p.amount() == n.amount()) {
                pIdx++;
                nIdx++;
            } else if (amount == n.amount()) {
                positivePayments.set(pIdx, new Payment(p.name(), p.amount() - amount));
                nIdx++;
            } else {
                negativePayments.set(nIdx, new Payment(n.name(), n.amount() - amount));
                pIdx++;
            }
        }
        return result;
    }

    /// Helper functions
    
    /// Get the total amount of the payments
    private int getTotalAmount(List<Payment> payments) {
        return payments.stream().mapToInt(Payment::amount).sum();
    }

    /// Get the payments that are positive subtracted by the share
    /// i.e. the payments that are more than the share
    private List<Payment> getPositivePayments(List<Payment> payments, int eachShare) {
        return payments.stream()
                .filter(payment -> payment.amount() - eachShare > 0)
                .map(payment -> new Payment(payment.name(), payment.amount() - eachShare))
                .collect(Collectors.toList());
    }

    /// Get the payments that are negative subtracted by the share
    /// i.e. the payments that are less than the share
    private List<Payment> getNegativePayments(List<Payment> payments, int eachShare) {
        return payments.stream()
                .filter(payment -> payment.amount() - eachShare < 0)
                .map(payment -> new Payment(payment.name(), (-1) * (payment.amount() - eachShare)))
                .collect(Collectors.toList());
    }

    /// Build the string that represents a payment from one person to another
    /// e.g. "Alice pays $ 10 to Bob"
    private String buildString(Payment payee, Payment receiver, int amount) {
        return payee.name() + " pays $ " + amount + " to " + receiver.name();
    }
}

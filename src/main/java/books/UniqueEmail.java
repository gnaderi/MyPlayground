package books;

import java.util.HashMap;
import java.util.Map;

class UniqueEmail {


    public static void main(String[] args) {
        String[] emails = {"test.email+alex@leetcode.com", "test.e.mail+bob.cathy@leetcode.com", "testemail+david@lee.tcode.com"};
        System.out.println("new UniqueEmail().numUniqueEmails(emails) = " + new UniqueEmail().numUniqueEmails(emails));
        emails = new String[]{"a@leetcode.com", "b@leetcode.com", "c@leetcode.com"};
        System.out.println("new UniqueEmail().numUniqueEmails(emails) = " + new UniqueEmail().numUniqueEmails(emails));

    }
    public static final String AT_SIGN = "@";
    public static final String EMAIL_FORMAT = "%s@%s";

    public int numUniqueEmails(String[] emails) {
        Map<String, Integer> map = new HashMap<>();
        for (String email : emails) {
            email = email.trim();
            String[] emailParts = email.split(AT_SIGN);
            if (emailParts.length != 2) {
                continue;//discarded
            }
            emailParts[0] = emailParts[0].replace(".", "").trim();
            int i = emailParts[0].indexOf("+");
            emailParts[0] = i > 0 ? emailParts[0].substring(0, i) : emailParts[0];
            map.put(String.format(EMAIL_FORMAT, emailParts[0], emailParts[1]), 1);
        }
        return map.size();
    }
}
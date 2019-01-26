package services;

public class Endpoints {

    private static String ENDPOINT
            = "http://parabank.parasoft.com/parabank/services/bank/";

    public static String CREATE_ACCOUNT
            = ENDPOINT + "createAccount?customerId=%s&newAccountType=0&fromAccountId=54321";

    public static String GET_ACCOUNT
            = ENDPOINT + "accounts/%d";

    public static String DEPOSIT
            = ENDPOINT + "deposit?accountId=%d&amount=%.2f";

    public static String WITHDRAW
            = ENDPOINT + "withdraw?accountId=%d&amount=%.2f";

}

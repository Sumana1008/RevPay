# RevPay
This payment gateway works as a service for other businesses. Which means anyone
who wants to collect payment as a business from anyone, they can use RevPay and start
collecting payments.

Features:
1. A business would be able to register themselves using a username and password.
2. Then they would be able to create multiple accounts. An account have following
   things:
   a. A Unique Identifier for each account so that each account if different.
   b. A bank account number.
   i. Numeric and could be up to 10 digits.
   c. A sort code/IFSC Code.
   i. Numeric and up to 8 digits.
   d. An activation status:
   i. ACTIVE: Means the account is active and we can accept payments
   ii. INACTIVE: Means the account is inactive and we should reject
   all (INCOMING as well as OUTGOING) payments for the account.
3. As each bank account can have CREDIT as well as DEBIT kind of transactions by
   default,  Where we should be able to control if we can accept only credit or only debit kind of
   transactions. By default, both type of transactions is allowed.
4. Created an API to create deposit and withdrawal transaction from an account.
   a. This transaction is of any amount and each transaction would go to a bank
   account. We can just accept bank account details of the beneficiary together
   with the RevPay accountId in your API request.
5. Placed a daily withdrawal limitation on an account and didn’t allow withdrawal above
   this amount.
6. Created an API to return balance of an account.
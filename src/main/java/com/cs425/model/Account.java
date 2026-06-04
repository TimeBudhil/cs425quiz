package com.cs425.model;

import java.time.LocalDate;
import java.time.Period;


public class Account {
    private long accountId;
    private Customer customer;
    private String accountNumber;
    private AccountType accountType;
    private LocalDate dateOpened;
    private double balance;
    
    public Account(Customer customer, long accountId, String accountNumber, AccountType accountType, LocalDate dateOpened,
            double balance) {
        this.customer = customer;
        this.accountId = accountId;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.dateOpened = dateOpened;
        this.balance = balance;
    }

    public long getAccountId() {
        return accountId;
    }
    public String getAccountNumber() {
        return accountNumber;
    }
    public AccountType getAccountType() {
        return accountType;
    }
    public LocalDate getDateOpened() {
        return dateOpened;
    }
    public double getBalance() {
        return balance;
    }
    public Customer getCustomer(){
        return customer;
    }

    /**helper functions */
    private int getAccountAge() {
        return Period.between(dateOpened, LocalDate.now()).getYears();
    }

    public AccountTier determineAccountTier() {
        int age = getAccountAge();

        if (age >= 10 && balance >= 100000) {
            return AccountTier.PLATINUM;
        }

        if (age >= 5 && balance >= 50000) {
            return AccountTier.GOLD;
        }

        if (age >= 2 && balance >= 10000) {
            return AccountTier.SILVER;
        }

        return AccountTier.REGULAR;
    }
    

    /**equality */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (accountId ^ (accountId >>> 32));
        result = prime * result + ((accountNumber == null) ? 0 : accountNumber.hashCode());
        result = prime * result + ((accountType == null) ? 0 : accountType.hashCode());
        result = prime * result + ((dateOpened == null) ? 0 : dateOpened.hashCode());
        long temp;
        temp = Double.doubleToLongBits(balance);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Account other = (Account) obj;
        if (accountId != other.accountId)
            return false;
        if (accountNumber == null) {
            if (other.accountNumber != null)
                return false;
        } else if (!accountNumber.equals(other.accountNumber))
            return false;
        if (accountType != other.accountType)
            return false;
        if (dateOpened == null) {
            if (other.dateOpened != null)
                return false;
        } else if (!dateOpened.equals(other.dateOpened))
            return false;
        if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Account [accountId=" + accountId + ", accountNumber=" + accountNumber + ", accountType=" + accountType
                + ", dateOpened=" + dateOpened + ", balance=" + balance + "]";
    }

    public String toJSON() {
        StringBuilder json = new StringBuilder();

        json.append("{\n");
        json.append("    \"accountId\": ").append(accountId).append(",\n");
        json.append("    \"accountNumber\": \"").append(accountNumber).append("\",\n");
        json.append("    \"accountType\": \"").append(accountType).append("\",\n");
        json.append("    \"dateOpened\": \"").append(dateOpened).append("\",\n");
        json.append("    \"balance\": ").append(balance).append(",\n");
        json.append("    \"accountTier\": \"").append(determineAccountTier()).append("\",\n");
        json.append("    \"customerId\": \"")
            .append(customer != null ? customer.getCustomerId() : "null")
            .append("\"\n");
        json.append("}");

        return json.toString();
    }
    
}


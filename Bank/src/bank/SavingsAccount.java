package bank;

public class SavingsAccount {
    private String number;
    private String client;
    private double value;
    private String key;

    public SavingsAccount(String number, String client, String key) {
        this.number = number;
        this.client = client;
        this.key = key;
        value=0;
    }

    @Override
    public String toString() {
        return "SavingsAccount{" + "number=" + number +
                ", client=" + client + ", value=" + value + '}';
    }

    public String getNumber() {
        return number;
    }

 
    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public double getValue() {
        return value;
    }

    private String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
    
    public void Deposit(double amount)
    {
        value+=amount;
    }
    
    public int Withdraw(double amount, String key)
    {
        if(value>=amount)
        {
            if(this.key.equals(key))
            {
                value-=amount;
                return 100; //retiro exitoso
            }
            return 200; //clave incorrecta
        }
        return 300; //saldo insuficiente
     }
    
}

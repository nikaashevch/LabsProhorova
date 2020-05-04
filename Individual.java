public class Individual {
    private Account[] accounts;
    private int size;

    public Individual() {
        this.accounts = new Account[16];
        this.size = 0;
    }

    public Individual(int size) {
        this.accounts = new Account[size];
        this.size = 0;
    }

    public Individual(Account[] accounts) {
        this.accounts = accounts;
        this.size = accounts.length;
    }

    public boolean add(Account account){
        if(size==accounts.length){
            extendArray();
            return false;
        }else{
            hideAdd(account);
            return true;
        }
    }

    public boolean add(Account account,int index){
        if(accounts[index]==null){
            accounts[index] = account;
            return true;
        }else{
            return false;
        }
    }

    public Account get(int index){
        return accounts[index];
    }

    public Account get(String number){
        for (Account account:accounts) {
            if(account.getNumber().equals(number)){
                return account;
            }
        }
        return null;
    }

    public boolean hasAccountWithNumber(String number){
        for(Account account: accounts){
            if(account.getNumber().equals(number))return true;
        }
        return false;
    }

    public Account set(Account account,int index){
        Account buf = accounts[index];
        accounts[index] = account;
        return buf;
    }

    public Account remove(int index){
        Account buf = accounts[index];
        accounts[index] = null;
        for(int i = index;i<accounts.length-1;i++){
            Account tmp = accounts[i];
            accounts[i] = accounts[i+1];
            accounts[i+1] = tmp;
        }
        accounts[accounts.length] = null;
        size--;
        return buf;
    }

    public Account remove(String number){
        return remove(indexOf(number));
    }

    public int indexOf(String number){
        for(int i = 0; i<accounts.length;i++){
            if(accounts[i].getNumber().equals(number))
                return i;
        }
        return -1;
    }

    private void extendArray(){
        Account[] buf = new Account[accounts.length*2];
        System.arraycopy(accounts,0,buf,0,accounts.length);
        accounts = buf;
    }

    private void hideAdd(Account account){
        for(int i = 0; i<accounts.length;i++){
            if(accounts[i]==null){
                accounts[i] = account;
                size++;
                return;
            }
        }
    }

    //Возвращает общее число счетов
    public int size(){
        return size;
    }

    //С учётом того что после каждого удаления массив сдвигается, нулов быть не должно
    public Account[] getAccounts(){
        Account[] buf = new Account[size];
        System.arraycopy(accounts,0,buf,0,size);
        return buf;
    }

    public Account[] getSortedAccountsByBalance(){
        Account[] buf = getAccounts();
        for(int i = 0; i<buf.length;i++){
            for(int j = 0; j<buf.length-1;j++){
                if(buf[j].getBalance() > buf[j+1].getBalance()){
                    Account tmp = buf[j];
                    buf[j] = buf[j+1];
                    buf[j+1] = tmp;
                }
            }
        }
        return buf;
    }

    public double getTotalBalance(){
        double totalBalance = 0;
        for(Account account:accounts){
            totalBalance+=account.getBalance();
        }
        return totalBalance;
    }
}
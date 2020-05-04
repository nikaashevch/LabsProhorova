public class AccountManager {
    private Individual[] individuals;
    private int size;

    public AccountManager(int size) {
        this.individuals = new Individual[size];
        this.size = 0;
    }

    public AccountManager(Individual[] individuals) {
        this.individuals = individuals;
        this.size = individuals.length;
    }

    public boolean add(Individual individual){
        if(size==individuals.length){
            extendArray();
            return false;
        }else{
            hideAdd(individual);
            return true;
        }
    }

    public boolean add(Individual individual,int index){
        if(individuals[index]==null){
            individuals[index] = individual;
            return true;
        }else{
            return false;
        }
    }

    public Individual get(int index){
        return individuals[index];
    }

    public Individual set(Individual individual, int index){
        Individual buf = individuals[index];
        individuals[index] = individual;
        if(buf == null && individual != null) size++;
        if(buf!=null && individual == null) size--;
        return buf;
    }

    public Individual remove(int index){
        Individual buf = individuals[index];
        individuals[index] = null;
        for(int i = index;i<individuals.length-1;i++){
            Individual tmp = individuals[i];
            individuals[i] = individuals[i+1];
            individuals[i+1] = tmp;
        }
        individuals[individuals.length] = null;
        size--;
        return buf;
    }

    //Возвращат число физ. лиц
    public int size(){
        return size;
    }

    public Individual[] getIndividuals(){
        Individual[] buf = new Individual[size];
        System.arraycopy(individuals,0,buf,0,size);
        return buf;
    }

    public Individual[] getSortedIndividualsByTotalBalance(){
        Individual[] buf = getIndividuals();
        for(int i = 0; i<buf.length;i++){
            for(int j = 0; j<buf.length-1;j++){
                if(buf[j].getTotalBalance() > buf[j+1].getTotalBalance()){
                    Individual tmp = buf[j];
                    buf[j] = buf[j+1];
                    buf[j+1] = tmp;
                }
            }
        }
        return buf;
    }

    public Account getAccountWithNumber(String number){
        for(Individual individual:individuals){
            if(individual.get(number)!=null){
                return individual.get(number);
            }
        }
        return null;
    }

    public Account removeAccount(String number){
        for(Individual individual:individuals){
            Account buf = individual.remove(number);
            if(buf!=null){
                return buf;
            }
        }
        return null;
    }

    public Account setAccount(Account account,String number){
        for(Individual individual:individuals){
            if(individual.indexOf(number)>0){
                return individual.set(account,individual.indexOf(number));
            }
        }
        return null;
    }

    private void extendArray(){
        Individual[] buf = new Individual[individuals.length*2];
        System.arraycopy(individuals,0,buf,0,individuals.length);
        individuals = buf;
    }

    private void hideAdd(Individual individual){
        for(int i = 0; i<individuals.length;i++){
            if(individuals[i]==null){
                individuals[i] = individual;
                size++;
                return;
            }
        }
    }

}


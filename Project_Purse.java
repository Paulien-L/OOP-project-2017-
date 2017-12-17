public class Purse extends Item {

    public Purse(long ID, double value, int capacity)throws IllegalArgumentException{
        super(ID, value);
        this.setCapacity(capacity);
    }

    public boolean isValidID(long PurseId){
        if (PurseId <1){
            return false;
        } else{
            long a = 1;
            long b = 1;
            long c = 1;
            while(c < PurseId){
                c = a+b;
                a = b;
                b = c;
            }
            if(c == PurseId)
                return true;
            else
                return false;
        }
    }
    public double getPurseValue(){
        return (this.getValue()+this.dukats);
    }
    private int dukats;

    public int getDukats(){
        return this.dukats;
    }
    private void setDukats(int newvalue){
        if(newvalue >= 0 && newvalue <= this.getCapacity())
            this.dukats = newvalue;
        else if (newvalue < 0)
            this.dukats = 0;
        else
            this.purseTear();
    }
    private void addDukats(int numberToAdd){
        setDukats(this.getDukats() + numberToAdd);
    }
   // error when there are insufficient dukats?
    private void removeDukats(int numberToDistract){
        setDukats(this.getDukats() - numberToDistract);
    }

    private int capacity;

    public int getCapacity(){
        return this.capacity;
    }
    //dukats
    void setCapacity(int capacity){
        if (capacity>0)
            this.capacity = capacity;
        else
            this.capacity = 1;
    }

    private void purseTear(){
        this.setValue(0);
        this.setDukats(0);
    }

	@Override
	public boolean isValidHolder(Monster holder) {
		// TODO Auto-generated method stub
		return false;
	}


}

public class Purse extends Item {

	public Purse(long ID, float weight, double value, ItemHolder holder, int capacity, int dukats)throws IllegalArgumentException{
        super(ID, weight, value, holder);

        this.setCapacity(capacity);
        this.setDukats(dukats);

    }
    @Override
    public void setID(long PurseId){
        if (PurseId <0){
            PurseId = Math.abs(PurseId);
            setID(PurseId);
        } else if (PurseId == 0) {
            PurseId = 1;
            setID(PurseId);
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
                super.setID(PurseId);
            else
                super.setID(c);
        }
    }


    public double getValue(){
        return (super.getValue()+this.dukats);
    }

    public double getOwnValue(){
        return super.getValue();
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

    private void removeDukats(int numberToDistract){
        if (numberToDistract <= this.dukats)
            setDukats(this.getDukats() - numberToDistract);
        else
            System.out.println("Insufficient dukats to remove");
    }

    public void transferDukats(Purse otherpurse, int numberToTransfer){
        if (numberToTransfer <= this.dukats) {
            this.removeDukats(numberToTransfer);
            otherpurse.addDukats(numberToTransfer);
        } else
            System.out.println("Insufficient dukats to transfer, transfer cancelled");
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

    public float getWeight(){
        return super.getWeight() + (this.getDukats()*50);
    }

    public float purseWeight(){
        return super.getWeight();
    }

	@Override
	public boolean isValidValue(double value) {
		return (value >= 0);
	}

	
	
	

}

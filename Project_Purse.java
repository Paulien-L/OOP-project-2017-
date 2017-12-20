package Items;

import Monsters.Monster;

public class Purse extends Item {

    public Purse(long ID, double value, int capacity, int dukats)throws IllegalArgumentException{
        if(!isValidID(ID))
            throw new IllegalArgumentException();
        else
            this.setID(ID);
        this.setValue(value);
        this.setCapacity(capacity);
        this.setDukats(dukats);
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
    public double getPurseTotalValue(){
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

    public boolean isValidHolder(Monster monster){
        if (monster.getPurse() == null)
            return true;
        else
            return false;
    }
}

package domain;

import domain.Employee;

public class Driver extends Employee {

    public String turn;
    public int hours;
    private boolean active;
    
    /**/
    public double salary1;

    public Driver() {
        this.turn = "";
        this.hours = 2;
    }
//, int hours, String turno
    public Driver(String name, String lastName, double salary, int id, int employeeNumber, boolean collegeDegree, String turn,int hours,boolean active) {
        super(name, lastName, salary, id, employeeNumber, collegeDegree);
        this.turn=turn;
        this.hours=hours;
        this.active=active;
    }


    public double calculateSalary(int hours,String turn,boolean degree) {

        //Normal Salary
        int salaryxhour = 30;
        

        //hours extra per turn
        
        if (turn.toLowerCase() == "noche") {
            salary1 = salaryxhour * (hours* 2);
        }else {
            salary1 = salaryxhour * hours;
        }
        //extras per degree
        
        if (degree == true) {
            salary1 += (salary1 * 0.395);
        }
        return salary1;

    }

    /**
     * @return the hours
     */
    public int getHours() {
        return hours;
    }

    /**
     * @param hours the hours to set
     */
    public void setHours(int hours) {
        this.hours = hours;
    }

    @Override
    public int size() {

        return super.size()+this.turn.length()*2+4;
    }
     @Override
    public String toString() {
        return super.toString()+" turno: "+this.getTurn()+" horas trabajadas "+this.hours+"\n";
                
    }

    /**
     * @return the turn
     */
    public String getTurn() {
        return turn;
    }

    /**
     * @param turn the turn to set
     */
    public void setTurn(String turn) {
        this.turn = turn;
    }

    /**
     * @return the active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }

}
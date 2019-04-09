package domain;

import domain.Employee;

public class Admin extends Employee {

    public int telephoneNumber;
    public int categoryType;
    public double salary;
    public boolean active;

    public Admin(String name, String lastName, double salary, int id, int employeeNumber, boolean collegeDegree, int telephoneNumber, int categoryType,boolean active) {
        super(name, lastName, salary, id, employeeNumber, collegeDegree);
        this.telephoneNumber = telephoneNumber;
        this.categoryType = categoryType;
        this.active=active;
    }

    public Admin() {
        
        this.categoryType = 0;
        this.telephoneNumber = 0;
        this.active = true;
    }

    /**
     * @return the telephoneNumber
     */
    public int getTelephoneNumber() {
        return telephoneNumber;
    }

    /**
     * @param telephoneNumber the telephoneNumber to set
     */
    public void setTelephoneNumber(int telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    /**
     * @return the categoryType
     */
    public int getCategoryType() {
        return categoryType;
    }

    /**
     * @param categoryType the categoryType to set
     */
    public void setCategoryType(int categoryType) {
        this.categoryType = categoryType;
    }

    public double calculateSalary(int category,boolean college) {

      
        salary = 250;
        if (category == 2) {
            salary += ((250 / 5) * 1);
        }
        //extras per degree
        double finalSalary = salary;
      
        if (college == true) {
            finalSalary += (salary * 0.395);
        }
        return finalSalary;

    }

    @Override
    public int size() {

        return super.size() + 4+4;
    }
     @Override
    public String toString() {
        return super.toString()+"Telephone Number: " +this.telephoneNumber + " Category Type: "+this.categoryType+"\n";
                
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

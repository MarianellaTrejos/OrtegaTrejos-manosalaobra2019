package domain;

import domain.Employee;

public class Janitor extends Employee {

    double yearsExperience;
    int age;
    double Salary1 = 120;
    double hoursextra;
    private boolean active;

    public Janitor() {
        this.yearsExperience = 0;
        this.age = 0;
    }

    public Janitor(String name, String lastName, double salary, int id, int employeeNumber, boolean collegeDegree, double yearsExperience, int age, boolean active) {
        super(name, lastName, salary, id, employeeNumber, collegeDegree);
        this.yearsExperience = yearsExperience;
        this.age = age;
        this.active=active;

    }

    public double getYearsExperience() {
        return yearsExperience;
    }

    public void setYearsExperience(double yearsExperience) {
        this.yearsExperience = yearsExperience;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    
    
    public double calculateSalary(int hours,boolean college) {

        //Extra Salary
        if (hours != 0) {
            Salary1 += (((120 / 4) * 1) * hours);
        }
        //extras per degree
        double finalSalary = Salary1;
  
        if (college == true) {
            finalSalary += (Salary1 * 0.395);
        }
        return finalSalary;

    }

     @Override
    public int size() {

        return super.size() + 4+4;
    }

    @Override
    public String toString() {
        return super.toString()+" Years of experience: "+this.yearsExperience+" Age: "+this.age+"\n";
                
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

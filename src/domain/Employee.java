package domain;

public class Employee {

    public String name;
    public String lastName;
    public double salaryperhour;//pregunta el monto por hora para multiplicarlo por las horas trabajadas
    public int id;
    public int employeeNumber;
    public boolean collegeDegree;
    //double bonus=salaryperhour;     

    public Employee() {
        this.name = "";
        this.lastName = "";
        this.id = 0;
        this.employeeNumber = 0;
        this.collegeDegree = true;
    }

    public Employee(String name, String lastName, double salary, int id, int employeeNumber, boolean collegeDegree) {
        this.name = name;
        this.lastName = lastName;
        this.salaryperhour = salary;
        this.id = id;
        this.employeeNumber = employeeNumber;
        this.collegeDegree = collegeDegree;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the salary
     */
    public double getSalary() {
        return salaryperhour;
    }

    /**
     * @param salary the salary to set
     */
    public void setSalary(double salary) {
        this.salaryperhour = salary;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the employeeNumber
     */
    public int getEmployeeNumber() {
        return employeeNumber;
    }

    /**
     * @param employeeNumber the employeeNumber to set
     */
    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    /**
     * @return the collegeDegree
     */
    public boolean isCollegeDegree() {
        return collegeDegree;
    }

    /**
     * @param collegeDegree the collegeDegree to set
     */
    public void setCollegeDegree(boolean collegeDegree) {
        this.collegeDegree = collegeDegree;
    }

    public String toString() {
        return "Name: " + this.name + " - lastName: "
                + this.lastName + " - Salary: " + this.salaryperhour + " - id: " + this.id 
                + " - Number Employee: "+this.employeeNumber+ " -College Degree: " +this.collegeDegree;
                
    }

    public int size() {
       
        return this.getName().length() * 2
                + this.getLastName().length() * 2
                + 4
                +4
                + 1
                + 4;
    }

    public double calculateSalary() {

        return 0;

    }
   
}

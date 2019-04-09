package domain;

import domain.Driver;

public class vehicle {

    public int serie;
    public float mileage;
    public double totalSalary;
    public String name;
    public int year;
    public boolean american;
    private boolean active;
    //nombre (20 caracteres), año (entero), kilometraje (flotante), americano (booleano) y serie (entero).

    public vehicle() {
        this.serie = 0;
        this.mileage = 0;
        this.totalSalary=0.0;
        this.name = "";
        this.year = 0;
        this.american = true;
    }
//    public vehicle d = new Driver();

    public vehicle( int serie,String name, int year, boolean american, float mileage, double totalSalary,boolean active) {
        this.serie = serie;
        this.mileage = mileage;
        this.totalSalary = totalSalary;
        this.name = name;
        this.year = year;
        this.american = american;
        this.active=active;
    }

    public double finalSalary;

    public double calculateExtra() {
        //Driver dv=new Driver();
        String tipoCarga = "";

        String tipoVehiculo = "";

        if (tipoVehiculo.toLowerCase() == "carga pesada") {

            switch (tipoCarga.toLowerCase()) {

                case "vagoneta":
                    setTotalSalary(getTotalSalary() + 6);
                    break;
                case "grua":
                    setTotalSalary(getTotalSalary() + 8);
                    break;
                case "montacargas":
                    setTotalSalary(getTotalSalary() + 14);
                    break;

            }

            //setFinalSalary(getTotalSalary();
        }
        return getFinalSalary();

    }

    /**
     * @return the serie
     */
    public int getSerie() {
        return serie;
    }

    /**
     * @param serie the serie to set
     */
    public void setSerie(int serie) {
        this.serie = serie;
    }

    /**
     * @return the mileage
     */
    public float getMileage() {
        return mileage;
    }

    /**
     * @param mileage the mileage to set
     */
    public void setMileage(float mileage) {
        this.mileage = mileage;
    }

    /**
     * @return the totalSalary
     */
    public double getTotalSalary() {
        return totalSalary;
    }

    /**
     * @param totalSalary the totalSalary to set
     */
    public void setTotalSalary(double totalSalary) {
        this.totalSalary = totalSalary;
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
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * @return the american
     */
    public boolean isAmerican() {
        return american;
    }

    /**
     * @param american the american to set
     */
    public void setAmerican(boolean american) {
        this.american = american;
    }


    /**
     * @return the finalSalary
     */
    public double getFinalSalary() {
        return finalSalary;
    }

    /**
     * @param finalSalary the finalSalary to set
     */
    public void setFinalSalary(double finalSalary) {
        this.finalSalary = finalSalary; 
    }

    public int size() {
        return this.getName().length() * 2 + 4 + 1 + 4 + 4+4+1;

    }
    
    public String toString() {
        return "Marca: "+this.name+" Año: "+this.year+ " Americano: "+this.american+" Serie: "+this.serie+" Kilometraje: "+this.mileage+" Salario total: "+this.totalSalary+"\n";
                
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

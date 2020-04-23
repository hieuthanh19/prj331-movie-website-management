/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movieWebsiteManager;

/**
 *
 * @author ThanhKH
 */
public class Director {
    private int d_id;
    private String d_name;
    private int d_age;
/**
 * create new Director
 * @param d_id
 * @param d_name
 * @param d_age 
 */
    public Director(int d_id, String d_name, int d_age) {
        this.d_id = d_id;
        this.d_name = d_name;
        this.d_age = d_age;
    }

    public int getD_id() {
        return d_id;
    }
/**
 * check id Director
 * @param d_id
 * @throws DirectorException 
 */
    public void setD_id(int d_id) throws DirectorException{
        if (d_id<=0) {
            throw new DirectorException(" ID Disrector greater than 0");
        }
        this.d_id = d_id;
    }

    public String getD_name() {
        return d_name;
    }
/**
 * check name Director
 * @param d_name
 * @throws DirectorException 
 */
    public void setD_name(String d_name) throws DirectorException{
        if (d_name.isEmpty()) {
            throw new DirectorException("Name Director is not null");
        }
        this.d_name = d_name;
    }

    public int getD_age() {
        return d_age;
    }
/**
 * check age Director
 * @param d_age
 * @throws DirectorException 
 */
    public void setD_age(int d_age) throws DirectorException{
        if (d_age<=0) {
            throw new DirectorException("Age Director greater tha 0");
        }
        this.d_age = d_age;
    }

    @Override
    public String toString() {
        return d_name+ " (ID: "+ d_id+")"; //To change body of generated methods, choose Tools | Templates.
    }
    
}

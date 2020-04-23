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
public class Actor {

    private int a_id;
    private String a_name;
    private int a_age;

    /**
     * create new Actor
     *
     * @param a_id actor ID
     * @param a_name actor name
     * @param a_age actor age
     */
    public Actor(int a_id, String a_name, int a_age) {
        this.a_id = a_id;
        this.a_name = a_name;
        this.a_age = a_age;
    }

    public int getA_id() {
        return a_id;
    }

    /**
     * check Actor id
     *
     * @param a_id
     * @throws ActorException
     */
    public void setA_id(int a_id) throws ActorException {
        if (a_id <= 0) {
            throw new ActorException("ID Actor greater than 0");
        }
        this.a_id = a_id;
    }

    public String getA_name() {
        return a_name;
    }

    /**
     * check name actor
     *
     * @param a_name
     * @throws ActorException
     */
    public void setA_name(String a_name) throws ActorException {
        if (a_name.isEmpty()) {
            throw new ActorException(" Name Actor is not null");
        }
        this.a_name = a_name;
    }

    public int getA_age() {
        return a_age;
    }

    /**
     * check age actor
     *
     * @param a_age
     * @throws ActorException
     */
    public void setA_age(int a_age) throws ActorException {
        if (a_age <= 0) {
            throw new ActorException(" Age Actor greater than 0");
        }
        this.a_age = a_age;
    }

    @Override
    public String toString() {
        return a_name + " (ID: " + a_id + ")"; //To change body of generated methods, choose Tools | Templates.
    }

}

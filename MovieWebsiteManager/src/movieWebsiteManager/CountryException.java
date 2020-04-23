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
public class CountryException extends Exception {

    /**
     * Create new Country Exception
     *
     * @param msg
     */
    public CountryException(String msg) {
        super("Country Exception: " + msg);
    }

}

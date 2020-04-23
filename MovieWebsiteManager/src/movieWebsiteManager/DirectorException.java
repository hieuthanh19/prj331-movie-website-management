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
public class DirectorException extends Exception {

    /**
     * Create new Director Exception
     *
     * @param msg
     */
    public DirectorException(String msg) {
        super("Director Exception: " + msg);
    }
}

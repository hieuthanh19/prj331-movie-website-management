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
public class MovieException extends Exception {
/**
 * Create new Movie Exception
 * @param msg 
 */
    public MovieException(String msg) {
        super("Movie Exception: " + msg);
    }
}

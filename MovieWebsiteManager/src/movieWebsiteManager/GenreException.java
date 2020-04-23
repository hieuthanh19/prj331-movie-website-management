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
public class GenreException extends Exception {
/**
 * Create new GenreException
 * @param msg 
 */
    public GenreException(String msg) {
        super("Genre Exception: " + msg);
    }

}

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
public class GenreInMovieException extends Exception {
/**
 * Create new Genre In Movie Exception
 * @param msg 
 */
    public GenreInMovieException(String msg) {
        super("Genre In Movie Exception: ");
    }
}

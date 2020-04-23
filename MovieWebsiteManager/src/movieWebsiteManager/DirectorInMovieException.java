/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movieWebsiteManager;

/**
 *
 * @author Dell
 */
public class DirectorInMovieException extends Exception{
    /**
     * Create new Country Exception
     *
     * @param msg
     */
    public DirectorInMovieException(String msg) {
        super("Director In MovieException: " + msg);
    }
}

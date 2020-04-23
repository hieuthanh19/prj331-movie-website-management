package movieWebsiteManager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ThanhKH
 */
public class FavoriteMovieException extends Exception {

    /**
     * Create new favorite movie exception
     *
     * @param msg
     */
    public FavoriteMovieException(String msg) {
        super("Favorite Movie Exception: " + msg);
    }
}

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
public class StudioException extends Exception {

    /**
     * Create new Studio Exception
     *
     * @param msg
     */
    public StudioException(String msg) {
        super("Studio Exception: " + msg);
    }
}

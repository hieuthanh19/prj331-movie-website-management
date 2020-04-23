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
public class Genre {
    private int g_id;
    private String g_name;
/**
 * create new genre
 * @param g_id
 * @param g_name 
 */
    public Genre(int g_id, String g_name) {
        this.g_id = g_id;
        this.g_name = g_name;
    }

    public int getG_id() {
        return g_id;
    }
/**
 * check id genre
 * @param g_id
 * @throws GenreException 
 */
    public void setG_id(int g_id) throws GenreException{
        if (g_id<=0) {
            throw new GenreException("Genre ID greater than 0");
        }
        this.g_id = g_id;
    }

    public String getG_name() {
        return g_name;
    }
/**
 * check genre name
 * @param g_name
 * @throws GenreException 
 */
    public void setG_name(String g_name) throws GenreException{
        if (g_name.isEmpty()) {
            throw new GenreException("Genre name is not null");
        }
        this.g_name = g_name;
    }

    @Override
    public String toString() {
        return g_name+ " (ID: "+ g_id+")"; //To change body of generated methods, choose Tools | Templates.
    }
    
}

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
public class GenreInMovie {

    //attributes
    private int m_id;
    private int g_id;

    /**
     * Create new Genre In Movie
     *
     * @param m_id
     * @param g_id
     */
    public GenreInMovie(int m_id, int g_id) {
        this.m_id = m_id;
        this.g_id = g_id;
    }

    /**
     * Get movie ID
     *
     * @return
     */
    public int getM_id() {
        return m_id;
    }
/**
 * Set movie ID
 * @param m_id
 * @throws GenreInMovieException 
 */
    public void setM_id(int m_id) throws GenreInMovieException {
        if (m_id<=0){
            throw new GenreInMovieException("Movie ID must be greater than 0");
        }
        this.m_id = m_id;
    }

    /**
     * get genre ID
     *
     * @return
     */
    public int getG_id() {
        return g_id;
    }
/**
 * set genre ID
 * @param g_id
 * @throws GenreInMovieException 
 */
    public void setG_id(int g_id) throws GenreInMovieException {
        if (g_id <=0){
            throw new GenreInMovieException("Genre ID must be greater than 0");
        }
        this.g_id = g_id;
    }

}

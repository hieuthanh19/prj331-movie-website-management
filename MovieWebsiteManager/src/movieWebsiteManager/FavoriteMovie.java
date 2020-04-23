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
public class FavoriteMovie {

    //attributes
    private int c_id;
    private int m_id;

    /**
     * Create new Favorite Movie
     *
     * @param c_id
     * @param m_id
     */
    public FavoriteMovie(int c_id, int m_id) {
        this.c_id = c_id;
        this.m_id = m_id;
    }

    /**
     * Get client ID
     *
     * @return
     */
    public int getC_id() {
        return c_id;
    }

    /**
     * Set client ID
     *
     * @param c_id
     * @throws FavoriteMovieException
     */
    public void setC_id(int c_id) throws FavoriteMovieException {
        if (c_id <= 0) {
            throw new FavoriteMovieException("Client ID must be greater than 0");
        }
        this.c_id = c_id;
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
     * set movie ID
     *
     * @param m_id
     * @throws FavoriteMovieException
     */
    public void setM_id(int m_id) throws FavoriteMovieException {
        if (m_id <= 0) {
            throw new FavoriteMovieException("Movie ID must be greater than 0");
        }
        this.m_id = m_id;
    }

}

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
public class DirectorInMovie {

    private int m_id;
    private int d_id;

    /**
     * create new DirectorInMovie
     *
     * @param m_id
     * @param d_id
     */
    public DirectorInMovie(int m_id, int d_id) {
        this.m_id = m_id;
        this.d_id = d_id;
    }

    public int getM_id() {
        return m_id;
    }

    /**
     * check Movie id
     *
     * @param m_id
     * @throws DirectorException
     */
    public void setM_id(int m_id) throws DirectorException {
        if (m_id <= 0) {
            throw new DirectorException("Movie id greater than 0");
        }
        this.m_id = m_id;
    }

    public int getD_id() {
        return d_id;
    }

    /**
     * check id Director
     *
     * @param d_id
     * @throws DirectorException
     */
    public void setD_id(int d_id) throws DirectorException {
        if (d_id <= 0) {
            throw new DirectorException("Director ID greater than 0");
        }
        this.d_id = d_id;
    }

}

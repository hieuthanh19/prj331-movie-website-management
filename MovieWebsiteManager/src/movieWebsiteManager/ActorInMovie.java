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
public class ActorInMovie {

    private int m_id;
    private int a_id;

    /**
     * create new ActorInMovie
     *
     * @param m_id
     * @param a_id
     */
    public ActorInMovie(int m_id, int a_id) {
        this.m_id = m_id;
        this.a_id = a_id;
    }

    public int getM_id() {
        return m_id;
    }

    /**
     * check id Movie
     *
     * @param m_id
     * @throws ActorInMovieException
     */
    public void setM_id(int m_id) throws ActorInMovieException {
        if (m_id <= 0) {
            throw new ActorInMovieException("Movie ID greater than 0");
        }
        this.m_id = m_id;
    }

    public int getA_id() {
        return a_id;
    }

    /**
     * check Actor ID
     *
     * @param a_id
     * @throws ActorInMovieException
     */
    public void setA_id(int a_id) throws ActorInMovieException {
        if (a_id <= 0) {
            throw new ActorInMovieException("Actor ID  greater than 0");
        }
        this.a_id = a_id;
    }

}

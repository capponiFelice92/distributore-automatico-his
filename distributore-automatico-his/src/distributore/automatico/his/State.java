/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distributore.automatico.his;

/**
 *
 * @author laptop
 */
public interface State {
    void next(Event e) throws erroreException;
}

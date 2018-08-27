/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alann.ifpb.server;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alann
 */
public class Operation implements Runnable {

    private RowClientRequest requests;

    public Operation(RowClientRequest rowRequest) {

        this.requests = rowRequest;

    }

    @Override
    public void run() {

        int cont = 0;
        while (cont < 3) {
            try {
                if (requests.getRequests().isEmpty()) {
                    Thread.sleep(1000);
                    cont++;
                }
                String data = requests.get();
                System.out.println(data + " na sessao " + requests.getIdSession());
            } catch (InterruptedException ex) {
                Logger.getLogger(Operation.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

}

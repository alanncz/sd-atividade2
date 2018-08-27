/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alann.ifpb.server;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alann
 */
public class RowClientRequest {
    
    private final LinkedBlockingQueue<String> requests;
    private final String idSession;
    
    public RowClientRequest(String idSession) {
        
        this.idSession = idSession;
        requests = new LinkedBlockingQueue();
    }

    public LinkedBlockingQueue<String> getRequests() {
        return requests;
    }

    public String getIdSession() {
        return idSession;
    }

    public String get() {
        
        String data = null;
        try {
            data = requests.take();
        } catch (InterruptedException ex) {
            Logger.getLogger(RowClientRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
        
    }

    public void set(String data) {
        try {
            requests.put(data);
        } catch (InterruptedException ex) {
            Logger.getLogger(RowClientRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

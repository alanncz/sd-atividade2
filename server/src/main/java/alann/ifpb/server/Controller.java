/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alann.ifpb.server;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author alann
 */
@RestController
@RequestMapping("/")
public class Controller {

    private List<RowClientRequest> rows = new ArrayList();
    ExecutorService executor = Executors.newFixedThreadPool(3);

    @RequestMapping(value = "/{name}/{cookie}")
    public String teste(@PathVariable String name, @PathVariable String cookie) {

        if ("null".equals(cookie)) {
            Calendar hoje = Calendar.getInstance();
            cookie = hoje.toInstant().toString();
            RowClientRequest newRow = new RowClientRequest(cookie);
            newRow.set(name);
            rows.add(newRow);
            executor.execute(new Operation(newRow));
            return cookie;
        }
        for (RowClientRequest row : rows) {
            if (row.getIdSession().equals(cookie)) {
                row.set(name);
                break;
            }
        }
        return cookie;
    }

}

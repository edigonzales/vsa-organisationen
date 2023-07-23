package ch.so.agi.vsa.controller;

import java.util.List;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.query.ObjectSelect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.so.agi.vsa.cayenne.Organisation;

@RestController
public class MainController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ObjectContext objectContext;

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        log.info("Hallo Welt");
        return new ResponseEntity<String>("vsa-organisationen", HttpStatus.OK);
    }

    @GetMapping("/foo")
    public String foo() {
        log.info("Hallo Foo");
        
        //List<Organisation> organisationen = ObjectSelect.query(Organisation.class).pageSize(50).orderBy(Organisation.BEZEICHNUNG.getName()).select(objectContext);
        List<Organisation> organisationen = ObjectSelect.query(Organisation.class).select(objectContext);
        for (Organisation organisation : organisationen) {
            log.info(organisation.getBezeichnung() + " -- " + organisation.getTIliTid());
            log.info(Organisation.T_ILI_TID.getName());
            log.info(organisation.getLetzteAenderung());
        }
        
     
        return "foo";
    }
    
    

    
}

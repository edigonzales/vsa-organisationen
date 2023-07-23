package ch.so.agi.vsa.data.service;

import java.util.List;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.query.ObjectSelect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.so.agi.vsa.cayenne.Organisation;

@Service
public class OrganisationService {

    @Autowired
    ObjectContext objectContext;

    public List<Organisation> findAllOrganisations(String stringFilter) {
//        if (stringFilter == null || stringFilter.isEmpty()) { 
//            return contactRepository.findAll();
//        } else {
//            return contactRepository.search(stringFilter);
//        }
        
        return ObjectSelect.query(Organisation.class).orderBy(Organisation.BEZEICHNUNG.getName()).select(objectContext);

        
    }

}

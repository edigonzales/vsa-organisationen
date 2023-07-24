package ch.so.agi.vsa.views.list;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.vaadin.flow.data.provider.AbstractBackEndDataProvider;
import com.vaadin.flow.data.provider.Query;
import com.vaadin.flow.data.provider.QuerySortOrder;
import com.vaadin.flow.data.provider.SortDirection;

import ch.so.agi.vsa.cayenne.Organisation;
import ch.so.agi.vsa.data.service.OrganisationService;
import jakarta.annotation.PostConstruct;

@Component
@DependsOn({"organisationService"})
public class OrganisationDataProvider extends AbstractBackEndDataProvider<Organisation, OrganisationFilter> {
    
    @Autowired
    OrganisationService organisationService;

    

    
//    public OrganisationDataProvider(OrganisationService organisationService) {
//        this.organisationService = organisationService;
//    }
    
    @Override
    protected Stream<Organisation> fetchFromBackEnd(Query<Organisation, OrganisationFilter> query) {
        // A real app should use a real database or a service
        // to fetch, filter and sort data.
//        OrganisationService organisationService = new OrganisationService();

        
        System.out.println("*****" + organisationService);
        
        
        Stream<Organisation> stream = organisationService.findAllOrganisations("").stream();

        // Filtering
        if (query.getFilter().isPresent()) {
            stream = stream.filter(organisation -> query.getFilter().get().test(organisation));
        }

        // Sorting
        if (query.getSortOrders().size() > 0) {
            stream = stream.sorted(sortComparator(query.getSortOrders()));
        }

        // Pagination
        return stream.skip(query.getOffset()).limit(query.getLimit());
    }

    @Override
    protected int sizeInBackEnd(Query<Organisation, OrganisationFilter> query) {
        System.out.println("sizeInBackEnd");
        return (int) fetchFromBackEnd(query).count();
    }
    
    
    private static Comparator<Organisation> sortComparator(List<QuerySortOrder> sortOrders) {
        return sortOrders.stream().map(sortOrder -> {
            Comparator<Organisation> comparator = organisationFieldComparator(sortOrder.getSorted());

            if (sortOrder.getDirection() == SortDirection.DESCENDING) {
                comparator = comparator.reversed();
            }

            return comparator;
        }).reduce(Comparator::thenComparing).orElse((o1, o2) -> 0);
    }

    private static Comparator<Organisation> organisationFieldComparator(String sorted) {
        if (sorted.equals("bezeichnung")) {
            //return Comparator.comparing(organisation -> organisation);
//        } else if (sorted.equals("profession")) {
//            return Comparator.comparing(person -> person.getProfession());
//        }
        }
        return (o1, o2) -> 0;
    }


}

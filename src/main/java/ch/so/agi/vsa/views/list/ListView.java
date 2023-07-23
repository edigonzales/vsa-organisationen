package ch.so.agi.vsa.views.list;

import ch.so.agi.vsa.cayenne.Organisation;
import ch.so.agi.vsa.data.service.OrganisationService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.lang.CharSequence;


import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.Column;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.LocalDateRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "")
@PageTitle("VSA | Organisationen")
public class ListView extends VerticalLayout { 
    Grid<Organisation> grid = new Grid<>(Organisation.class, false); 
    TextField filterText = new TextField();

    OrganisationService organisationService;
    
    public ListView(OrganisationService organisationService) {
        this.organisationService = organisationService;
        
        addClassName("list-view"); 
        setSizeFull();
        configureGrid(); 

        add(getToolbar(), grid);
        updateList();
    }

    private void configureGrid() {
        grid.addClassNames("organisation-grid"); 
        grid.setSizeFull();
        //grid.setColumns(Organisation.BEZEICHNUNG.getName(), Organisation.AUID.getName(), Organisation.ASTATUS.getName(), Organisation.ORGANISATIONSTYP.getName());

        grid.addColumn(Organisation.BEZEICHNUNG.getName()).setComparator(new Comparator<Organisation>() {
            @Override
            public int compare(Organisation o1, Organisation o2) {                                                
                String string0 = o1.getBezeichnung().toLowerCase();
                String string1 = o2.getBezeichnung().toLowerCase();
                string0 = string0.replace("ä", "a");
                string0 = string0.replace("ö", "o");
                string0 = string0.replace("ü", "u");
                string1 = string1.replace("ä", "a");
                string1 = string1.replace("ö", "o");
                string1 = string1.replace("ü", "u");
                return string0.compareTo(string1);
            }
        });
        // Hier stimmt was nicht im Zusammenspiel Cayenne und Vaadin. Gross-/Kleinschreibung des Property?
        // Organisation.T_ILI_TID.getName() liefert 'tIliTid'.
        // grid.addColumn("TIliTid"); 
        grid.addColumn(Organisation.AUID.getName()).setHeader("UID");
        grid.addColumn(Organisation.ASTATUS.getName()).setHeader("Status");
        grid.addColumn(Organisation.ORGANISATIONSTYP.getName());
        grid.addColumn(new LocalDateRenderer<>(Organisation::getLetzteAenderungDate, "dd.MM.YYYY"))
        .setSortable(true)
        .setComparator(new Comparator<Organisation>() {
            @Override
            public int compare(Organisation o1, Organisation o2) {
                return o1.getLetzteAenderungDate().compareTo(o2.getLetzteAenderungDate());
            }
        })
        .setHeader("Letzte Änderung");
        
        
//        grid.addColumn(contact -> contact.getStatus().getName()).setHeader("Status"); 
//        grid.addColumn(contact -> contact.getCompany().getName()).setHeader("Company");
        grid.getColumns().forEach(col -> col.setAutoWidth(true)); 
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY); 

        Button addContactButton = new Button("Add contact");

        var toolbar = new HorizontalLayout(filterText, addContactButton); 
        toolbar.addClassName("toolbar"); 
        return toolbar;
    }
    
    private void updateList() { 
        grid.setItems(organisationService.findAllOrganisations(filterText.getValue()));
    }

}

package ch.so.agi.vsa.views.list;

import ch.so.agi.vsa.cayenne.Organisation;

public class OrganisationFilter {
    private String searchTerm;

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public boolean test(Organisation organisation) {
        boolean matchesBezeichnung = matches(organisation.getBezeichnung(), searchTerm);
        return matchesBezeichnung;
//        boolean matchesFullName = matches(organisation.getFullName(), searchTerm);
//        boolean matchesProfession = matches(organisation.getProfession(), searchTerm);
//        return matchesFullName || matchesProfession;
    }

    private boolean matches(String value, String searchTerm) {
        return searchTerm == null || searchTerm.isEmpty()
                || value.toLowerCase().contains(searchTerm.toLowerCase());
    }

}

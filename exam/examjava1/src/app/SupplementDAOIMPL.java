package app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SupplementDAOIMPL implements SupplementDAO {

    private Map<String, Supplement> supplements;

    public SupplementDAOIMPL() {
        supplements = new HashMap<>();
        for (Supplement supp : Data.getInstance().supplementsData) {
            supplements.put(supp.getCode(), supp);
        }
    }

    @Override
    public Supplement getOne(String code) {
        for (Map.Entry<String, Supplement> supp : supplements.entrySet()) {
            if (supp.getKey().equals(code))
                return supp.getValue();
        }
        return null;
    }

    @Override
    public List<Supplement> getAll() {
        List<Supplement> list = new ArrayList<>();
        for (Map.Entry<String, Supplement> supp : supplements.entrySet()) {
            list.add(supp.getValue());
        }
        return list;
    }

}

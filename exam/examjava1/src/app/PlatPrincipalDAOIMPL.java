package app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlatPrincipalDAOIMPL implements PlatPrincipalDAO {

    private Map<String, PlatPrincipal> plats;

    public PlatPrincipalDAOIMPL() {
        plats = new HashMap<>();
        for (PlatPrincipal plat : Data.getInstance().platsData) {
            plats.put(plat.getCode(), plat);
        }
    }

    @Override
    public PlatPrincipal getOne(String code) {
        for (Map.Entry<String, PlatPrincipal> plat : plats.entrySet()) {
            if (plat.getKey().equals(code))
                return plat.getValue();
        }
        return null;
    }

    @Override
    public List<PlatPrincipal> getAll() {
        List<PlatPrincipal> list = new ArrayList<>();
        for (Map.Entry<String, PlatPrincipal> plat : plats.entrySet()) {
            list.add(plat.getValue());
        }
        return list;
    }

}

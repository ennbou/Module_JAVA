package app;

import java.util.List;

public interface PlatPrincipalDAO {
    PlatPrincipal getOne(String code);
    List<PlatPrincipal> getAll();   
}

package app;

import java.util.List;

public interface SupplementDAO {

    Supplement getOne(String code);
    List<Supplement> getAll();
}

package app;

import java.util.List;

public interface IngredientDAO {

    Ingredient getOne(String code);
    List<Ingredient> getAll();

}

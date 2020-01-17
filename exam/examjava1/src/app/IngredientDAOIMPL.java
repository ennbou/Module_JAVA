package app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IngredientDAOIMPL implements IngredientDAO {

    private final Map<String, Ingredient> ingredients;

    public IngredientDAOIMPL() {
        ingredients = new HashMap<>();
        for (final Ingredient ing : Data.getInstance().ingredientsData) {
            ingredients.put(ing.getCode(), ing);
        }
    }

    @Override
    public Ingredient getOne(final String code) {
        for (final Map.Entry<String, Ingredient> ing : ingredients.entrySet()) {
            if (ing.getKey().equals(code))
                return ing.getValue();
        }
        return null;
    }

    @Override
    public List<Ingredient> getAll() {
        final List<Ingredient> list = new ArrayList<>();
        for (final Map.Entry<String, Ingredient> ing : ingredients.entrySet()) {
            list.add(ing.getValue());
        }
        return list;
    }

}

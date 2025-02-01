package ma.tp2_pizza_mobile.dao;

import java.util.List;

import ma.tp2_pizza_mobile.classes.Produit;

public interface IDao <T> {
    public boolean create(T o);

    public boolean update(T O);
    public boolean delete(T o);
    public List<T> findAll ();
    T findById (int id);


}

package ma.tp2_pizza_mobile.service;

import java.util.ArrayList;
import java.util.List;

import ma.tp2_pizza_mobile.classes.Produit;
import ma.tp2_pizza_mobile.dao.IDao;

public class ProduitService implements IDao<Produit> {

    private List<Produit> produits;

    public ProduitService() {
        produits = new ArrayList<>();
    }

    @Override
    public boolean create(Produit o) {
        return produits.add(o);
    }

    @Override
    public boolean update(Produit o) {
        return false;
    }

    @Override
    public boolean delete(Produit o) {
        return produits.remove(o);
    }

    @Override
    public List findAll() {
        return produits;
    }

    @Override
    public Produit findById(int id) {
        for(Produit p :produits)
            if(p.getId() == id)
                return p;
        return null;
    }
}

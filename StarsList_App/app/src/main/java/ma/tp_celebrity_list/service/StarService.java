package ma.tp_celebrity_list.service;

import java.util.ArrayList;
import java.util.List;

import ma.tp_celebrity_list.beans.Star;
import ma.tp_celebrity_list.dao.IDao;

public class StarService implements IDao<Star> {
    private List<Star> stars;
    private static StarService instance;
    private StarService() {
        this.stars = new ArrayList<>();
    }
    public static StarService getInstance() {
        if(instance == null)
            instance = new StarService();
        return instance;
    }
    @Override
    public boolean create(Star o) {
        return stars.add(o);
    }
    @Override
    public boolean update(Star o) {
        for (int i = 0; i < stars.size(); i++) {
            if (stars.get(i).getId() == o.getId()) {
                stars.set(i, o);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(Star o) {
        return stars.remove(o);
    }
    @Override
    public Star findById(int id) {
        for(Star s : stars){
            if(s.getId() == id)
                return s;
        }
        return null;
    }
    @Override
    public List<Star> findAll() {
        return stars;
    }
}

package porquenao.mobi.forecast.core.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import porquenao.mobi.forecast.core.database.DatabaseControl;

/**
 * Created by lucas on 7/11/15.
 */
public class GenericDAO<E> extends DatabaseControl {

    protected Dao<E, String> dao;
    private Class<E> type;

    public GenericDAO(Context context, Class<E> type) {
        super(context);
        this.type = type;
        setDao();
    }

    public void clearTable() {
        try {
            TableUtils.clearTable(dao.getConnectionSource(), type);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void setDao() {
        try {
            dao = DaoManager.createDao(getConnectionSource(), type);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<E> getAll() {
        List<E> list = new ArrayList<>();
        try {
            list = dao.queryForAll();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public E getById(String id) {
        try {
            E obj = dao.queryForId(id);
            return obj;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean insert(List<E> objs) {
        clearTable();
        try {
            for (int i = 0; i < objs.size(); i++) {
                dao.create(objs.get(i));
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void delete(E obj) {
        try {
            dao.delete(obj);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(E obj) {
        try {
            dao.update(obj);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

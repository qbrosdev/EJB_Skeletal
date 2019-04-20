import model.dao.BaseDao;
import model.entity.BaseEntity;

import javax.ejb.EJB;

/**
 * Created by V.Ghasemi
 * on 4/20/2019.
 */

public class BaseService <T extends BaseDao, E extends BaseEntity> {

    @EJB
    protected T serviceDaoImple;

    public E getById(){
        return null;
    }
}

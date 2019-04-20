package model.dao;


import model.entity.UserEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import java.util.List;


@NamedQueries({
        @NamedQuery(name = "User.find.by.username.or.email", query = "select userItem from UserEntity ue " +
                "WHERE ue.username = :inputPlaceHolder or ue.email = :inputPlaceHolder")
})
@ApplicationScoped
public class UserDao  extends BaseDao <UserEntity>{

    public UserEntity findByUsernameOrEmail(String identifier) {
        List<UserEntity> userItems = em.createNamedQuery("User.find.by.username.or.email",getEntityClass())
                .setParameter("inputPlaceHolder", identifier).setMaxResults(1).getResultList();
        if (userItems.isEmpty()) {
            return null;
        }
        return userItems.get(0);
    }

    @Override
    public Class getEntityClass() {
        return UserDao.class;
    }
}

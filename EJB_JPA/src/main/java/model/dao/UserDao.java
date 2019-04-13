//package model.dao;
//
//
//import com.qbros.model.entity.UserItem;
//
//import javax.enterprise.context.ApplicationScoped;
//import java.util.List;
//import java.util.Optional;
//
///**
// * Service that provides operations for {@link com.qbros.model.entity.UserItem}s.
// *
// * @author cassiomolin
// */
//@ApplicationScoped
//public class UserDao extends BaseDao{
//
//    /**
//     * Find a user by username or email.
//     *
//     * @param identifier
//     * @return
//     */
//    public UserItem findByUsernameOrEmail(String identifier) {
//        List<UserItem> userItems = em.createQuery("SELECT u FROM UserItem u WHERE u.username = :identifier OR u.email = :identifier", UserItem.class)
//                .setParameter("identifier", identifier)
//                .setMaxResults(1)
//                .getResultList();
//        if (userItems.isEmpty()) {
//            return null;
//        }
//        return userItems.get(0);
//    }
//
//    /**
//     * Find all users.
//     *
//     * @return
//     */
//    public List<UserItem> findAll() {
//        return em.createQuery("SELECT u FROM UserItem u", UserItem.class).getResultList();
//    }
//
//    /**
//     * Find a user by id.
//     *
//     * @param userId
//     * @return
//     */
//    public Optional<UserItem> findById(Long userId) {
//        return Optional.ofNullable(em.find(UserItem.class, userId));
//    }
//
//    @Override
//    public Class getEntityClass() {
//        return UserItem.class;
//    }
//}

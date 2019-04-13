//package model.dao;
//
//import com.qbros.model.entity.Address;
//
//import javax.ejb.LocalBean;
//import javax.ejb.Stateless;
//
//
//@Stateless
//@LocalBean
//public class AddressDao extends BaseDao {
//
//    public boolean create(Address entity) {
//        try {
//            em.persist(entity);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    @Override
//    public Class getEntityClass() {
//        return Address.class;
//    }
//}

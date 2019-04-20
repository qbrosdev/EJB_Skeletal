package model.dao;


import model.entity.Address;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;


@Stateless
@LocalBean
public class AddressDAO extends BaseDao <Address> {


    @Override
    public Class getEntityClass() {
        return Address.class;
    }
}

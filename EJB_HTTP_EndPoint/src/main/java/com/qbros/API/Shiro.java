package com.qbros.API;

import Utils.JsonMapper;
import intf.iTokenProvider;
import model.Subject.User;
import model.credential.Token.AbsToken;
import org.apache.shiro.SecurityUtils;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/*
  Context root is the base uri of your app:. for web app (single war file) it is the name of war file
  for ear file it is defined in application.xml file.

  A @Path value isn't required to have leading or trailing slashes (/).
 */

@Path("sec")
public class Shiro {

    public static final String MESSAGE = "It works!!";

    @Inject
    private iTokenProvider tokenProvider;

    @POST
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login() throws Exception {
        //fixme: check which parameters are received from SecurityUtils
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        AbsToken token = tokenProvider.generateTokenForUser(user);
        return Response.status(200).entity(token).build();
    }

    @GET
    @Path("test")
    @Produces(MediaType.APPLICATION_JSON)
    public Response secured() throws Exception {
        return Response.status(200).entity(JsonMapper.ObjToJsonString(MESSAGE)).build();
    }
}

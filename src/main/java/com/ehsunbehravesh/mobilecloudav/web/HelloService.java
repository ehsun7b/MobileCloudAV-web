/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ehsunbehravesh.mobilecloudav.web;

import com.ehsunbehravesh.mobilecloudav.ejb.HelloBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author ehsun7b
 */
@Stateless
@Path("service")
public class HelloService {

  @Inject 
  private HelloBean bean;
  
  @GET
  @Path("hello")
  @Produces("text/html")
  public String say() {
    return bean.say();
  }

    // Add business logic below. (Right-click in editor and choose
  // "Insert Code > Add Business Method")
  
}

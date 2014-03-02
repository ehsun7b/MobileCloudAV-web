/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ehsunbehravesh.mobilecloudav.web;

import com.ehsunbehravesh.mobilecloudav.ejb.DefaultScanBean;
import com.ehsunbehravesh.mobilecloudav.ejb.HelloBean;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author ehsun7b
 */
@Stateless
@Path("service")
public class HelloService {

  @EJB
  private HelloBean bean;
  
  @EJB
  private DefaultScanBean scanner;
  
  @GET
  @Path("hello")
  @Produces("text/html")
  public String say() {
    return bean.say();
  }

  @POST
  @Path("upload")
  @Consumes("*/*")
  @Produces(MediaType.TEXT_PLAIN)
  public String uploadFile() {
    
    return bean.say();
  }  
}

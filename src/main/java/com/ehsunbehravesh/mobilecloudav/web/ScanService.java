package com.ehsunbehravesh.mobilecloudav.web;

import com.ehsunbehravesh.mobilecloudav.ejb.DefaultScanBean;
import com.ehsunbehravesh.mobilecloudav.result.ScanResult;
import com.sun.jersey.multipart.FormDataParam;
import java.io.IOException;
import java.io.InputStream;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Priority;

/**
 *
 * @author ehsun7b
 */
@Path("scanner")
public class ScanService {

  private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ScanService.class);
  
  @EJB
  private DefaultScanBean scanner;
  

  @POST
  @Path("/scan")
  @Consumes(MediaType.MULTIPART_FORM_DATA)
  @Produces(MediaType.APPLICATION_JSON)
  public ScanResult scan(@FormDataParam("file") InputStream is) {
    try {
      byte[] fileContent = IOUtils.toByteArray(is);
      Byte[] objContent = ArrayUtils.toObject(fileContent);
      ScanResult result = scanner.scanBytes(objContent);
      return result;
    } catch (IOException ex) {
      logger.log(Priority.FATAL, ex.getMessage(), ex);
    }    
    
    return null;
  }

}

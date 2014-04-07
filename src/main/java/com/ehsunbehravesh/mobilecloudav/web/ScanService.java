package com.ehsunbehravesh.mobilecloudav.web;

import com.ehsunbehravesh.mobilecloudav.ejb.DefaultScanBean;
import com.ehsunbehravesh.mobilecloudav.result.ScanResult;
import com.sun.jersey.multipart.FormDataParam;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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
      /*byte[] fileContent = IOUtils.toByteArray(is);
       Byte[] objContent = ArrayUtils.toObject(fileContent);
       ScanResult result = scanner.scanBytes(objContent);
       return result;*/

      String tempDirectory = System.getProperty("java.io.tmpdir");
      String pathToTempFile = tempDirectory + File.separator + System.nanoTime() + ".cav";
      File file = writeToFile(pathToTempFile, is);
      ScanResult result = scanner.scanFile(file);

      return result;
    } catch (Exception ex) {
      logger.log(Priority.FATAL, ex.getMessage(), ex);
    }

    return null;
  }

  private File writeToFile(String file, InputStream inputStream) {
    int bufferSize = 1014;
    byte[] buffer = new byte[bufferSize];
    int len = 0;

    File result = new File(file);

    try (FileOutputStream fos = new FileOutputStream(result)) {

      while ((len = inputStream.read(buffer)) > 0) {
        fos.write(buffer, 0, len);
      }

    } catch (Exception ex) {
      logger.log(Priority.FATAL, "Error in writing temp file. ", ex);
    }

    return result;
  }

}

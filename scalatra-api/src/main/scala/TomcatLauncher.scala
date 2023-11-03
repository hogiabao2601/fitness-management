import org.apache.catalina.startup.Tomcat
import org.scalatra.servlet.ScalatraListener

import java.io.File

object TomcatLauncher extends App {
  val baseDir        = System.getProperty("java.io.tmpdir")
  val contextPath    = "/"
  val docBase        = new File(".").getAbsolutePath
  val port           = sys.env.getOrElse("PORT", "8080").toInt
  val tomcat: Tomcat = new Tomcat()
  tomcat.setPort(port)
  tomcat.getConnector
  tomcat.setBaseDir(baseDir)

  val context = tomcat.addContext(contextPath, docBase)
  context.addApplicationListener(classOf[ScalatraListener].getName)
  context.addServletContainerInitializer(new ScalatraInitializer, null)

  tomcat.start()
  tomcat.getServer.await()
}

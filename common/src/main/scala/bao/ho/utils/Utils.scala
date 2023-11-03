package bao.ho.utils

object Utils {

  def fail[T](message: String): T      = throw new RuntimeException(message)
  def fail[T](throwable: Throwable): T = throw throwable
}

import co.touchlab.kermit.Logger
import com.copperleaf.ballast.BallastLogger
import com.copperleaf.ballast.core.BallastLoggingException

/**
 * Logs in a cross-platform way using Kermit.
 */
class KermitBallastLogger(tag: String) : BallastLogger {
  private val logger = Logger.withTag(tag)

  override fun debug(message: String) {
    logger.d(message)
  }

  override fun info(message: String) {
    logger.i(message)
  }

  override fun error(throwable: Throwable) {
    if (throwable is BallastLoggingException) {
      logger.e(
        "Ballast error capture, input sequence types " +
          "are ${throwable.inputSequence.map { it::class.simpleName }}",
        throwable
      )
    } else {
      logger.e("Ballast error capture", throwable)
    }
  }
}

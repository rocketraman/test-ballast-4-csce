import FooScreenContract.Events
import FooScreenContract.Inputs
import FooScreenContract.State
import com.copperleaf.ballast.EventHandler
import com.copperleaf.ballast.EventHandlerScope

class FooScreenEventHandler(
  private val onSuccess: () -> Unit,
) : EventHandler<Inputs, Events, State> {
  override suspend fun EventHandlerScope<Inputs, Events, State>.handleEvent(event: Events) = when (event) {
    is Events.OnSuccess -> onSuccess()
  }
}

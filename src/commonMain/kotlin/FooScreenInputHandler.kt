import FooScreenContract.Events
import FooScreenContract.Inputs
import FooScreenContract.State
import com.copperleaf.ballast.InputHandler
import com.copperleaf.ballast.InputHandlerScope

typealias FooInputHandlerScope = InputHandlerScope<Inputs, Events, State>

class FooScreenInputHandler : InputHandler<Inputs, Events, State> {
  @Suppress("LongMethod", "CyclomaticComplexMethod")
  override suspend fun FooInputHandlerScope.handleInput(input: Inputs) = when (input) {
    Inputs.Initialize -> noOp()
    Inputs.Increment -> updateState { it.copy(count = it.count + 1) }
  }
}

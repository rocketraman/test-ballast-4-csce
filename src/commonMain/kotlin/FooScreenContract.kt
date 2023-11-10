object FooScreenContract {
  data class State(
    val initialized: Boolean = false,
    val count: Int = 0,
  )

  sealed class Inputs {
    data object Initialize : Inputs()
    data object Increment : Inputs()
  }

  sealed class Events {
    data object OnSuccess : Events()
  }
}

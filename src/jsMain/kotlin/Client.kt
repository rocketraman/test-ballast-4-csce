@file:Suppress("MissingPackageDeclaration")

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.copperleaf.ballast.BallastViewModelConfiguration
import com.copperleaf.ballast.build
import com.copperleaf.ballast.core.BasicViewModel
import com.copperleaf.ballast.core.BootstrapInterceptor
import com.copperleaf.ballast.core.LoggingInterceptor
import com.copperleaf.ballast.navigation.routing.Backstack
import com.copperleaf.ballast.navigation.routing.RouterContract
import com.copperleaf.ballast.navigation.routing.build
import com.copperleaf.ballast.navigation.routing.directions
import com.copperleaf.ballast.navigation.routing.renderCurrentDestination
import com.copperleaf.ballast.withViewModel
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.renderComposable

external fun require(o: String)

fun main() {
  renderComposable("root") {
    App()
  }
}

/*
val di = DI {
}
*/

@Suppress("LongMethod")
@Composable
fun App() {
  val scope = rememberCoroutineScope()
  val router = remember(scope) { RouterViewModel(scope) }
  val routerState: Backstack<AppRoutes> by router.observeStates().collectAsState()

  routerState.renderCurrentDestination(
    route = { routes: AppRoutes ->
      when(routes) {
        AppRoutes.Home -> {
          Home(router)
        }
        AppRoutes.Foo -> {
          Foo(router)
        }
      }
    },
    notFound = {
      // this can be used to replicate the null routing, start at /notexist, then click on "Foo", then go back twice
      router.trySend(RouterContract.Inputs.GoToDestination(AppRoutes.Home.directions().build()))
    },
  )
}

@Composable
private fun Home(router: RouterViewModel) {
  Text("Home")
  Button(attrs = {
    style { padding(14.px, 24.px) }
    onClick {
      router.trySend(RouterContract.Inputs.GoToDestination(AppRoutes.Foo.directions().build()))
    }
  }) {
    Text("Foo")
  }
}

@Composable
private fun Foo(router: RouterViewModel) {
  val scope = rememberCoroutineScope()

  val vm = remember(scope) {
    BasicViewModel(
      config = singletonVmConfig,
      eventHandler = FooScreenEventHandler(
        onSuccess = {
        }
      ),
      coroutineScope = scope,
    )
  }
  val state by vm.observeStates().collectAsState()

  Text("Foo value = ${state.count}")
  Button(attrs = {
    style { padding(14.px, 24.px) }
    onClick {
      router.trySend(RouterContract.Inputs.GoToDestination(AppRoutes.Home.directions().build()))
    }
  }) {
    Text("Home")
  }
  Button(attrs = {
    style { padding(14.px, 24.px) }
    onClick {
      vm.trySend(FooScreenContract.Inputs.Increment)
    }
  }) {
    Text("Increment")
  }
}

private val singletonVmConfig = BallastViewModelConfiguration.Builder()
  .withViewModel(
    initialState = FooScreenContract.State(),
    inputHandler = FooScreenInputHandler(),
  )
  .apply {
    name = "Foo"
    interceptors += LoggingInterceptor()
    interceptors += BootstrapInterceptor { FooScreenContract.Inputs.Initialize }
    logger = { KermitBallastLogger(it) }
  }
  .build()

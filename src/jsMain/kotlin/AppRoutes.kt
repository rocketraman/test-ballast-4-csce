import com.copperleaf.ballast.BallastViewModelConfiguration
import com.copperleaf.ballast.build
import com.copperleaf.ballast.eventHandler
import com.copperleaf.ballast.navigation.browser.withBrowserHistoryRouter
import com.copperleaf.ballast.navigation.routing.Route
import com.copperleaf.ballast.navigation.routing.RouteAnnotation
import com.copperleaf.ballast.navigation.routing.RouteMatcher
import com.copperleaf.ballast.navigation.routing.RoutingTable
import com.copperleaf.ballast.navigation.routing.fromEnum
import com.copperleaf.ballast.navigation.vm.BasicRouter
import kotlinx.coroutines.CoroutineScope

enum class AppRoutes(
  routeFormat: String,
  override val annotations: Set<RouteAnnotation> = emptySet(),
) : Route {
  Home("/home"),
  Foo("/foo"),
  ;

  override val matcher: RouteMatcher = RouteMatcher.create(routeFormat)
}

@OptIn(ExperimentalStdlibApi::class)
class RouterViewModel(
  viewModelCoroutineScope: CoroutineScope
) : BasicRouter<AppRoutes>(
  config = BallastViewModelConfiguration.Builder()
    // todo interceptors for logging
    .withBrowserHistoryRouter(
      RoutingTable.fromEnum(AppRoutes.entries.toTypedArray()), basePath = "/", initialRoute = AppRoutes.Home
    )
    .build(),
  eventHandler = eventHandler { },
  coroutineScope = viewModelCoroutineScope,
)

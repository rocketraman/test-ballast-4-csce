if (config.devServer) {
  // reroute 404s to index.html for client-side routing
  config.devServer.historyApiFallback = true;
}

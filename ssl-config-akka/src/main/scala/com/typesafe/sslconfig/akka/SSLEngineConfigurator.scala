/*
 * Copyright (C) 2015 Typesafe Inc. <http://www.typesafe.com>
 */

package com.typesafe.sslconfig.akka

import javax.net.ssl.{ SSLContext, SSLEngine }

import com.typesafe.sslconfig.ssl.SSLConfig

/**
 * Gives the chance to configure the SSLContext before it is going to be used.
 * The passed in context will be already set in client mode and provided with hostInfo during initialization.
 */
trait SSLEngineConfigurator {
  def configure(engine: SSLEngine, sslContext: SSLContext): SSLEngine
}

final class DefaultSSLEngineConfigurator(config: SSLConfig, enabledProtocols: Array[String], enabledCipherSuites: Array[String])
    extends SSLEngineConfigurator {
  def configure(engine: SSLEngine, sslContext: SSLContext): SSLEngine = {
    engine.setSSLParameters(sslContext.getDefaultSSLParameters)
    engine.setEnabledProtocols(enabledProtocols)
    engine.setEnabledCipherSuites(enabledCipherSuites)
    engine
  }
}

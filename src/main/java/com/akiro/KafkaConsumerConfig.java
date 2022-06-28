package com.akiro;

public class KafkaConsumerConfig {

  private String bootstrapservers;

  private String topic;

  private String autoResetConfig;

  private long pollInterval;

  private boolean kerberosEnabled;

  private String saslMechanism;

  private String securityProtocol;

  private String kerberosServiceName;

  private String jaasConfig;

  public String getBootstrapservers() {
    return bootstrapservers;
  }

  public void setBootstrapservers(String bootstrapservers) {
    this.bootstrapservers = bootstrapservers;
  }

  public String getTopic() {
    return topic;
  }

  public void setTopic(String topic) {
    this.topic = topic;
  }

  public String getAutoResetConfig() {
    return autoResetConfig;
  }

  public void setAutoResetConfig(String autoResetConfig) {
    this.autoResetConfig = autoResetConfig;
  }

  public long getPollInterval() {
    return pollInterval;
  }

  public void setPollInterval(long pollInterval) {
    this.pollInterval = pollInterval;
  }

  public boolean isKerberosEnabled() {
    return kerberosEnabled;
  }

  public void setKerberosEnabled(boolean kerberosEnabled) {
    this.kerberosEnabled = kerberosEnabled;
  }

  public String getSaslMechanism() {
    return saslMechanism;
  }

  public void setSaslMechanism(String saslMechanism) {
    this.saslMechanism = saslMechanism;
  }

  public String getSecurityProtocol() {
    return securityProtocol;
  }

  public void setSecurityProtocol(String securityProtocol) {
    this.securityProtocol = securityProtocol;
  }

  public String getKerberosServiceName() {
    return kerberosServiceName;
  }

  public void setKerberosServiceName(String kerberosServiceName) {
    this.kerberosServiceName = kerberosServiceName;
  }

  public String getJaasConfig() {
    return jaasConfig;
  }

  public void setJaasConfig(String jaasConfig) {
    this.jaasConfig = jaasConfig;
  }
}

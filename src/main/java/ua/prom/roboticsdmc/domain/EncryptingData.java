package ua.prom.roboticsdmc.domain;

import java.util.Objects;

public class EncryptingData {
    private final String sourceTextPath;
    private final String command;
    private final int key;

    public EncryptingData(Builder builder) {
        this.sourceTextPath = builder.sourceTextPath;
        this.command = builder.command;
        this.key = builder.key;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getSourceTextPath() {
        return sourceTextPath;
    }

    public String getCommand() {
        return command;
    }

    public int getKey() {
        return key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        EncryptingData encryptingData = (EncryptingData) o;
        return Objects.equals(key, encryptingData.key)
            && Objects.equals(sourceTextPath, encryptingData.sourceTextPath)
            && Objects.equals(command, encryptingData.command);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sourceTextPath, command, key);
    }

    public static class Builder {

        private String sourceTextPath;
        private String command;
        private int key;

        private Builder() {
        }

        public Builder withSourceTextPath(String sourceTextPath) {
            this.sourceTextPath = sourceTextPath;
            return this;
        }

        public Builder withCommand(String command) {
            this.command = command;
            return this;
        }

        public Builder withKey(int key) {
            this.key = key;
            return this;
        }

        public EncryptingData build() {
            return new EncryptingData(this);
        }
    }
}

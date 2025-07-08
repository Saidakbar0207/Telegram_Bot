package uz.pdp.telegram_bot.factory;

import java.util.UUID;

public class RecordWrapper {
    private UUID id;
    private String name;
    private String command;

    protected RecordWrapper(UUID id, String name, String command) {
        this.id = id;
        this.name = name;
        this.command = command;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCommand() {
        return command;
    }


    public static class WrappedRecord {
        private UUID id;
        private String name;
        private String command;

        public WrappedRecord id(UUID id) {
            this.id = id;
            return this;
        }

        public WrappedRecord name(String name) {
            this.name = name;
            return this;
        }

        public WrappedRecord command(String command) {
            this.command = command;
            return this;
        }

        public RecordWrapper build() {
            return new RecordWrapper(id, name, command);
        }
    }
}

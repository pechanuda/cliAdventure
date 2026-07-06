package org.pechanuda.orchestration;

public class Command {

    private CommandType commandType;
    private String subject;

    public CommandType getCommandType() {
        return commandType;
    }

    public void setCommandType(CommandType commandType) {
        this.commandType = commandType;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}

enum CommandType {
    MOVE_COMMAND("go"),
    TALK_COMMAND("talk"),
    PICK_COMMAND("pick"),
    ATTACK_COMMAND("attack"),
    USE_COMMAND("use"),
    GIVE_COMMAND("give"),
    INVALID_COMMAND(""),
    EXIT_COMMAND("");

    public final String invocationName;

    CommandType(String invocationName) {
        this.invocationName = invocationName;
    }
}

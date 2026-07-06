package org.pechanuda.orchestration;

public class CommandParser {

    public Command parsePrompt(String prompt) {

        Command command = new Command();

        if (prompt.split(" ").length > 1) {
            String verb = prompt.split(" ")[0];
            String subject = prompt.substring(verb.length() + 1);
            command.setSubject(subject);

            switch (verb.toLowerCase()) {
                case "go" -> {
                    command.setCommandType(CommandType.MOVE_COMMAND);
                }
                case "pick" -> {
                    command.setCommandType(CommandType.PICK_COMMAND);
                }
                case "attack" -> {
                    command.setCommandType(CommandType.ATTACK_COMMAND);
                }
                case "talk" -> {
                    command.setCommandType(CommandType.TALK_COMMAND);
                }
                case "use" -> {
                    command.setCommandType(CommandType.USE_COMMAND);
                }
                case "give" -> {
                    command.setCommandType(CommandType.GIVE_COMMAND);
                }
                default -> {
                    command.setCommandType(CommandType.INVALID_COMMAND);
                    System.out.println("!!!! INVALID STATEMENT !!!!");
                }
            }
        } else if(
                prompt.equalsIgnoreCase("EXIT") ||
                        prompt.equalsIgnoreCase("QUIT") ||
                        prompt.equalsIgnoreCase("Q")
        ) {
            command.setCommandType(CommandType.EXIT_COMMAND);
        } else {
            command.setCommandType(CommandType.INVALID_COMMAND);
            System.out.println("!!!! INVALID STATEMENT !!!!");
        }
        return command;
    }
}

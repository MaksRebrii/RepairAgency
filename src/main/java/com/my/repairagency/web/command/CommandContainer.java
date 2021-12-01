package com.my.repairagency.web.command;

import java.util.HashMap;
import java.util.Map;

public class CommandContainer {

    private static final Map<String, Command> commands;

    static {
        commands = new HashMap<>();
        commands.put("login", new LoginCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("register", new RegisterCommand());
        commands.put("newApplication", new CreateApplicationCommand());
        commands.put("getAllApplications", new GetAllApplicationsCommand());
        commands.put("setMaster", new SetMasterCommand());
        commands.put("setPrice", new SetPriceCommand());
        commands.put("changePaymentStatus", new ChangePaymentStatusCommand());
        commands.put("changeCompletionStatus", new ChangeCompletionStatusCommand());
        commands.put("cancelApplication", new CancelApplicationCommand());
        commands.put("editApplication", new EditApplicationCommand());
        commands.put("filterByCompletionStatus", new FilterByCompletionStatusCommand());
        commands.put("filterByPaymentStatus", new FilterByPaymentStatusCommand());
        commands.put("filterByPrice", new FilterByPriceCommand());
        commands.put("filterByDate", new FilterByDateCommand());
    }

    private CommandContainer(){}
    public static Command getCommand(String commandName){
        return commands.get(commandName);
    }
}

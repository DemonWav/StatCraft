package wav.demon.StatCraft;

import org.junit.Test;

import wav.demon.StatCraft.Commands.BaseCommand;
import wav.demon.StatCraft.Commands.CommandAlreadyDefinedException;

public class BaseCommandTest {

    @Test(expected = CommandAlreadyDefinedException.class)
    public void testConflictingCommands() {
        BaseCommand baseCommand = new BaseCommand(null);
        baseCommand.registerCommand("one", null);
        baseCommand.registerCommand("one", null);
    }

    @Test
    public void testOneCommand() {
        BaseCommand baseCommand = new BaseCommand(null);
        baseCommand.registerCommand("one", null);
    }

    @Test
    public void test100000Commands() {
        BaseCommand baseCommand = new BaseCommand(null);
        for (int i = 0; i < 100000; i++) {
            baseCommand.registerCommand(String.valueOf(i), null);
        }
    }

    @Test(expected = CommandAlreadyDefinedException.class)
    public void test100000CommandsWithOneConflict() {
        BaseCommand baseCommand = new BaseCommand(null);
        int j = 0;
        for (int i = 0; i < 100000; i++) {
            baseCommand.registerCommand(String.valueOf(i), null);
            j = i;
        }
        baseCommand.registerCommand(String.valueOf(j), null);
    }
}
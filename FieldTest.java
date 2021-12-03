import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

enum Command {ADD, UP, START, INFO, STOP, QUIT, INVALID}

public class FieldTest {
    private static Field field=new Field();
    public static void main(String[] args) {
        final Scanner sc=new Scanner(System.in);
        while(true) {
            final Command cmd = getCommand(sc);
            if (cmd==Command.QUIT) {
                System.out.println("Bye");
                break;
            }
            if (cmd==Command.INVALID) {
                System.out.println("Invalid Operation!");
                continue;
            }
            switch (cmd) {
                case ADD:
                    final Player newPlayer=createPlayer(sc);
                    System.out.println(newPlayer);
                    field.add(newPlayer);
                    break;
                case UP:
                    field.moveUp(choosePlayer(sc));
                    break;
                case START:
                    field.start();
                    field.info();
                    break;
                case STOP:
                    field.stop();
                    break;
                case INFO:
                    field.info();
                    break;
                default:
                    break;
            }
        }
        sc.close();
    }

    private static Command getCommand(final Scanner sc) {
        System.out.print("Enter Command String!");
        final String operation=sc.next();

        Command kind;
        try {
            kind=Command.valueOf(operation.toUpperCase());
        } catch (IllegalArgumentException e) {
            kind = Command.INVALID;
        }

        return kind;
    }

    private static int choosePlayer(Scanner sc) {
        final int jerseyNumber=sc.nextInt();
        return jerseyNumber;
    }

    private static Player createPlayer(final Scanner sc) {
        final String position=sc.next();
        final String name=sc.next();
        final int jerseyNumber=sc.nextInt();
        final int speed=sc.nextInt();
        final int stamin=sc.nextInt();
        final int passing=sc.nextInt();

        Player newPlayer=null;
        if("F".equals(position)) {
            newPlayer=new Forward(name, jerseyNumber, speed, stamin, passing);
        } else if ("M".equals(position)) {
            newPlayer=new Midfielder(name, jerseyNumber, speed, stamin, passing);
        } else if ("D".equals(position)) {
            newPlayer=new Defender(name, jerseyNumber, speed, stamin, passing);
        }
        return newPlayer;
    }
}

abstract class Player {
    private String name;
    private int jerseyNumber;

    private int[] abilities=new int[3];
    final int SPEED=0;
    final int STAMINA=1;
    final int PASSING=2;

    Position position;

    public Player(String name, int jerseyNumber, int speed, int stamina, int passing) {
        this.name=name;
        this.jerseyNumber=jerseyNumber;
        this.abilities[0]=speed;
        this.abilities[1]=stamina;
        this.abilities[2]=passing;
    }

    public class Position{
        public int x;
        public int y;

        public Position(int x, int y) {
            this.x=x;
            this.y=y;
        }

        public String toString() {
            return String.format("(%d , %d)", this.x,this.y);
        }
    }

    public Position getPosition() {
        return new Position(position.x, position.y);
    }

    public void setPosition(int x, int y) {
        this.position.x=x;
        this.position.y=y;
    }

    public void moveUp() {
        float move_delta=getMoveDelta();
        position.y=(int)(position.y-move_delta);
        decreaseStamina();
    }

    private void decreaseStamina() {
        abilities[STAMINA]=(int)(abilities[STAMINA]-2);
    }

    private float getMoveDelta() {
        return 1+getSpeed()/100*getStamina()/100;
    }

    protected float getSpeed() {
        return (float) abilities[SPEED];
    }

    protected float getPassing() {
        return (float) abilities[PASSING];
    }

    protected float getStamina() {
        return (float) abilities[STAMINA];
    }

    protected String getName() {
        return name;
    }

    protected int getJerseyNumber() {
        return jerseyNumber;
    }

    public String toString() {
        return String.format("Player Name=%s, JerseyNumber=%d Position %s Forward SPEED=%.1f, , " +
                        "STMINA=%.1f, , PASSING=%.1f",
                name, jerseyNumber, getPosition(), getSpeed(), getStamina(), getPassing());
    }
}

class Field {
    private List<Player> players=new ArrayList<>();

    public void add(Player player) {
        players.add(player);
    }

    public void moveUp(int n) {
        for(Player player :  players)
            if (player.getJerseyNumber()==n) player.moveUp();
    }
    public void moveDown(int player) {
    }

    public void moveRight(int player) {
    }

    public void moveLeft(int player) {
    }

    public void start() {
        for (Player player : players) {
            if(player.getName().equals("Forward")) {
                player.setPosition(34,25);
            }
            else if(player.getName().equals("Midfielder")) {
                player.setPosition(34,50);
            }
            else if(player.getName().equals("Defender")) {
                player.setPosition(34,70);
            }
        }
    }

    public void info() {
        for (Player player :  players)
            System.out.println(player);
    }

    public void stop() {

    }

}

class Forward extends Player {
    private int ACCELERATION_POINT=10;

    public Forward(String name, int num, int speed, int stamina, int passing) {
        super(name, num, speed,stamina,passing);
        position=new Position(0,0);
    }

    @Override
    protected float getSpeed() {
        return super.getSpeed()+ACCELERATION_POINT;
    }

    public String toString() {
        return String.format("Player Name=%s, JerseyNumber=%d Position (%d , %d) Forward SPEED=%.1f, , " +
                        "STMINA=%.1f, , PASSING=%.1f",
                super.getName(), super.getJerseyNumber(), position.x, position.y, getSpeed(), getStamina(), getPassing());
    }

}

class Midfielder extends Player {
    private int PASSING_POINT=10;


    public Midfielder(String name, int num, int speed, int stamina, int passing) {
        super(name, num, speed,stamina,passing);
        position=new Position(0,0);
    }

    public void start() {
        position.x=34;
        position.y=25;
    }

    @Override
    protected float getPassing() {
        return super.getPassing()+PASSING_POINT;
    }

    public String toString() {
        return String.format("Player Name=%s, JerseyNumber=%d Position (%d , %d) Forward SPEED=%.1f, , " +
                        "STMINA=%.1f, , PASSING=%.1f",
                super.getName(), super.getJerseyNumber(), position.x, position.y, getSpeed(), getStamina(), getPassing());
    }
}

class Defender extends Player {
    private int STRENGTH_POINT=10;

    Position position=new Position(0,0);

    public Defender(String name, int num, int speed, int stamina, int passing) {
        super(name, num, speed,stamina,passing);
        position=new Position(0,0);
    }

    public void start() {
        position.x=34;
        position.y=25;
    }

    @Override
    protected float getStamina() {
        return super.getStamina()+STRENGTH_POINT;
    }

    public String toString() {
        return String.format("Player Name=%s, JerseyNumber=%d Position (%d , %d) Forward SPEED=%.1f, , " +
                        "STMINA=%.1f, , PASSING=%.1f",
                super.getName(), super.getJerseyNumber(), position.x, position.y, getSpeed(), getStamina(), getPassing());
    }
}




import java.util.Scanner;

enum Command {ADD, FIND, CLEAR, LIST, QUIT, INVALID};

public class SchoolTest{
    private static Scanner scanner=new Scanner(System.in);
    public static void main(String[] args) {
        School pnu = new School("PNU", 100);
        while(true) {
            final Command cmd=getCommand(scanner);
            if(cmd==Command.QUIT) {
                System.out.println("Bye");
                break;
            }
            if(cmd==Command.INVALID) {
                System.out.println("Invalid Operation");
                continue;
            }
            switch (cmd) {
                case ADD: {
                    Student newStudent = creatStudent();
                    pnu.addStudent(newStudent);
                    break;
                }
                case FIND: {
                    findStudent(pnu);
                    break;
                }
                case CLEAR:
                    pnu.removeAllStudent();
                    break;
                case LIST:
                    System.out.println(pnu);
                    break;
                default:
                    break;
            }
        }
    }

    private static void findStudent(final School school) {
        final String studentName = scanner.next();
        final int schoolYear = scanner.nextInt();
        final Student foundStudent = school.findStudent(studentName, schoolYear);
        if (foundStudent!= null)
            System.out.println(foundStudent);
        else
            System.out.println("Student Not Found with name "+studentName+
                    " and year "+schoolYear);
    }

    private static Student creatStudent() {
        final String studentName=scanner.next();
        final int schoolYear = scanner.nextInt();
        return new Student(studentName, schoolYear);
    }

    private static Command getCommand(final Scanner scanner) {
        System.out.print("Enter Command String! ");
        final String commandStr = scanner.next().toUpperCase();
        Command command = Command.INVALID;

        try {
            command = Command.valueOf(commandStr);
        }
        catch ( IllegalArgumentException e ) {
        }

        return command;
    }
}

class School {
    private String name;
    private int limit;

    private Student[] students=new Student[100];
    private int studentCount=0;

    public School(String name, int limit) {
        this.name=name;
        this.limit=limit;
    }

    public void addStudent(Student newStudent) {
        students[studentCount]=newStudent;
        studentCount++;
    }

    public Student findStudent(String name, Integer year) {
        Student s = new Student(name, year);
        for (int i=0;i<studentCount;i++) {
            if (students[i].equals(s))
                return students[i];
        }

        return null;
    }

    public void removeAllStudent() {
        for (int i=0;i<studentCount;i++) {
            students[i]=null;
        }
        studentCount=0;
    }

    public String toString() {
        String msg="School Name: "+name+" Student Count: "+studentCount+"\n";
        for (int i=0;i<studentCount;i++) {
            msg+="\t"+students[i]+"\n";
        }
        return msg;
    }
}

class Student {
    private String name;
    private Integer year;

    public Student(String name, int year) {
        this.name=name;
        this.year=year;
    }

    public boolean equals(Object o) {
        if(!(o instanceof Student)) return false;
        Student other = (Student) o;
        return name.equals(other.name)&&year.equals(other.year);
    }

    public int hashCode() {
        int result=1;
        result=31*result+name.hashCode();
        result=31*result+year.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return String.format("[%s, %d학년]", name, year);
    }

}
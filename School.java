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
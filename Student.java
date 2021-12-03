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